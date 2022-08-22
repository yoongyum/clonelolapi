package com.clonelol.summoner.api.summonerapi;

import com.clonelol.summoner.api.summonerapi.dto.LeagueEntryDto;
import com.clonelol.summoner.api.summonerapi.dto.MatchSummaryDto;
import com.clonelol.summoner.api.summonerapi.dto.SimpleMatchDto;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.Participant;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.Team;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.teamProperty.BanDto;
import com.clonelol.summoner.entity.Ban;
import com.clonelol.summoner.service.MatchService;
import com.clonelol.summoner.service.SummonerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

import static com.clonelol.config.ApiKeyConfiguration.*;


@Slf4j
@RequiredArgsConstructor
@RestController
public class MatchApi {
    private final WebClient.Builder webClient;
    private final MatchService matchService;
    private final SummonerService summonerService;

    @GetMapping("/matchData")
    public void getMatchData() {
        String matchId = "KR_6080890757";
        SimpleMatchDto matchDto = webClient.baseUrl(BASE_ASIA_API)
                .build()
                .get()
                .uri(
                        uriBuilder -> uriBuilder.path(MATCH_INFO)
                                .queryParam("api_key", DEV_KEY)
                                .build(matchId)
                )
                .retrieve()
                .bodyToMono(SimpleMatchDto.class)
                .block();

        //평균티어 구하기
        assert matchDto != null;
        List<String> summonerIds = matchDto.getInfo().getParticipants()
                .stream()
                .map(Participant::getSummonerId)
                .collect(Collectors.toList());

        int sum = 0;
        for (var id : summonerIds) {
            sum = getLeagueEntryDto(id)
                    .getSum();
        }
        int avgOfTier = sum / 10;

        List<MatchSummaryDto> matchSummaryDtos = matchDto.getInfo().getParticipants()
                .stream()
                .map(participant -> new MatchSummaryDto(participant, avgOfTier))
                .collect(Collectors.toList());

        matchService.initializeAllForSummary(matchSummaryDtos);

        Set<BanDto> banDtos = new HashSet<>();
        matchDto.getInfo().getTeams()
                .stream()
                .map(Team::getBanDtos)
                .forEach(banDtos::addAll);

        matchService.temp(banDtos, avgOfTier);
    }

    private LeagueEntryDto getLeagueEntryDto(String id) {
        return webClient.baseUrl(BASE_KOR_API)
                .build()
                .get()
                .uri(
                        uriBuilder -> uriBuilder.path("/lol/league/v4/entries/by-summoner/{summonerId}")
                                .queryParam("api_key", DEV_KEY)
                                .build(id)
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ArrayList<LeagueEntryDto>>() {
                })
                .block().get(0);
    }

    public void saveMatch() {
        summonerService.puuIdList()
                .forEach(this::searchMatchApi);
    }

//    @RequestMapping("/match")
    public void searchMatchApi(String puuId) {
            matchService.initializeAll(
                    Objects.requireNonNull(webClient.baseUrl(BASE_ASIA_API)
                            .build()
                            .get()
                            .uri(builder -> builder.path(MATCH_ID)
                                    .queryParams(matchData())
                                    .build(puuId)
                            )
                            .retrieve()
                            .bodyToMono(ArrayList.class)
                            .block())
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
