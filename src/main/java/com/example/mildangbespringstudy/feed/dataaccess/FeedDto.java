package com.example.mildangbespringstudy.feed.dataaccess;


import com.example.mildangbespringstudy.domain.StudyModuleInstance;
import com.example.mildangbespringstudy.domain.StudyUnitInstance;
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
  private List<SMIDto> SMIList = new ArrayList<>();

  public static FeedDto of(Feed feed) {
    List<SMIDto> smiDtoList = feed.getStudyModuleInstanceList().stream().map(
        studyModuleInstance -> new SMIDto(studyModuleInstance.getId(),
            studyModuleInstance.getCompleteRate(),
            studyModuleInstance.getStudyUnitInstanceList().stream().map(
                studyUnitInstance -> new SUIDto(studyUnitInstance.getId(),
                    studyUnitInstance.getCompleteRate(),
                    studyUnitInstance.getStudyActivityInstanceList().stream().map(
                        studyActivityInstance -> new SAIDto(studyActivityInstance.getId(),
                            studyActivityInstance.getAnswer(),
                            studyActivityInstance.getUserAnswer(),
                            studyActivityInstance.getIsCorrect())).toList())).toList())).toList();
    return new FeedDto(feed.getId(), smiDtoList);
  }
}
