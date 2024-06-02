package com.fiveis.leasemates.repository;

import com.fiveis.leasemates.domain.vo.UserVO;
import com.fiveis.leasemates.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 생성")
    void createUser() {
        UserVO userVO = UserVO.builder()
                .userNo("1")
                .id("1")
                .password("1234")
                .role("USER")
                .name("1")
                .email("1@naver.com")
                .phoneNumber("010-1111-1111")
                .build();
        userRepository.createUser(userVO);
    }

    @Test
    @DisplayName("유저 id로 유저 있는지 확인")
    void findById() {
        UserVO userVO = UserVO.builder()
                .userNo("2")
                .id("2")
                .password("1234")
                .role("USER")
                .name("2")
                .email("2@naver.com")
                .phoneNumber("010-2222-2222")
                .build();
        userRepository.createUser(userVO);

        Optional<UserVO> foundUser = userRepository.findByUserId(userVO.getId());
        System.out.println("user = " + foundUser.get());
    }

    @Test
    @DisplayName("userNo로 닉네임 찾기")
    void findNameByUserNo() {
        UserVO userVO = UserVO.builder()
                .userNo("3")
                .id("3")
                .password("1234")
                .role("USER")
                .name("3")
                .email("3@naver.com")
                .phoneNumber("010-3333-3333")
                .build();
        userRepository.createUser(userVO);

        String username = userRepository.findNameByUserNo(userVO.getUserNo());
        System.out.println("username = " + username);
    }
}