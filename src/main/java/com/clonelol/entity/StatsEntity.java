package com.clonelol.entity;

import lombok.Builder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StatsEntity {

    @Id @GeneratedValue
    private Long id;
    @Embedded
    private Stats stats;
    @Embedded
    private Info info;

    @Builder
    public StatsEntity(Stats stats, Info info){
        this.stats = stats;
        this.info = info;
    }
}
