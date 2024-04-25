package com.example.mildangbespringstudy.chap01.feed.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SAIDto {
    private UUID id;
    private Integer answer;
    private Integer userAnswer;
    private Boolean isCorrect;

    public static SAIDto of(UUID id, Integer answer, Integer userAnswer, Boolean isCorrect) {
        return new SAIDto(id, answer, userAnswer, isCorrect);
    }
}
