package com.example.mildangbespringstudy.chap01.feed.dataaccess;

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
public class FeedDto {
    private Long id;
    private List<SMIDto> SMIList= new ArrayList<>();

    public static FeedDto from(Long id, List<SMIDto> SMIList) {
        return new FeedDto(id, SMIList);
    }
}
