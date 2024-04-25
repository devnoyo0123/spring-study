package com.example.mildangbespringstudy.chap01.feed.dataaccess;

import com.example.mildangbespringstudy.chap01.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.chap01.feed.domain.Feed;

import java.util.List;
import java.util.Optional;

public interface FeedRepository {

    void save(Feed feed);

    List<FeedDto> searchFeeds(FeedSearchRequest request);

    Optional<Feed> findById(Long id);
}
