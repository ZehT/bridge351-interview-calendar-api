package com.bridge351.interviewcalendarapi.slot;

import com.bridge351.interviewcalendarapi.InterviewCalendarApiApplication;
import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.slot.domain.SlotDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotFilterDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotRequestDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotRequestDateTimeDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>Class that represents the Integration Tests with MockMvc</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InterviewCalendarApiApplication.class)
public class SlotControllerIntegrationTests {

    final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenUserWhenFindSlotsByUserThenReturnUser() throws Exception {
        final SlotDTO expected = SlotDTO.builder()
                .name("Carl")
                .build();
        final MvcResult mvcResult = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/slots/user/")
                                .param("userId", "2")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        final String jsonResponse = mockHttpServletResponse.getContentAsString();
        final BasicResponse<List<SlotDTO>> basicResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
        assertEquals(HttpStatus.OK.value(), basicResponse.getStatusCode());
        assertEquals(expected.getName(), basicResponse.getData().get(0).getName());
    }

    @Test
    public void givenUserAndSlotsWhenAddSlotsThenValidateSucess() throws Exception {
        final List<SlotRequestDateTimeDTO> slotDateTimeList = new ArrayList<>();
        final SlotRequestDateTimeDTO slotDateTime = SlotRequestDateTimeDTO.builder()
                .slotDate(LocalDate.of(2022, 1, 2))
                .slotHour(12)
                .build();
        slotDateTimeList.add(slotDateTime);
        final SlotRequestDTO slotRequest = SlotRequestDTO.builder()
                .userId(2L)
                .slotDateTimeList(slotDateTimeList)
                .build();
        final String body = objectMapper.writeValueAsString(slotRequest);
        final MvcResult mvcResult = this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/slots/")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        final String jsonResponse = mockHttpServletResponse.getContentAsString();
        final BasicResponse<Void> basicResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
        assertEquals(HttpStatus.OK.value(), basicResponse.getStatusCode());
        assertEquals("Slot(s) added", basicResponse.getMessage());
    }

    @Test
    public void givenNoFiltersWhenFindMatchedSlotsThenExpectBadRequestResponse() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/slots/")
                                .param("candidateId", "")
                                .param("interviewersId", "1")
                                .flashAttr("slotFilter", new SlotFilterDTO())
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void givenUserAndInterviewersWhenFindMatchedSlotsThenExpectTwoMatches() throws Exception {
        final int expectedSize = 2;
        final MvcResult mvcResult = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/slots/")
                                .param("candidateId", "2")
                                .param("interviewersId", "1")
                                .param("interviewersId", "3")
                                .flashAttr("slotFilter", new SlotFilterDTO())
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        final String jsonResponse = mockHttpServletResponse.getContentAsString();
        final BasicResponse<List<SlotDTO>> basicResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
        assertEquals(HttpStatus.OK.value(), basicResponse.getStatusCode());
        assertEquals(expectedSize, basicResponse.getData().size());
    }

}
