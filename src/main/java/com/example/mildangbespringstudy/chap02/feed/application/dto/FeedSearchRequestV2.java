package com.example.mildangbespringstudy.chap02.feed.application.dto;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
@Setter
@Builder
public class FeedSearchRequestV2 {
    private static final int MAX_SIZE = 200;
    public static final String SIZE_VALIDATION_MESSAGE = "size는 "+MAX_SIZE + "이하이어야 합니다.";

    private String memberName; // 멤버 이름으로 필터링

    @Builder.Default
    @Min(1)
    private Integer page = 1;

    @Builder.Default
    @Max(value = MAX_SIZE, message = SIZE_VALIDATION_MESSAGE)
    private Integer size = 10;

    @Hidden
    public long getOffset() {
        return (long) (max(1, page) - 1) * min(size, MAX_SIZE);
    }
}