package com.example.mildangbespringstudy.chap01.feed.application.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
@Setter
@Builder
public class FeedSearchRequest {
    private static final int MAX_SIZE = 200;

    @Hidden
    private String memberName; // 멤버 이름으로 필터링

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    @Hidden
    public long getOffset() {
        return (long) (max(1, page) - 1) * min(size, MAX_SIZE);
    }
}