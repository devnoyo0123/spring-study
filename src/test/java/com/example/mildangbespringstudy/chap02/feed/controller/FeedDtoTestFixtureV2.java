package com.example.mildangbespringstudy.chap02.feed.controller;

import com.example.mildangbespringstudy.chap02.feed.dataaccess.FeedDtoV2;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.SAIDto;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.SMIDto;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.SUIDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FeedDtoTestFixtureV2 {
    public static FeedDtoV2 createSampleFeedDtoV2() {
        // Create SAIDto instances
        SAIDto sai1 = SAIDto.of(UUID.randomUUID(), 42, 40, false);
        SAIDto sai2 = SAIDto.of(UUID.randomUUID(), 73, 73, true);

        // Create SUIDto instances with SAIDto lists
        SUIDto sui1 = new SUIDto(UUID.randomUUID(), 85, Arrays.asList(sai1));
        SUIDto sui2 = new SUIDto(UUID.randomUUID(), 90, Arrays.asList(sai2));

        // Create SMIDto instances with SUIDto lists
        SMIDto smi1 = new SMIDto(UUID.randomUUID(), 95, Arrays.asList(sui1));
        SMIDto smi2 = new SMIDto(UUID.randomUUID(), 80, Arrays.asList(sui2));

        // Create FeedDtoV2 instance with SMIDto list
        return FeedDtoV2.from(1L, "yobs", 1L, Arrays.asList(smi1, smi2));
    }

    public static List<FeedDtoV2> createSampleFeedDtoV2List() {
        return List.of(createSampleFeedDtoV2());
    }
}
