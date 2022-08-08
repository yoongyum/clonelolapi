package com.clonelol.summoner.api.summonerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerIdInfoDto {
    @JsonProperty("id")
    public String summonerId;

    public String accountId;

    @JsonProperty("puuid")
    public String puuId;
}
