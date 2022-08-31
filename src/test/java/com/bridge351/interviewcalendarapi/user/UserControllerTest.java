package com.bridge351.interviewcalendarapi.user;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.user.domain.UserDTO;
import com.bridge351.interviewcalendarapi.user.domain.UserEntity;
import com.bridge351.interviewcalendarapi.user.domain.UserRequestDTO;
import com.bridge351.interviewcalendarapi.user.enums.UserTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * <p>Class that represents some Behavior Tests mocking everything with Mockito always expecting desired results.</p>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private MessageSource messageSource;

    @Test
    public void givenCandidateTypewhenGetUsersThenValidateResponse() {
        final int userCandidateType = UserTypeEnum.CANDIDATE.getType();
        final List<UserEntity> usersExpected = new ArrayList<>();
        final UserEntity user = UserEntity.builder()
                .name("trein")
                .build();
        usersExpected.add(user);
        when(this.userService.findAllUsersByType(userCandidateType)).thenReturn(usersExpected);
        final BasicResponse<List<UserDTO>> response = this.userController.getUsers(userCandidateType);
        verify(this.userService).findAllUsersByType(userCandidateType);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(user.getName(), response.getData().get(0).getName());
    }

    @Test
    public void givenUserRequestWhenAddUsersThenValidateResponse() {
        final UserRequestDTO userRequest = UserRequestDTO.builder()
                .name("trein")
                .email("trein@mail.com")
                .type(UserTypeEnum.CANDIDATE.getType())
                .build();
        final UserEntity ofDto = UserEntity.ofDto(userRequest);
        final UserEntity expectedUser = UserEntity.builder()
                .id(1L)
                .build();
        final String expectedMsg = "User created";
        when(this.userService.addUser(ofDto)).thenReturn(expectedUser);
        when(this.messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn(expectedMsg);
        final BasicResponse<UserDTO> response = this.userController.addUser(userRequest);
        verify(this.userService).addUser(ofDto);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(expectedMsg, response.getMessage());
        assertEquals(expectedUser.getId(), response.getData().getId());
    }

}
