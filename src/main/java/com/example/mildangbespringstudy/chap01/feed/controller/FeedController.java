package com.example.mildangbespringstudy.chap01.feed.controller;

import com.example.mildangbespringstudy.chap01.feed.application.FeedService;
import com.example.mildangbespringstudy.chap01.feed.application.dto.CreateFeedRequest;
import com.example.mildangbespringstudy.chap01.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.chap01.feed.dataaccess.FeedDto;
import com.example.mildangbespringstudy.chap01.feed.domain.Feed;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds")
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    public ResponseEntity<Void> createFeed(@RequestBody CreateFeedRequest request) {
        feedService.createFeeds(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "TODO: Implement this API")
    @GetMapping
    public ResponseEntity<List<FeedDto>> getFeeds(FeedSearchRequest request) {
        /**
         * TODO
         */
        List<FeedDto> feeds = feedService.searchFeeds(request);
        return ResponseEntity.ok(feeds);
    }

    @Operation(summary = "TODO: Implement this API")
    @GetMapping("/{id}")
    public ResponseEntity<FeedDto> getFeedById(@PathVariable Long id) {
        /**
         * TODO
         */
        FeedDto feed = feedService.getFeedById(id);
        return ResponseEntity.ok(feed);
    }
}