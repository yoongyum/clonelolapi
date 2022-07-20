package com.clonelol;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)    //기본생성자를 막아둠// 밖에서 상속도 못받는 클래스임
public abstract class ApiConfiguration {

    //챔피언 세부 정보 📌 URI에 version을 포함해서 동적으로 바꿔야함 일단 고정으로 해둠..(주의)
    public static final String CHAMP_INFO = "http://ddragon.leagueoflegends.com/cdn/12.13.1/data/en_US/champion.json";

    //이번주 로테이션
    public static final String CHAMP_ROTATIONS = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key=";

}
