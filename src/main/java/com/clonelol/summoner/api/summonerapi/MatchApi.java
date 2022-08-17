package com.clonelol.summoner.api.summonerapi;

import com.clonelol.summoner.api.summonerapi.dto.SimpleMatchDto;
import com.clonelol.summoner.service.MatchService;
import com.clonelol.summoner.service.SummonerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import static com.clonelol.config.ApiKeyConfiguration.BASE_ASIA_API;
import static com.clonelol.config.ApiKeyConfiguration.MATCH_INFO;


@Slf4j
@RequiredArgsConstructor
public class MatchApi {
    //https://asia.api.riotgames.com
    // /lol/match/v5/matches/KR_6034648402?api_key=RGAPI-eb38b7d0-dde0-4b1f-bb1f-25a5ed994163
    private final WebClient.Builder webclient;
    private final MatchService matchService;
    private final SummonerService summonerService;

    @GetMapping("/matchData")
    public void getMatchData() {
        String matchId = "KR_6034648402";
        webclient.baseUrl(BASE_ASIA_API)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(MATCH_INFO)
                        .build(matchId)
                ).retrieve()
                .bodyToMono(SimpleMatchDto.class)
                .block();
    }
}
