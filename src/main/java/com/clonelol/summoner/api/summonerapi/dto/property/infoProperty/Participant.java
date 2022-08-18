package com.clonelol.summoner.api.summonerapi.dto.property.infoProperty;

import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.participantProperty.Perks;
import lombok.Getter;

@Getter
public class Participant {
    private int assist;
    private int baronKills;
    private int bountyLevel;
    private int champExperience;
    private int champLevel;
    private Long championId;
    private String championName;

    /*
    This field is currently only utilized for Kayn's transformations.
    (Legal values: 0 - None, 1 - Slayer, 2 - Assassin)
    */
    private int championTransform;
    private int consumablesPurchased;
    private int damageDealtToBuildings;
    private int damageDealtToObjectives;
    private int damageDealtToTurrets;
    private int damageSelfMitigated;
    private int deaths;
    private int detectorWardsPlaced;
    private int doubleKills;
    private int dragonKills;
    private Boolean firstBloodAssist;
    private Boolean firstBloodKill;
    private Boolean firstTowerAssist;
    private Boolean firstTowerKill;
    private Boolean gameEndedInEarlySurrender;
    private Boolean gameEndedInSurrender;
    private int goldEarned;
    private int goldSpent;

    /*
    Both individualPosition and teamPosition are computed by the game server
    and are different versions of the most likely position played by a player.
    The individualPosition is the best guess for which position the player actually played in isolation of anything else.
    The teamPosition is the best guess for which position the player actually played if we add the constraint that each team must have one top player, one jungle, one middle, etc.
    Generally the recommendation is to use the teamPosition field over the individualPosition field.
    */
    private String individualPosition;
    private int inhibitorKills;
    private int inhibitorTakedowns;
    private int inhibitorsLost;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int itemsPurchased;
    private int killingSprees;
    private int kills;
    private String lane;
    private int largestCriticalStrike;
    private int largestKillingSpree;
    private int largestMultiKill;
    private int longestTimeSpentLiving;
    private int magicDamageDealt;
    private int magicDamageDealtToChampions;
    private int magicDamageTaken;
    private int neutralMinionsKilled;
    private int nexusKills;
    private int nexusTakedowns;
    private int nexusLost;
    private int objectivesStolen;
    private int objectivesStolenAssists;
    private int participantId;
    private int pentaKills;
    private Perks perks;
    private int physicalDamageDealt;
    private int physicalDamageDealtToChampions;
    private int physicalDamageTaken;
    private int profileIcon;
    private String puuid;
    private int quadraKills;
    private String riotIdName;
    private String riotIdTagline;
    private String role;
    private int sightWardsBoughtInGame;
    private int spell1Casts;
    private int spell2Casts;
    private int spell3Casts;
    private int spell4Casts;
    private int summoner1Casts;
    private int summoner1Id;
    private int summoner2Casts;
    private int summoner2Id;
    private String summonerId;
    private int summonerLevel;
    private String summonerName;
    private Boolean teamEarlySurrendered;
    private int teamId;

    /*
    Both individualPosition and teamPosition are computed by the game server
    and are different versions of the most likely position played by a player.
    The individualPosition is the best guess for which position the player actually played in isolation of anything else. The teamPosition is the best guess for which position the player actually played if we add the constraint that each team must have one top player, one jungle, one middle, etc. Generally the recommendation is to use the teamPosition field over the individualPosition field.
    */
    private String teamPosition;
    private int timeCCingOthers;
    private int timePlayed;
    private int totalDamageDealt;
    private int totalDamageDealtToChampions;
    private int totalDamageShieldedOnTeammates;
    private int totalDamageTaken;
    private int totalHeal;
    private int totalHealsOnTeammates;
    private int totalMinionsKilled;
    private int totalTimeCCDealt;
    private int totalTimeSpentDead;
    private int totalUnitsHealed;
    private int tripleKills;
    private int trueDamageDealt;
    private int trueDamageDealtToChampions;
    private int trueDamageTaken;
    private int turretKills;
    private int turretTakedowns;
    private int turretsLost;
    private int unrealKills;
    private int visionScore;
    private int visionWardsBoughtInGame;
    private int wardsKilled;
    private int wardsPlaced;
    private Boolean win;
}
