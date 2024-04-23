package com.example.mildangbespringstudy.feed.dataaccess;


import com.example.mildangbespringstudy.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.feed.domain.Feed;

import java.util.List;
import java.util.Optional;

public interface FeedRepository {

    void save(Feed feed);

    List<FeedDto> searchFeeds(FeedSearchRequest request);

    Optional<FeedDto> findById(Long id);
}
