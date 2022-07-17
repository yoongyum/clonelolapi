package com.clonelol.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ChampList {

    private String type;
    private String format;
    private String version;
//    private Object data;
    private Map<String, Champion> data;
}
