package com.clonelol.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleInfoDto {

    public String id;
    public String key;
    public String name;

//    public ChampionEntity convertToChamp() {
//        return ChampionEntitiy.builder()
//                .id(id)
//                .name(name)
//                .stats(statusBuilder())
//                .build();
//    }
}
