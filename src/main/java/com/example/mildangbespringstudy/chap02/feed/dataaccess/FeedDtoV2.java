package com.example.mildangbespringstudy.chap02.feed.dataaccess;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedDtoV2 {
    private Long id;
    private String memberName;
    private Long memberId;
    private List<SMIDto> SMIList= new ArrayList<>();

    public static FeedDtoV2 from(Long id, String memberName, Long memberId, List<SMIDto> SMIList) {
        return FeedDtoV2.builder()
                .id(id)
                .memberName(memberName)
                .memberId(memberId)
                .SMIList(SMIList)
                .build();
    }
}
