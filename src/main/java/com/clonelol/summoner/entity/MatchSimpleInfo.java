package com.clonelol.summoner.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class MatchSimpleInfo {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    private String matchId;
    
    private Boolean checked = false;    //이미 사용한 매치 데이터인지 확인 - 사용한 뒤에는 true로 바꿔줘야함

    public MatchSimpleInfo(String matchId) {
        this.matchId = matchId;
    }
}
