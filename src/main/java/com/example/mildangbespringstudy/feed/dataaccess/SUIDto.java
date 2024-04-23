package com.example.mildangbespringstudy.feed.dataaccess;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SUIDto {
    private UUID id;
    private Integer completeRate;
    private List<SAIDto> SAIList = new ArrayList<>();

    public static SUIDto of(UUID id, Integer completeRate, List<SAIDto> SAIList) {
        return new SUIDto(id, completeRate, SAIList);
    }
}