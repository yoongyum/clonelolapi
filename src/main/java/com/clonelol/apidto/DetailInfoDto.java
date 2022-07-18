package com.clonelol.apidto;

import com.clonelol.apidto.property.*;
import com.clonelol.entity.Champion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailInfoDto {

    public String id;
    public Long key;
    public String name;
    public String title;
    private Info info;
    private List<Skin> skins = new ArrayList<>();
    private Stats stats;
    private List<ActiveSkill> spells = new ArrayList<>();
    private PassiveSkill passive;

    public Champion convertToEntity(){
        return Champion.builder()
                .id(key)
                .title(title)
                .nameKr(name)
                .nameEn(id)
                .build();
    }
}
