package com.example.mildangbespringstudy.feed.dataaccess;


import com.example.mildangbespringstudy.feed.domain.Feed;
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

    public static FeedDto of(Feed feed) {
        return new FeedDto(feed.getId(), new ArrayList<>());
    }
}
