package com.clonelol.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Champion {

    @Id
    @Column(name = "champion_id")
    private Long id;

    private String nameEn;
    private String nameKr;
    private String title;

    @Builder
    public Champion(Long id, String nameEn, String nameKr, String title) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameKr = nameKr;
        this.title = title;
    }

    //null 경우 로테이션이 아님
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rotations_id")
    public Rotations rotations;

    //로테이션 셋팅
    public void setRotations(Rotations rotations) {
        this.rotations = rotations;
    }

    //    @OneToOne
//    @JoinColumn(name = "123")
//    private Skill skill;

//    @OneToOne
//    private Stats stats;

//    @OneToXXX
//    private Skin skin;
}