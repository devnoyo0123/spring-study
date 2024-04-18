package com.example.mildangbespringstudy.feed.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to create feeds for multiple members")
public record CreateFeedRequest(
        @Schema(description = "Number of feeds to create for each member", example = "5", required = true)
        int numberOfFeed,

        @Schema(description = "Maximum member ID to which feeds will be created", example = "100", required = true)
        int maxMemberId
) {}
