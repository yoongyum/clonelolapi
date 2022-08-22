package com.clonelol.summoner.api.summonerapi.dto.property.infoProperty;

import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.teamProperty.BanDto;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.teamProperty.Objectives;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Team {

    @JsonProperty("bans")
    private List<BanDto> banDtos;
    private Objectives objectives;
    private int teamId;
    private Boolean win;
}
