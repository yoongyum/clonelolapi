package com.clonelol.summoner.api.summonerapi.dto;

import com.clonelol.summoner.entity.SummonerIdInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerIdInfoDto {
    @JsonProperty("id")
    private String summonerId;

    private String accountId;

    @JsonProperty("puuid")
    private String puuId;

    // DTO -> Entity
    public SummonerIdInfo convertToEntity(){
        return SummonerIdInfo.builder()
                .summonerId(this.summonerId)
                .accountId(this.accountId)
                .puuId(this.puuId)
                .build();
    }
}
