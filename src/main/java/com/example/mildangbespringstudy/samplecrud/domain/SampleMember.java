package com.example.mildangbespringstudy.samplecrud.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sample_member")
public class SampleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Team team;

    public SampleMember(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public static SampleMember of(String name) {
        return new SampleMember(null, name, null);
    }

    public void setName(String name) {
        this.name = name;
    }
}