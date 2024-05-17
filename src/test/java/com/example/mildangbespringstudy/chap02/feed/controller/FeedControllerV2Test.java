package com.example.mildangbespringstudy.chap02.feed.controller;

import com.example.mildangbespringstudy.chap02.feed.application.FeedServiceV2;
import com.example.mildangbespringstudy.chap02.feed.application.dto.FeedSearchRequestV2;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.FeedDtoV2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static com.example.mildangbespringstudy.chap02.feed.application.dto.FeedSearchRequestV2.SIZE_VALIDATION_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(FeedControllerV2.class)
public class FeedControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedServiceV2 feedServiceV2;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[성공] 피드를 조회합니다")
    void getFeeds_Success() throws Exception {
        FeedDtoV2 feedDto = FeedDtoTestFixtureV2.createSampleFeedDtoV2();
        List<FeedDtoV2> feeds = Collections.singletonList(feedDto);
        FeedSearchRequestV2 request = FeedSearchRequestV2.builder()
                .memberName("yobs")
                .page(1)
                .size(10)
                .build();

        given(feedServiceV2.searchFeeds(any(FeedSearchRequestV2.class))).willReturn(feeds);

        String expectedResponse = objectMapper.writeValueAsString(feeds);

        mockMvc.perform(MockMvcRequestBuilders.get("/feeds/v2")
                        .param("memberName", request.getMemberName())
                        .param("page", String.valueOf(request.getPage()))
                        .param("size", String.valueOf(request.getSize())))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    @DisplayName("[성공] 존재하는 id로 feed 조회시 Feed를 조회합니다")
    void getFeedById_Success() throws Exception {

        FeedDtoV2 feedDto = FeedDtoTestFixtureV2.createSampleFeedDtoV2();
        given(feedServiceV2.getFeedById(feedDto.getId())).willReturn(feedDto);

        String expectedFeedDto = objectMapper.writeValueAsString(feedDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/feeds/v2/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedFeedDto));

    }

    @Test
    @DisplayName("[실패] Feed가 존재하지 않는 경우 BadRequest를 반환합니다")
    void getFeedById_NotFound() throws Exception {
        given(feedServiceV2.getFeedById(anyLong())).willThrow(new IllegalArgumentException("Feed not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/feeds/v2/{id}", 1L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Feed not found"));
    }

    @Test
    @DisplayName("[실패] 페이지 번호가 유효하지 않음")
    void getFeeds_InvalidPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/feeds/v2")
                        .param("memberName", "yobs")
                        .param("page", "0") // Invalid page number
                        .param("size", "300"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("[실패] 페이지 크기가 유효하지 않음")
    void getFeeds_InvalidSize() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/feeds/v2")
                        .param("memberName", "yobs")
                        .param("page", "1")
                        .param("size", "300")) // Invalid size
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(String.format("size: %s", SIZE_VALIDATION_MESSAGE)));
    }
}