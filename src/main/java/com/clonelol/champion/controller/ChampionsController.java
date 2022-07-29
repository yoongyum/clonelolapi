package com.clonelol.champion.controller;

import com.clonelol.champion.apidto.ChampInformationDto;
import com.clonelol.champion.apidto.DetailInfoDto;
import com.clonelol.champion.apidto.SimpleInfoDto;
import com.clonelol.champion.controller.dto.RotationsDto;
import com.clonelol.champion.entity.Champion;
import com.clonelol.champion.service.ChampionsService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.clonelol.config.ApiKeyConfiguration.*;

@RestController
@RequestMapping("/lol/api/champion")
@RequiredArgsConstructor
public class ChampionsController {

    private final Gson gson;
    private final RestTemplate  restTemplate;
    private final ChampionsService championsService;

    //모든 챔피언 정보 불러오기
    @GetMapping("/info")
    public void getChampionList()  {

        URI uri = createUriComponent(CHAMP_INFO)
                .encode()
                .build().toUri();

        RequestEntity<Void> build = RequestEntity.get(uri)
                .build();

        ChampInformationDto<SimpleInfoDto> champList = restTemplate
                .exchange(build, new ParameterizedTypeReference<ChampInformationDto<SimpleInfoDto>>() {})
                .getBody();

        List<Champion> entityList = champList.getNameSet()
                .stream()
                .map(this::searchChampDetail)
                .map(DetailInfoDto::convertToEntity)
                .collect(Collectors.toList());

        championsService.initializeAll(entityList);
//        getFreeChampList();    //챔피언이 최신화 되면 로테이션 값도 다시 넣어줘야 한다.
    }

    //이번주 로테이션 정보 가져오기
    @GetMapping("/rotations")
    public String getFreeChampList() {
        URI uri = createUriComponent(CHAMP_ROTATIONS)
                .queryParam("api_key", DEV_KEY)
                .encode()
                .build().toUri();

        String result = restTemplate.getForObject(uri, String.class);

        RotationsDto rotationsDto = gson.fromJson(result, RotationsDto.class);

        //이번주 로테이션 최신화
        championsService.updateRotations(rotationsDto);

        return null;
    }

    // 각 챔피언의 세부정보 받아오는 API 호출 메서드
    private DetailInfoDto searchChampDetail(String champName) {
        //String -> URI type 변경.
        URI url = createUriComponent(CHAMP_DETAILS)
                .encode()
                .buildAndExpand(champName)
                .toUri();

        RequestEntity<Void> request = RequestEntity.get(url)
                .build();

        ChampInformationDto<DetailInfoDto> infoList = restTemplate.exchange(request, new ParameterizedTypeReference<ChampInformationDto<DetailInfoDto>>() {})
                .getBody();

        return infoList.getValue(champName);
    }

    private UriComponentsBuilder createUriComponent(String uri) {
        return UriComponentsBuilder
                .fromUriString(uri);
    }
    
    @GetMapping("/version")
    public String getVersion()  {

        URI uri = createUriComponent(GAME_VERSION)
                .encode()
                .build().toUri();

        RequestEntity<Void> build = RequestEntity.get(uri)
                .build();
        
        String result = restTemplate.getForObject(uri, String.class);
        
        return result;

    }
}
