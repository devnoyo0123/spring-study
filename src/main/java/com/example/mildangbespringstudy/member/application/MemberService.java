package com.example.mildangbespringstudy.member.application;

import com.example.mildangbespringstudy.member.dataaccess.MemberJpaRepository;
import com.example.mildangbespringstudy.member.domain.Member;
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
        /**
         * TODO: MemberJpaRepository를 이용하여 모든 Member를 조회하는 코드를 작성하세요.
         */
        return null;
    }

    public Member getMemberById(Long id) {
        /**
         * TODO: MemberJpaRepository를 이용하여 id에 해당하는 Member를 조회하는 코드를 작성하세요.
         */
        return null;
    }
}

