package com.clonelol.summoner.api.summonerapi.dto.property;

import lombok.Getter;

import java.util.List;

@Getter
public class Metadata {
    private String dataVersion;
    private String matchId;
    private List<String> participants;
}
