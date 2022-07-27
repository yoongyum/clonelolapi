package com.clonelol.summoner.apidto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SummonerDto {

    private String accountId;   // 소환사 계정 ID
    private int profileIconId;  // 소환사 프로필 아이콘 ID
    private long revisionDate;  // 소환사의 revisionDate (프로필 아이콘 변경, 닉네임 변경시 갱신됌.)
    private String name;        // 소환사의 닉네임
    private String id;          // 소환사의 Encrypted summoner ID
    private String puuid;       // 소환사의 Encrypted puuid
    private long summonerLevel; // 소환사의 레벨

}
