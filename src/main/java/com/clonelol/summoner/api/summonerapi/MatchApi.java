package com.clonelol.summoner.api.summonerapi;

import com.clonelol.summoner.api.summonerapi.dto.SimpleMatchDto;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.Participant;
import com.clonelol.summoner.service.MatchService;
import com.clonelol.summoner.service.SummonerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.clonelol.config.ApiKeyConfiguration.*;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MatchApi {
    private final WebClient.Builder webclient;
    private final MatchService matchService;
    private final SummonerService summonerService;

    @GetMapping("/matchData")
    public void getMatchData() {
        String matchId = "KR_6034648402";
        SimpleMatchDto result = webclient.baseUrl(BASE_ASIA_API)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(MATCH_INFO)
                        .queryParam("api_key",DEV_KEY)
                        .build(matchId)
                ).retrieve()
                .bodyToMono(SimpleMatchDto.class)
                .block();

        assert result != null;
        List<Participant> participants = result.getInfo().getParticipants();

        for (var player : participants) {
            log.info("플레이어 이름: {}", player.getSummonerName());
            log.info("플레이어 PUUID: {}", player.getPuuid());
            log.info("사용한 챔피언: {}", player.getChampionName());
            log.info("승패 여부: {}", player.getWin());
            log.info("=======================================");
        }
    }
}
