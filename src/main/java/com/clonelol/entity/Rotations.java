package com.clonelol.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
public class Rotations {
    @Id
    private String id;    //로테이션 ID (PK)

    private int maxNewPlayerLevel;  //신규유저 레벨

    @OneToMany(mappedBy = "rotations")
    private List<Champion> freeChampions = new ArrayList<>();

    // 신규 유저 레벨 변경
    public void updateMaxLevel(int maxNewPlayerLevel){
        this.maxNewPlayerLevel = maxNewPlayerLevel;
    }


    //로테이션 리스트 추가
    public void addFreeChampions(Champion champion){
        this.freeChampions.add(champion);
        champion.setRotations(this);
    }

    public void deleteFreeChampions(){
        //지난주 로테이션 챔피언들의 FK값을 NULL 처리
        for (Champion freeChampion : freeChampions) {
            freeChampion.setRotations(null);
        }
        //챔피언 리스트 초기화
        freeChampions = new ArrayList<>();
    }

}
