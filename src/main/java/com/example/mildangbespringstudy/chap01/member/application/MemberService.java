package com.example.mildangbespringstudy.chap01.member.application;

import com.example.mildangbespringstudy.chap01.member.dataaccess.MemberJpaRepository;
import com.example.mildangbespringstudy.chap01.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberJpaRepository memberRepository;

    @Transactional
    public void createMultipleMembers(int count) {
        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            members.add(new Member(null, "user" + i + "@example.com", "password" + i, "User" + i, null));
        }
        memberRepository.saveAll(members);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }
}
