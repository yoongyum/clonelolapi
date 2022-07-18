package com.clonelol.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
public class ChampListDto<T> {

    private String version;

    private Map<String, T> data;

    public Set<String> champNameSet(){
        return data.keySet();
    }

}
