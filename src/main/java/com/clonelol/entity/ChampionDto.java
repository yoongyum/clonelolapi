package com.clonelol.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ChampionDto {

    public String version;
    public String id;
    public String key;
    public String name;
    public String title;
    public String blurb;
//    @Embedded
    private Info info;
    public Image image;
    public ArrayList<String> tags;
    public String partype;
    public Stats stats;

    public StatsEntity statusBuilder(){
        return StatsEntity.builder()
                .stats(stats)
                .info(info)
                .build();
    }

    public ChampionEntity convertToChamp() {
        return ChampionEntitiy.builder()
                .id(id)
                .name(name)
                .stats(statusBuilder())
                .build();
    }
}
