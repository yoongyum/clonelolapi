package com.clonelol.summoner.api.summonerapi.dto;

import com.clonelol.summoner.entity.MatchSummary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchSummaryDto {
    private String puuId;
    private Long champId;
    private Boolean matchResult;    //win: true or lose: false
    private int tier;

    public MatchSummaryDto(String puuId, Long champId, Boolean matchResult, int tier) {
        this.puuId = puuId;
        this.champId = champId;
        this.matchResult = matchResult;
        this.tier = tier;
    }

    public MatchSummary convertToEntity() {
        return MatchSummary.builder()
                .puuId(puuId)
                .champId(champId)
                .matchResult(matchResult)
                .tier(tier)
                .build();
    }
}
