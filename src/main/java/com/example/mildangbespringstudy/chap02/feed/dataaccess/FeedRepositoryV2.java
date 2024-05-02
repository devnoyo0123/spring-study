package com.example.mildangbespringstudy.chap02.feed.dataaccess;

import com.example.mildangbespringstudy.chap02.feed.application.dto.FeedSearchRequestV2;
import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;

import java.util.List;
import java.util.Optional;

public interface FeedRepositoryV2 {

    void save(FeedV2 feedV2);

    List<FeedDtoV2> searchFeeds(FeedSearchRequestV2 request);

    Optional<FeedV2> findById(Long id);
}
