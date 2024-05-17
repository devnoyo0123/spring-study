package com.example.mildangbespringstudy.chap02.member.application;

import com.example.mildangbespringstudy.chap02.member.dataaccess.MemberJpaRepositoryV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceV2Test {

    @InjectMocks
    private MemberServiceV2 sut;

    @Mock
    private MemberJpaRepositoryV2 memberRepository;

    @Test
    void getMemberById_Success() {
        // Given
        MemberV2 mockMember = new MemberV2(1L, "user1@example.com", "password1", "User1", null);

        given(memberRepository.findById(1L)).willReturn(Optional.of(mockMember));

        // When
        MemberV2 foundMember = sut.getMemberById(1L);

        // Then
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getId()).isEqualTo(mockMember.getId());
        assertThat(foundMember.getEmail()).isEqualTo(mockMember.getEmail());
    }

    @Test
    void getMemberById_Failure() {
        // Given
        given(memberRepository.findById(2L)).willReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> sut.getMemberById(2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Member not found with id: 2");
    }
}