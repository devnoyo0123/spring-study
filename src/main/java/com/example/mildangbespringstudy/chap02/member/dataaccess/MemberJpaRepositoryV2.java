package com.example.mildangbespringstudy.chap02.member.dataaccess;

import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepositoryV2 extends JpaRepository<MemberV2, Long>{
}
