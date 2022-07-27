package com.clonelol.champion.apidto.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveSkill {

    private String id;
    private String name;
    private String description;
    private int maxrank;
    private String tooltip;
    private String cooldownBurn;    //레벨당 쿨타임 변화
    private String costBurns;   //레벨당 마나 소모량 변화
    private String rangeBurn;   //레벨당 스킬 사거리 변화
}
