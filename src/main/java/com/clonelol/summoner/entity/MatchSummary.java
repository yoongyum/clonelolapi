package com.clonelol.summoner.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MatchSummary {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String puuId;

    private String champId;
    private Boolean matchResult;    //win: true or lose: false
    private int tier;
}
