package com.clonelol.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
public class ChampInformationDto<T> {

    private String version;

    private Map<String, T> data;

    public Set<String> getNameSet(){
        return data.keySet();
    }

    public T getValue(String name) {
        return (T)(data.get(name));
    }

}
