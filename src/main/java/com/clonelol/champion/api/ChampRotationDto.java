package com.clonelol.champion.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ChampRotationDto {
    private int maxNewPlayerLevel;
    private List<Integer> freeChampionIdsForNewPlayers;
    private List<Integer> freeChampionIds;

    public void setMaxNewPlayerLevel(int maxNewPlayerLevel) {
        this.maxNewPlayerLevel = maxNewPlayerLevel;
    }

    public void setFreeChampionIdsForNewPlayers(List<Integer> freeChampionIdsForNewPlayers) {
        this.freeChampionIdsForNewPlayers = freeChampionIdsForNewPlayers;
    }

    public void setFreeChampionIds(List<Integer> freeChampionIds) {
        this.freeChampionIds = freeChampionIds;
    }

    @Override
    public String toString() {
        return "ChampRotationDto{" +
                "maxNewPlayerLevel=" + maxNewPlayerLevel +
                ", freeChampionIdsForNewPlayers=" + freeChampionIdsForNewPlayers +
                ", freeChampionIds=" + freeChampionIds +
                '}';
    }
}
