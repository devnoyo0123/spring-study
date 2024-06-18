package com.example.mildangbespringstudy.chap02.member.controller;

import com.example.mildangbespringstudy.chap02.member.application.MemberServiceV2;
import com.example.mildangbespringstudy.chap02.member.application.dto.MemberDtoMapper;
import com.example.mildangbespringstudy.chap02.member.application.dto.MemberDtoV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members/v2")
public class MemberControllerV2 {

    private final MemberDtoMapper memberDtoMapper;

    private final MemberServiceV2 memberServiceV2;

    @PostMapping("/sample-users")
    public ResponseEntity<String> create100Members() {
        memberServiceV2.createMultipleMembers(100);
        return ResponseEntity.ok("100 members created");
    }

    @GetMapping
    public ResponseEntity<List<MemberDtoV2>> findAllMembers() {

        List<MemberDtoV2> memberV2s = memberServiceV2.findAllMembers();
        return ResponseEntity.ok(memberV2s);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MemberDtoV2> getMemberById(@PathVariable Long id) {

        MemberDtoV2 memberdtoV2 = memberServiceV2.getMemberById(id);
        return ResponseEntity.ok(memberdtoV2);
    }
}
