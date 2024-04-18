package com.example.mildangbespringstudy.feed.dataaccess;


import com.example.mildangbespringstudy.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedJpaRepository extends JpaRepository<Feed, Long> {
}
