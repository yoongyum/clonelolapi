package com.clonelol.champion.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Version {
    @Id
    private String id;

    private String LatestVersion;

    public void updateLatestVersion(String newVersion) {
        this.LatestVersion = newVersion;
    }
}
