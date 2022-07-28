package com.clonelol.champion.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skill {

    @Id
    private Long id;
    private String passive;
    private String description;
    private String temp;

}
