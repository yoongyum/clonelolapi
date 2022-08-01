package com.clonelol.champion.controller;

import com.clonelol.champion.apidto.ChampInformationDto;
import com.clonelol.champion.apidto.DetailInfoDto;
import com.clonelol.champion.apidto.SimpleInfoDto;
import com.clonelol.champion.controller.dto.RotationsDto;
import com.clonelol.champion.entity.Champion;
import com.clonelol.champion.entity.Version;
import com.clonelol.champion.repository.VersionRepository;
import com.clonelol.champion.service.ChampionsService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

    private final VersionRepository versionRepository;

    //모든 챔피언 정보 불러오기
    @GetMapping("/info")
    public void getChampionList(String version)  {

        URI uri = createUriComponent(CHAMP_INFO)
                .encode()
                .buildAndExpand(version)
                .toUri();

        RequestEntity<Void> build = RequestEntity.get(uri)
                .build();

        ChampInformationDto<SimpleInfoDto> champList = restTemplate
                .exchange(build, new ParameterizedTypeReference<ChampInformationDto<SimpleInfoDto>>() {})
                .getBody();

        List<Champion> entityList = champList.getNameSet()
                .stream()
                .map(name -> searchChampDetail(version, name))
                .map(DetailInfoDto::convertToEntity)
                .collect(Collectors.toList());

        championsService.initializeAll(entityList);
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
    private DetailInfoDto searchChampDetail(String version,String champName) {
        //String -> URI type 변경.
        URI url = createUriComponent(CHAMP_DETAILS)
                .encode()
                .buildAndExpand(version,champName)
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
    
    //버전 확인하기
    @EventListener(ApplicationReadyEvent.class)
    public String getVersion()  {
        URI uri = createUriComponent(GAME_VERSION)
                .encode()
                .build().toUri();

        RequestEntity<Void> build = RequestEntity.get(uri)
                .build();
        
        String[] result = restTemplate.getForObject(uri, String[].class);

        versionRepository.findById("Version").ifPresentOrElse(version -> {
            //버전이 최신화 될 경우
            if( result[0] != version.getLatestVersion()){
                version.updateLatestVersion(result[0]);
                versionRepository.save(version);
                getChampionList(version.getLatestVersion());
            }
            //버전이 그대로면 아무일도 안일어남
        },() -> {//버전 테이블이 검색이 안돼면 새로 생성
            versionRepository.deleteAll();
            Version version = new Version();
            version.setId("Version");
            version.setLatestVersion(result[0]);
            versionRepository.save(version);
            getChampionList(version.getLatestVersion());
        });


        return null;

    }
}
