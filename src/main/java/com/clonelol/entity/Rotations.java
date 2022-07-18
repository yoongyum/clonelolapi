package com.clonelol.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Rotations {
    @Id
    private String id;    //로테이션 ID (PK)

    private int maxNewPlayerLevel;  //신규유저 레벨

    // 신규 유저 레벨 변경
    public void updateMaxLevel(int maxNewPlayerLevel){
        this.maxNewPlayerLevel = maxNewPlayerLevel;
    }
}
