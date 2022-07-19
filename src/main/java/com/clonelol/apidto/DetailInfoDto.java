package com.clonelol.apidto;

import com.clonelol.apidto.property.*;
import com.clonelol.config.ApiKeyConfiguration;
import com.clonelol.entity.Champion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailInfoDto {

    public String id;   //챔피언 영문명(고정)
    public Long key;    //키
    public String name; //챔피언 명(Region에 따라 다름 :현재 한글)
    public String title;//챔피언 타이틀
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
                .portrait(ApiKeyConfiguration.CHAMP_IMG_PORTRAIT+id+".png")
                .build();
    }
}
