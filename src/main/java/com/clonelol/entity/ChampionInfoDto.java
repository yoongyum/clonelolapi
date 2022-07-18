package com.clonelol.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChampionInfoDto {

    public String id;
    public String key;
    public String name;
    public String title;
    private Info info;
    private List<Skin> skins = new ArrayList<>();
    private Stats stats;
    private List<ActiveSkill> spells = new ArrayList<>();
    private PassiveSkill passive;

//    public ChampionInfoDto convertToEntity() {
//
//
//    }


//    public ChampionEntity convertToChamp() {
//        return ChampionEntitiy.builder()
//                .id(id)
//                .name(name)
//                .stats(statusBuilder())
//                .build();
//    }
}
