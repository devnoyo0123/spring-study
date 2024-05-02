package com.example.mildangbespringstudy.chap02.feed.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedDtoV2 {
    private Long id;
    private List<SMIDto> SMIList= new ArrayList<>();

    public static FeedDtoV2 from(Long id, List<SMIDto> SMIList) {
        return new FeedDtoV2(id, SMIList);
    }
}
