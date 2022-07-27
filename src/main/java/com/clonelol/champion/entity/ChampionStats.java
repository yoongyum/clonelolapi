package com.clonelol.champion.entity;

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
    @Column(name = "champion_stats_id")
    private Long id;    //PK
    // 스텟 정보
    private double hp;   //체력

    @Column(name = "HP_PER_LV")
    private double hpperlevel;   //레벨 당 체력 증가량

    private double mp; //마나
    @Column(name = "MP_PER_LV")
    private double mpperlevel;   //레벨 당 마나 증가량

    @Column(name = "MOVE_SPEED")
    private double movespeed;    //이동속도

    private double armor;    //방어력

    @Column(name = "ARMOR_PER_LV")
    private double armorperlevel;//레벨 당 방어력 증가량

    @Column(name = "SPELL_BLOCK")
    private double spellblock;   //마법저항력

    @Column(name = "SPELL_BLOCK_PER_LV")
    private double spellblockperlevel;//레벨 당 마법저항력 증가량

    @Column(name = "ATTACK_RANGE")
    private double attackrange; //사정거리

    @Column(name = "HP_REGEN")
    private double hpregen; //초당 hp 회복량

    @Column(name = "HP_REGEN_PER_LV")
    private double hpregenperlevel;//레벨 당 초당 hp 회복량 증가량

    @Column(name = "MP_REGEN")
    private double mpregen;  //초당 mp 회복량

    @Column(name = "MP_REGEN_PER_LV")
    private double mpregenperlevel;//레벨 당 초당 mp 회복량 증가량

    private double crit; //치명타 확률

    @Column(name = "CRIT_PER_LV")
    private double critperlevel; //레벨 당 치명타 확률 증가량

    @Column(name = "ATTACK_DAMAGE")
    private double attackdamage; //공격력

    @Column(name = "ATTACK_DAMAGE_PER_LV")
    private double attackdamageperlevel;//레벨 당 공격력 증가량

    @Column(name = "ATTACK_SPEED")
    private double attackspeed;//공격 속도

    @Column(name = "ATTACK_SPEED_PER_LV")
    private double attackspeedperlevel;//레벨 당 공격속도 증가량
}