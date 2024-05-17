package com.example.mildangbespringstudy.chap02.member.controller;

import com.example.mildangbespringstudy.chap02.member.application.MemberServiceV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberControllerV2.class)
class MemberControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberServiceV2 service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[성공] 존재하는 회원을 조회합니다")
    void getMemberById_Success() throws Exception {
        Long id = 1L;
        MemberV2 member = new MemberV2(id, "John Doe", "1234","john.doe@example.com", new ArrayList<>());

        given(service.getMemberById(anyLong())).willReturn(member);

        mockMvc.perform(MockMvcRequestBuilders.get("/members/v2/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(member)));
    }

    @Test
    @DisplayName("[실패] 존재하지 않은 회원인 경우 BadRequest 상태를 반환합니다")
    void getMemberById_Failure() throws Exception {
        Long id = 1L;

        given(service.getMemberById(anyLong())).willThrow(new IllegalArgumentException("Member not found with id " + id));

        mockMvc.perform(MockMvcRequestBuilders.get("/members/v2/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}