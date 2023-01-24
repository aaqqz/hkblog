package com.hkblog.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkblog.api.domain.Session;
import com.hkblog.api.domain.User;
import com.hkblog.api.repository.SessionRepository;
import com.hkblog.api.repository.UserRepository;
import com.hkblog.api.request.Login;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공")
    void test1() throws Exception {
        // given
        userRepository.save(User.builder()
                .name("hk")
                .email("sswwx1@naver.com")
                .password("1234")
                .build());

        Login login = Login.builder()
                .email("sswwx1@naver.com")
                .password("1234")
                .build();


        String json = objectMapper.writeValueAsString(login);

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("로그인 성공후 세션 1개 생성")
    void test2() throws Exception {
        // given
        User user = userRepository.save(User.builder()
                .name("hk")
                .email("sswwx1@naver.com")
                .password("1234")
                .build());

        Login login = Login.builder()
                .email("sswwx1@naver.com")
                .password("1234")
                .build();


        String json = objectMapper.writeValueAsString(login);

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, user.getSessions().size());
    }

    @Test
    @Transactional
    @DisplayName("로그인 성공후 세션 응답")
    void test3() throws Exception {
        // given
        User user = userRepository.save(User.builder()
                .name("hk")
                .email("sswwx1@naver.com")
                .password("1234")
                .build());

        Login login = Login.builder()
                .email("sswwx1@naver.com")
                .password("1234")
                .build();


        String json = objectMapper.writeValueAsString(login);

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", Matchers.notNullValue()))
                .andDo(print());

    }

    @Test
    @DisplayName("로그인 후 권한이 필요한 페이지 접속한다 /foo")
    void test4() throws Exception {
        // given
        User user = User.builder()
                .name("hk")
                .email("sswwx1@naver.com")
                .password("1234")
                .build();
        Session session = user.addSession();
        userRepository.save(user);
        System.out.println(session.toString());
        // expected
        mockMvc.perform(get("/foo")
                        .header("Authorization", session.getAccessToken())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 후 검증되지 않은 세션값으로 권한이 필요한 페이지에 접속할 수 없다.")
    void test5() throws Exception {
        // given
        User user = User.builder()
                .name("hk")
                .email("sswwx1@naver.com")
                .password("1234")
                .build();
        Session session = user.addSession();
        userRepository.save(user);
        System.out.println(session.toString());
        // expected
        mockMvc.perform(get("/foo")
                        .header("Authorization", session.getAccessToken() + "-other")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}