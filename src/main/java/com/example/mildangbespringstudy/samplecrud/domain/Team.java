package com.example.mildangbespringstudy.samplecrud.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member2> member2s = new ArrayList<>();

    // 팀에 멤버를 추가하는 편의 메서드
    public void addMember(Member2 member2) {
        member2s.add(member2);
        member2.setTeam(this);
    }

    // 팀에서 멤버를 제거하는 편의 메서드
    public void removeMember(Member2 member2) {
        member2s.remove(member2);
        member2.setTeam(null);
    }

    public void setName(String name) {
        this.name = name;
    }
}
