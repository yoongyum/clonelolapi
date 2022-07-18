package com.clonelol.controller;

import com.clonelol.controller.dto.RotationsDto;
import com.clonelol.entity.ChampListDto;
import com.clonelol.entity.ChampionDto;
import com.clonelol.entity.ChampionInfoDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.clonelol.config.ApiKeyConfiguration.*;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/lol/api/champion")
@RequiredArgsConstructor
public class ChampionsController {

    private final Gson gson;
    private final RestTemplate  restTemplate;

    //모든 챔피언 정보 불러오기
    @GetMapping("/info")
    public String getChampionList() {

        URI uri = createUriComponent(CHAMP_INFO)
                .encode()
                .build().toUri();

        RequestEntity<Void> build = RequestEntity.get(uri)
                .build();

        ChampListDto<ChampionDto> champList = restTemplate.exchange(
                build, new ParameterizedTypeReference<ChampListDto<ChampionDto>>() {})
                .getBody();

        List<ChampionInfoDto> collect = champList.champNameSet()
                .stream()
                .map(this::champDetails)
                .collect(toList());

        for (ChampionInfoDto championInfoDto : collect) {
            System.out.println("championInfoDto = " + championInfoDto);
        }
        return null;
    }

    //이번주 로테이션 정보 가져오기
    @GetMapping("/rotations")
    public String getFreeChapList(Model model) {
        URI uri = createUriComponent(CHAMP_ROTATIONS)
                .queryParam("api_key", DEV_KEY)
                .encode()
                .build().toUri();

        String result = restTemplate.getForObject(uri, String.class);

        RotationsDto rotationNums = gson.fromJson(result, RotationsDto.class);

        return result;
    }

    private UriComponentsBuilder createUriComponent(String champInfo) {
        return UriComponentsBuilder
                .fromUriString(champInfo);
    }

    // 각 챔피언의 세부정보 받아오는 API 호출 메서드
    private ChampionInfoDto champDetails(String champName) {
        //String -> URI type 변경.

        URI url = createUriComponent(CHAMP_DETAILS)
                .encode()
                .buildAndExpand(champName)
                .toUri();

        RequestEntity<Void> request = RequestEntity.get(url)
                .build();

        ChampListDto<ChampionInfoDto> infoList = restTemplate.exchange(request, new ParameterizedTypeReference<ChampListDto<ChampionInfoDto>>() {})
                .getBody();

        return infoList.getData().get(champName);
    }
}
