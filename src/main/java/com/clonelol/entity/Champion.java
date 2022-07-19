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

    private String nameEn;  //챔피언 영문명
    private String nameKr;  //챔피언 명칭(Regions에 따라 언어 다름 현재: ko_KR)
    private String title;   //챔피언 타이틀
    private String portrait;    //챔피언 초상화

    @Builder
    public Champion(Long id, String nameEn, String nameKr, String title, String portrait) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameKr = nameKr;
        this.title = title;
        this.portrait = portrait;
    }

    //null 경우 로테이션이 아님
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rotations_id")
    public Rotations rotations;

    //로테이션 셋팅
    public void setRotations(Rotations rotations) {
        this.rotations = rotations;
        this.rotations.getFreeChampions().add(this);
    }

    //로테이션 연결 해제
    public void deleteRotations(){
        this.rotations = null;
    }

    //    @OneToOne
//    @JoinColumn(name = "123")
//    private Skill skill;

//    @OneToOne
//    private Stats stats;

//    @OneToXXX
//    private Skin skin;
}