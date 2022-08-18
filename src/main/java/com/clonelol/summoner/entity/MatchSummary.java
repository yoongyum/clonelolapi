package com.clonelol.summoner.entity;


import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
@NoArgsConstructor
@Entity
public class MatchSummary {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String puuId;

    private Long champId;
    private Boolean matchResult;    //win: true or lose: false
    private int tier;

    @Builder
    public MatchSummary(String puuId, Long champId, Boolean matchResult, int tier) {
        this.puuId = puuId;
        this.champId = champId;
        this.matchResult = matchResult;
        this.tier = tier;
    }
}
