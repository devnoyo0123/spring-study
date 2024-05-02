package com.example.mildangbespringstudy.chap02.feed.dataaccess;

import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedJpaRepositoryV2 extends JpaRepository<FeedV2, Long> {
}
