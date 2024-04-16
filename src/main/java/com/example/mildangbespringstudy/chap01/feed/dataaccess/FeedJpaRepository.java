package com.example.mildangbespringstudy.chap01.feed.dataaccess;

import com.example.mildangbespringstudy.chap01.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedJpaRepository extends JpaRepository<Feed, Long> {
}
