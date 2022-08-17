package com.clonelol.summoner.api.summonerapi;

import com.clonelol.summoner.api.summonerapi.dto.SimpleMatchDto;
import com.clonelol.summoner.service.MatchService;
import com.clonelol.summoner.service.SummonerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

import static com.clonelol.config.ApiKeyConfiguration.*;


@Slf4j
@RequiredArgsConstructor
public class MatchApi {
    private final WebClient.Builder webClient;
    private final MatchService matchService;
    private final SummonerService summonerService;

    @GetMapping("/matchData")
    public void getMatchData() {
        String matchId = "KR_6034648402";
        webClient.baseUrl(BASE_ASIA_API)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(MATCH_INFO)
                        .build(matchId)
                ).retrieve()
                .bodyToMono(SimpleMatchDto.class)
                .block();
    }

    @RequestMapping("/match")
    public void searchMatchApi() {

        matchService.initializeAll(
                webClient.baseUrl(MATCH_ID)
                        .build()
                        .get()
                        .uri(builder -> builder
                                .queryParams(matchData())
                                .build(/*사용시 pUuid 값 입력*/)
                        )
                        .retrieve()
                        .bodyToMono(ArrayList.class)
                        .block()
        );
    }


    private MultiValueMap<String, String> matchData() {
        MultiValueMap<String, String> mv = new LinkedMultiValueMap<>();
        mv.set("queue", "420");
        mv.set("type", "ranked");
        mv.set("start", "0");
        mv.set("count", "100");
        mv.set("api_key", DEV_KEY);
        return mv;
    }
}
