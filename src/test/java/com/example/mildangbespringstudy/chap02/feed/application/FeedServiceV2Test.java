package com.example.mildangbespringstudy.chap02.feed.application;
import com.example.mildangbespringstudy.chap02.feed.application.dto.CreateFeedRequestV2;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.FeedRepositoryV2;
import com.example.mildangbespringstudy.chap02.member.dataaccess.MemberJpaRepositoryV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FeedServiceV2Test {
    @Mock
    private FeedRepositoryV2 feedRepo;

    @Mock
    private MemberJpaRepositoryV2 memberRepo;

    @InjectMocks
    private FeedServiceV2 sut;

    @Test
    @DisplayName("피드를 생성합니다.")
    void createFeeds() {
        // Given
        MemberV2 mockMember = new MemberV2(1L, "user1@example.com", "password1", "User1", null);
        given(memberRepo.findById(1L)).willReturn(Optional.of(mockMember));

        CreateFeedRequestV2 request = new CreateFeedRequestV2(1, 1);

        // When
        sut.createFeeds(request);

        // Then
        verify(feedRepo).save(any());
    }

    @Test
    @DisplayName("피드를 생성시 멤버를 찾지 못한 경우 에러를 반환합니다.")
    void createFeedsFailWhenMemberNotFound() {
        // Given
        given(memberRepo.findById(1L)).willThrow(new RuntimeException("Member not found with id: " + 1L));

        CreateFeedRequestV2 request = new CreateFeedRequestV2(1, 1);

        // When & Then
        Assertions.assertThrows(RuntimeException.class, () -> {
            sut.createFeeds(request);
        });
    }

    @Test
    @DisplayName("피드 조회 시, 피드를 찾지 못한 경우 에러를 반환합니다.")
    void getFeedByIdFailsWhenFeedNotFound() {
        // Given
        given(feedRepo.findById(1L)).willThrow(new RuntimeException("Feed not found with id: " + 1L));

        // When & Then
        Assertions.assertThrows(RuntimeException.class, () -> {
            sut.getFeedById(1L);
        });
    }
}
