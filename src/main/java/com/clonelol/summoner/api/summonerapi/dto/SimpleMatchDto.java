package com.clonelol.summoner.api.summonerapi.dto;

import com.clonelol.summoner.api.summonerapi.dto.property.Info;
import com.clonelol.summoner.api.summonerapi.dto.property.Metadata;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleMatchDto {

    private Metadata metadata;

    private Info info;

}
