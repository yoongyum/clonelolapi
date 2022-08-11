package com.clonelol.summoner.api.summonerapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SummonerApiDto {

    private String summonerId;
    @JsonProperty("puuid")
    private String pUuid;
}
