package com.example.mildangbespringstudy.chap02.feed.controller;

import com.example.mildangbespringstudy.chap02.feed.application.FeedServiceV2;
import com.example.mildangbespringstudy.chap02.feed.application.dto.CreateFeedRequestV2;
import com.example.mildangbespringstudy.chap02.feed.application.dto.FeedSearchRequestV2;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.FeedDtoV2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds/v2")
public class FeedControllerV2 {

    private final FeedServiceV2 feedServiceV2;

    @PostMapping
    public ResponseEntity<Void> createFeed(@RequestBody CreateFeedRequestV2 request) {
        feedServiceV2.createFeeds(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FeedDtoV2>> getFeeds(@Valid FeedSearchRequestV2 request) {

        List<FeedDtoV2> feeds = feedServiceV2.searchFeeds(request);
        return ResponseEntity.ok(feeds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedDtoV2> getFeedById(@PathVariable Long id) {

        FeedDtoV2 feed = feedServiceV2.getFeedById(id);
        return ResponseEntity.ok(feed);
    }
}