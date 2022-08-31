package com.bridge351.interviewcalendarapi.user;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.user.domain.UserDTO;
import com.bridge351.interviewcalendarapi.user.domain.UserRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api(value = "User Service", tags = "User Service")
public interface UserAPI {

    @ApiOperation(value = "List of Users", nickname = "getUsers", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = UserDTO.class, responseContainer = "list"),
            @ApiResponse(code = 200, message = "No Users Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping(value = "/users/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<List<UserDTO>> getUsers(@ApiParam(value = "User Type", required = true, example = "1")
                                          @RequestParam final int type);

    @ApiOperation(value = "Add User", nickname = "addUser", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User created", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unable to add User"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping(value = "/users/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<UserDTO> addUser(@ApiParam(value = "User to create") @Valid @RequestBody final UserRequestDTO userRequest);

}
