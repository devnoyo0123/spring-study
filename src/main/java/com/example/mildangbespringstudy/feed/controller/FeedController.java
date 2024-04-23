package com.example.mildangbespringstudy.feed.controller;


import com.example.mildangbespringstudy.feed.application.FeedService;
import com.example.mildangbespringstudy.feed.application.dto.CreateFeedRequest;
import com.example.mildangbespringstudy.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.feed.dataaccess.FeedDto;
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

    @Operation(summary = "feed 생성 API입니다.")
    @PostMapping
    public ResponseEntity<Void> createFeed(@RequestBody CreateFeedRequest request) {
        feedService.createFeeds(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "feed 조회 API입니다.")
    @GetMapping
    public ResponseEntity<List<FeedDto>> getFeeds(FeedSearchRequest request) {
        List<FeedDto> feeds = feedService.searchFeeds(request);
        return ResponseEntity.ok(feeds);
    }

    @Operation(summary = "feed 단건 조회 API입니다.")
    @GetMapping("/{id}")
    public ResponseEntity<FeedDto> getFeedById(@PathVariable Long id) {
        FeedDto feed = feedService.getFeedById(id);
        return ResponseEntity.ok(feed);
    }
}