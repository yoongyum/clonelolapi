package com.clonelol.summoner.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Rank {
    I("I",100),
    II("II",75),
    III("III",50),
    IV("IV",25);

    private final String key;
    private final int value;
}
