package com.example.mildangbespringstudy.member.dataaccess;

import com.example.mildangbespringstudy.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}