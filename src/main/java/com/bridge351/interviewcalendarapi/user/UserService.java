package com.bridge351.interviewcalendarapi.user;

import com.bridge351.interviewcalendarapi.commons.exceptions.BusinessException;
import com.bridge351.interviewcalendarapi.user.domain.UserEntity;
import com.bridge351.interviewcalendarapi.user.enums.UserTypeEnum;
import com.bridge351.interviewcalendarapi.user.exception.UserAlreadyExistsException;
import com.bridge351.interviewcalendarapi.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity addUser(final UserEntity user) {
        this.userRepository.findUserByEmail(user.getEmail())
                .ifPresent(found -> {
                    throw new UserAlreadyExistsException();
                });
        return this.userRepository.save(user);
    }

    public List<UserEntity> findAllUsersByType(final int type) {
        validateUserType(type);
        final List<UserEntity> users = this.userRepository.findAllUsersByType(type);
        validateUsersBeenFound(users);
        return users;
    }

    private void validateUserType(final int type) {
        if (Objects.isNull(UserTypeEnum.getByType(type))) {
            throw new BusinessException("user.validation.invalid.type");
        }
    }

    private void validateUsersBeenFound(final List<UserEntity> users) {
        if (CollectionUtils.isEmpty(users)) {
            throw new UserNotFoundException("user.exception.users.not.found");
        }
    }

    public void findUserById(final Long id) throws UserNotFoundException {
        this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

}
