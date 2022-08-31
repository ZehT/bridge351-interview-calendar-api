package com.bridge351.interviewcalendarapi.user;

import com.bridge351.interviewcalendarapi.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllUsersByType(final int type);

    Optional<UserEntity> findUserByEmail(final String email);

}
