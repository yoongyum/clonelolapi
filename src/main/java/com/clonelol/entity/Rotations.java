package com.clonelol.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Rotations {
    @Id
    private String id = "rotations";    //로테이션 ID

    private int maxNewPlayerLevel;
}
