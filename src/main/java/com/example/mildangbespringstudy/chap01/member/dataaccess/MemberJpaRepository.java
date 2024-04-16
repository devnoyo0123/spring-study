package com.example.mildangbespringstudy.chap01.member.dataaccess;

import com.example.mildangbespringstudy.chap01.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long>{
}
