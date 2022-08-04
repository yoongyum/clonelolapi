package com.clonelol.summoner.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = PROTECTED)
public class SummonerSimpleInfo implements Persistable<String> {

    @Id
    private String summonerId;
    private String queueType;
    private String tier;
    private String grade;

    @CreatedDate
    private LocalDateTime firstInsert;

    @Builder
    public SummonerSimpleInfo(String summonerId, String queueType, String tier, String grade) {
        this.summonerId = summonerId;
        this.queueType = queueType;
        this.tier = tier;
        this.grade = grade;
    }

    @Override
    public String getId() {
        return summonerId;
    }

    @Override
    public boolean isNew() {
        return firstInsert == null;
    }
}
