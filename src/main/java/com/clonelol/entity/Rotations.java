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
}
