package com.clonelol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ChampionEntity {

    @Id
    private String id;

    @Column(name = "champ_name")
    private String name;
    @OneToOne(mappedBy = "stats_id")
    Stats stats;

}
