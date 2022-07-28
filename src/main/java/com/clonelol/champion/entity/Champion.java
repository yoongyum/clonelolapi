package com.clonelol.champion.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
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

    //null 경우 로테이션이 아님
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rotations_id")
    public Rotations rotations;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "champion_stats_id")
    private ChampionStats champStats;

    @OneToMany(mappedBy = "champion")
    private List<ChampionSkills> skills = new ArrayList<>();

    @Builder
    public Champion(Long id, String nameEn, String nameKr, String title, String portrait, ChampionStats championStats, List<ChampionSkills> skills) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameKr = nameKr;
        this.title = title;
        this.portrait = portrait;
        this.champStats = championStats;
        setSkills(skills);
    }

    private void setSkills(List<ChampionSkills> skills){
        for (ChampionSkills skill : skills) {
            skills.add(skill);
        }
    }

    //로테이션 셋팅
    public void setRotations(Rotations rotations) {
        this.rotations = rotations;
        this.rotations.getFreeChampions().add(this);
    }

    //로테이션 연결 해제
    public void deleteRotations(){
        this.rotations = null;
    }

}