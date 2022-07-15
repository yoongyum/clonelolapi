package com.clonelol.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Champion {

    private String type = "champion";
    private String format = "standAloneComplex";

}
