package com.clonelol.entity;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Champion {

    private String version;
    private String name;
    private String title;
    private ChampList data;
    private String parType;
    @Embedded
    private Stats stats;

}
