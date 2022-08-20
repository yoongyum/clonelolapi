package com.clonelol.summoner.api.summonerapi.dto;

import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.Participant;
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

    public MatchSummaryDto(Participant participant, int tier) {
        this.puuId = participant.getPuuid();
        this.champId = participant.getChampionId();
        this.matchResult = participant.getWin();
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
