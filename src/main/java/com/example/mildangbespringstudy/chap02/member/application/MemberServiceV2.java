package com.example.mildangbespringstudy.chap02.member.application;

import com.example.mildangbespringstudy.chap02.member.application.dto.MemberDtoV2;
import com.example.mildangbespringstudy.chap02.member.dataaccess.MemberJpaRepositoryV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void createMember(String email, String password, String name) {
        MemberV2 memberV2 = new MemberV2(null, email, password, name, null);
        memberRepository.save(memberV2);
    }


    public List<MemberDtoV2> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(item -> MemberDtoV2.of(item.getId(), item.getEmail(), item.getName()))
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "localCache",  cacheResolver = "cacheResolver")
    public MemberDtoV2 getMemberById(Long id) {
        MemberV2 memberV2 = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
        return MemberDtoV2.of(memberV2.getId(), memberV2.getEmail(), memberV2.getName());
    }
}
