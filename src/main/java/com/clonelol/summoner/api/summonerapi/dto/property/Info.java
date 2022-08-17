package com.clonelol.summoner.api.summonerapi.dto.property;

import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.Participant;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    private Long gameCreation;
    private Long gameDuration;
    private Long gameEndTimestamp;
    private Long gameId;
    private String gameMode;
    private String gameName;
    private Long gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private List<Participant> participants;
    private String platformId;
    private int queueId;
    private List<Team> teams;
    private String tournamentCode;
}
