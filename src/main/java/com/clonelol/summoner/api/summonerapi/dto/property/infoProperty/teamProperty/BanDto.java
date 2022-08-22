package com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.teamProperty;

import lombok.Data;

@Data
public class BanDto {
    private Long championId;
    private int pickTurn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BanDto banDto = (BanDto) o;

        return championId.equals(banDto.championId);
    }

    @Override
    public int hashCode() {
        return championId.hashCode();
    }
}
