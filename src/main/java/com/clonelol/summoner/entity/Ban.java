package com.clonelol.summoner.entity;

import com.clonelol.champion.entity.Champion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Ban {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "champion_id")
    private Champion champion;

    private Integer tierAvg;

    public Ban(Champion champion, Integer tierAvg) {
        this.champion = champion;
        this.tierAvg = tierAvg;
    }
}
