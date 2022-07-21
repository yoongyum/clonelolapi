package com.clonelol.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class ChampionStats {

    @Id @GeneratedValue
    @Column(name = "champ_stats_id")
    private Long id;    //PK
    // 스텟 정보
    private double hp;   //체력

    @Column(name = "HP_PER_LV")
    private double hpperlevel;   //레벨 당 체력 증가량

    private double mp; //마나
    private double mpperlevel;   //레벨 당 마나 증가량

    private double movespeed;    //이동속도

    private double armor;    //방어력
    private double armorperlevel;//레벨 당 방어력 증가량

    private double spellblock;   //마법저항력
    private double spellblockperlevel;//레벨 당 마법저항력 증가량

    private double attackrange; //사정거리

    private double hpregen; //초당 hp 회복량
    private double hpregenperlevel;//레벨 당 초당 hp 회복량 증가량

    private double mpregen;  //초당 mp 회복량
    private double mpregenperlevel;//레벨 당 초당 mp 회복량 증가량

    private double crit; //치명타 확률
    private double critperlevel; //레벨 당 치명타 확률 증가량

    private double attackdamage; //공격력
    private double attackdamageperlevel;//레벨 당 공격력 증가량

    private double attackspeed;//공격 속도
    private double attackspeedperlevel;//레벨 당 공격속도 증가량
}