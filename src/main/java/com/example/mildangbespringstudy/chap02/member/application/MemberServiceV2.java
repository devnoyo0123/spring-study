package com.example.mildangbespringstudy.chap02.member.application;

import com.example.mildangbespringstudy.chap02.member.dataaccess.MemberJpaRepositoryV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceV2 {

    private final MemberJpaRepositoryV2 memberRepository;

    @Transactional
    public void createMultipleMembers(int count) {
        List<MemberV2> memberV2s = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            memberV2s.add(new MemberV2(null, "user" + i + "@example.com", "password" + i, "User" + i, null));
        }
        memberRepository.saveAll(memberV2s);
    }

    public List<MemberV2> findAllMembers() {
        return memberRepository.findAll();
    }

    public MemberV2 getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
    }
}
