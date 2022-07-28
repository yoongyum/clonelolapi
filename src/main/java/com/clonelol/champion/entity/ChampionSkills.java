package com.clonelol.champion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ChampionSkills {

    @Id
    @Column(name = "champion_skills_id")
    private String id;    //PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "champion_id")
    private Champion champion;

    private String name;

    private String description;

    //    @Column(name = "max_rank")
    private int maxRank;

    //    @Column(name = "tool_tip")
    private String tooltip;

    //    @Column(name = "cool_down_burn")
    private String coolDownBurn;    //레벨당 쿨타임 변화

    //    @Column(name = "cost_burns")
    private String costBurns;   //레벨당 마나 소모량 변화

    //    @Column(name = "range_burn")
    private String rangeBurn;   //레벨당 스킬 사거리 변화

    public void addChampion(Champion champion){
        this.champion = champion;
    }
}