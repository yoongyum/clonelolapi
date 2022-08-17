package com.clonelol.summoner.api.summonerapi.dto.property.infoProperty;

import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.teamProperty.Ban;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.teamProperty.Objectives;

import java.util.List;

public class Team {
    private List<Ban> bans;
    private Objectives objectives;
    private int teamId;
    private Boolean win;
}
