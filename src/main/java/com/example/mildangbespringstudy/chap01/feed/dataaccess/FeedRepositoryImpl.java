package com.example.mildangbespringstudy.chap01.feed.dataaccess;

import com.example.mildangbespringstudy.chap01.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.chap01.feed.domain.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FeedRepositoryImpl implements FeedRepository{

    private final FeedCustomRepository feedCustomRepository;
    private final FeedJpaRepository feedJpaRepository;

    @Override
    public void save(Feed feed) {
        feedJpaRepository.save(feed);
    }

    @Override
    public List<FeedDto> searchFeeds(FeedSearchRequest request) {
        return feedCustomRepository.searchFeeds(request);
    }

    @Override
    public Optional<Feed> findById(Long id) {
        return feedCustomRepository.findById(id);
    }
}
