package com.example.mildangbespringstudy.chap02.member.application.dto;

import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberDtoMapper {
    public static MemberDtoV2 toDto(MemberV2 member) {
        return MemberDtoV2.of(member.getId(), member.getEmail(), member.getName());
    }

    public static List<MemberDtoV2> toDtoList(List<MemberV2> members) {
        return members.stream()
                .map(MemberDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
