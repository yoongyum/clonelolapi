package com.clonelol.summoner.api.summonerapi;

import com.clonelol.summoner.api.summonerapi.dto.LeagueEntryDto;
import com.clonelol.summoner.api.summonerapi.dto.SimpleMatchDto;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.Participant;
import com.clonelol.summoner.service.MatchService;
import com.clonelol.summoner.service.SummonerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        SimpleMatchDto matchDto = webClient.baseUrl(BASE_ASIA_API)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(MATCH_INFO)
                        .build(matchId)
                ).retrieve()
                .bodyToMono(SimpleMatchDto.class)
                .block();

        //평균티어 구하기
        assert matchDto != null;
        List<String> summonerIds = matchDto.getInfo().getParticipants()
                .stream()
                .map(Participant::getSummonerId)
                .collect(Collectors.toList());

        for (var id : summonerIds) {
            LeagueEntryDto entryDto = webClient.baseUrl(BASE_KOR_API)
                    .build()
                    .get()
                    .uri(uriBuilder -> uriBuilder.path("/lol/league/v4/entries/by-summoner/{summonerId}")
                            .queryParam("api_key", DEV_KEY)
                            .build(id)
                    ).retrieve()
                    .bodyToMono(new ParameterizedTypeReference<ArrayList<LeagueEntryDto>>() {
                    })
                    .block().get(0);
            entryDto.getTier();
            entryDto.getRank();
        }

    }

    @RequestMapping("/match")
    public void searchMatchApi(String puuId) {
        matchService.initializeAll(
                webClient.baseUrl(BASE_ASIA_API)
                        .build()
                        .get()
                        .uri(builder -> builder.path(MATCH_ID)
                                .queryParams(matchData())
                                .build(puuId)
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
