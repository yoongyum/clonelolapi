package com.clonelol;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)    //기본생성자를 막아둠// 밖에서 상속도 못받는 클래스임
public abstract class ApiConfiguration {

    //이번주 로테이션
    public static final String CHAMP_ROTATIONS = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key=";
}
