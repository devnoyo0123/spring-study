package com.example.mildangbespringstudy.chap01.member.controller;

import com.example.mildangbespringstudy.chap01.member.application.MemberService;
import com.example.mildangbespringstudy.chap01.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sample-users")
    public ResponseEntity<String> create100Members() {
        memberService.createMultipleMembers(100);
        return ResponseEntity.ok("100 members created");
    }

    @Operation(summary = "TODO: Implement this API")
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        /**
         * TODO
         */
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }


    @Operation(summary = "TODO: Implement this API")
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        /**
         * TODO
         */
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }
}
