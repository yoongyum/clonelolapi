package com.clonelol.summoner.api.summonerapi.dto;

import com.clonelol.summoner.entity.Rank;
import com.clonelol.summoner.entity.Tier;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LeagueEntryDto {

    private String leagueId;
    private String summonerId;
    private String summonerName;
    private String queueType;
    private Tier tier;
    private Rank rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean hotStreak;
    private boolean veteran;
    private boolean freshBlood;
    private boolean inactive;

    public int getTierScore(){
        return tier.getValue();
    }

    public int getRankScore(){
        return rank.getValue();
    }
}
