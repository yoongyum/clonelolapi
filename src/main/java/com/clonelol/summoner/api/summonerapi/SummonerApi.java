package com.clonelol.summoner.api.summonerapi;

import com.clonelol.summoner.api.summonerapi.dto.SummonerApiDto;
import com.clonelol.summoner.entity.SummonerSimpleInfo;
import com.clonelol.summoner.repository.SummonerSimpleInfoRepository;
import com.clonelol.summoner.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.clonelol.config.ApiKeyConfiguration.*;
import static java.util.Objects.requireNonNull;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SummonerApi {

    private final RestTemplate restTemplate;
    private final SummonerSimpleInfoRepository summonerSimpleInfoRepository;
    private final MatchService matchService;


    @RequestMapping("/summoner")
    public void searchSummonerApi(/*String tier, String division*/){
        URI uri = createUriComponent(USER_SOLO_RANK)
                .encode()
                .queryParam("api_key", DEV_KEY)
                .buildAndExpand("DIAMOND", "I")
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();

        List<SummonerSimpleInfo> simpleInfoList = requireNonNull(restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<SummonerApiDto>>() {
                }).getBody())
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        summonerSimpleInfoRepository.saveAll(simpleInfoList);
    }

    @RequestMapping("/match")
    public void searchMatchApi(){
        URI uri = createUriComponent(MATCH_ID)
                .encode()
                .queryParam("queue",420)
                .queryParam("type","ranked")
                .queryParam("start",0)
                .queryParam("count",100)
                .queryParam("api_key", DEV_KEY)
                .buildAndExpand(/*puuid*/)
                .toUri();

        List<String> result = restTemplate.getForObject(uri, ArrayList.class);
        matchService.initializeAll(result);
    }


    private UriComponentsBuilder createUriComponent(String uri) {
        return UriComponentsBuilder
                .fromUriString(uri);
    }

    private SummonerSimpleInfo convertToEntity(SummonerApiDto dto){

        return SummonerSimpleInfo.builder()
                .summonerId(dto.getSummonerId())
                .queueType(dto.getQueueType())
                .tier(dto.getTier())
                .grade(dto.getRank())
                .build();
    }
}
