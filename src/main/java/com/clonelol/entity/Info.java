package com.clonelol.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@ToString
public class Info {

    private int attack;
    private int defense;
    private int magic;
    private int difficulty;

    public Info(String key, Integer value){
        this.attack = value;
        this.defense = value;
        this.magic = value;
        this.difficulty = value;
    }
}
