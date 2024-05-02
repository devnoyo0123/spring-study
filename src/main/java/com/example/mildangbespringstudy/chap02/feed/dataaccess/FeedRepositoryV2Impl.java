package com.example.mildangbespringstudy.chap02.feed.dataaccess;

import com.example.mildangbespringstudy.chap02.feed.application.dto.FeedSearchRequestV2;
import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FeedRepositoryV2Impl implements FeedRepositoryV2 {

    private final FeedCustomRepositoryV2 feedCustomRepositoryV2;
    private final FeedJpaRepositoryV2 feedJpaRepositoryV2;

    @Override
    public void save(FeedV2 feedV2) {
        feedJpaRepositoryV2.save(feedV2);
    }

    @Override
    public List<FeedDtoV2> searchFeeds(FeedSearchRequestV2 request) {
        return feedCustomRepositoryV2.searchFeeds(request);
    }

    @Override
    public Optional<FeedV2> findById(Long id) {
        return feedCustomRepositoryV2.findById(id);
    }
}
