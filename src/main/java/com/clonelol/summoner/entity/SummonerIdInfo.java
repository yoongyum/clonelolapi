package com.clonelol.summoner.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class SummonerIdInfo {
    @Id
    private String summonerId;

    private String accountId;

    private String puuId;

    @Builder
    public SummonerIdInfo(String summonerId, String accountId, String puuId) {
        this.summonerId = summonerId;
        this.accountId = accountId;
        this.puuId = puuId;
    }
}
