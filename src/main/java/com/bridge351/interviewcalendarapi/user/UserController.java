package com.bridge351.interviewcalendarapi.user;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.user.domain.UserDTO;
import com.bridge351.interviewcalendarapi.user.domain.UserEntity;
import com.bridge351.interviewcalendarapi.user.domain.UserRequestDTO;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class UserController implements UserAPI {

    private final UserService userService;
    private final MessageSource messageSource;

    public UserController(final UserService userService, final MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @Override
    public BasicResponse<List<UserDTO>> getUsers(final int type) {
        final List<UserEntity> users = this.userService.findAllUsersByType(type);
        return BasicResponse.withData(users.stream()
                .map(UserDTO::ofEntity)
                .collect(Collectors.toList()));
    }

    @Override
    public BasicResponse<UserDTO> addUser(final UserRequestDTO userRequest) {
        final UserEntity user = this.userService.addUser(UserEntity.ofDto(userRequest));
        return BasicResponse.withDataAndMessage(
                UserDTO.ofEntity(user),
                this.messageSource.getMessage("user.created", null, Locale.getDefault())
        );
    }

}
