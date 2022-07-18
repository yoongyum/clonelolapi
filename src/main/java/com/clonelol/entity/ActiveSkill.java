package com.clonelol.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveSkill {

    private String id;
    private String name;
    private String tooltip;
    private String cooldownBurn;
    private String costBurns;
}
