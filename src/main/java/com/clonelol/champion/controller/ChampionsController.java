package com.clonelol.champion.controller;

import com.clonelol.champion.apidto.ChampInformationDto;
import com.clonelol.champion.apidto.DetailInfoDto;
import com.clonelol.champion.apidto.SimpleInfoDto;
import com.clonelol.champion.entity.Champion;
import com.clonelol.champion.entity.Version;
import com.clonelol.champion.repository.VersionRepository;
import com.clonelol.champion.service.ChampionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.clonelol.config.ApiKeyConfiguration.*;

@RequiredArgsConstructor
public class ChampionsController {

    private final RestTemplate restTemplate;
    private final ChampionsService championsService;
    private final VersionRepository versionRepository;
    private final static List<Champion> freeChampion = new ArrayList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void checkVersion() {
        String[] result = checkChampVersion();
        String newVersion = result[0];

        Optional<Version> version = versionRepository.findById("Version");

        if (version.isEmpty()) {
            saveNewVersion(newVersion);
            return;
        }

        Version findVersion = version.get();

        if (!findVersion.isLatestVersion(newVersion)) {
            updateChamp4newVersion(newVersion, findVersion);
        }

    }


    private void updateChamp4newVersion(String newVersion, Version findVersion) {
        findVersion.updateLatestVersion(newVersion);
        versionRepository.save(findVersion);
        getChampionList(newVersion);
    }

    private void saveNewVersion(String latestVersion) {
        versionRepository.save(Version.builder()
                .id("Version")
                .latestVersion(latestVersion)
                .build());

        getChampionList(latestVersion);
    }

    private String[] checkChampVersion() {
        URI uri = createUriComponent(GAME_VERSION)
                .encode()
                .build().toUri();

        String[] result = restTemplate.getForObject(uri, String[].class);
        return result;
    }

    private void getChampionList(String version) {
        URI uri = createUriComponent(CHAMP_INFO)
                .encode()
                .buildAndExpand(version)
                .toUri();

        RequestEntity<Void> build = RequestEntity.get(uri)
                .build();

        ChampInformationDto<SimpleInfoDto> champList = restTemplate
                .exchange(build, new ParameterizedTypeReference<ChampInformationDto<SimpleInfoDto>>() {
                })
                .getBody();

        List<Champion> entityList = champList.getNameSet()
                .stream()
                .map(name -> searchChampDetail(version, name))
                .map(DetailInfoDto::convertToEntity)
                .collect(Collectors.toList());

        championsService.initializeAll(entityList);
    }


    // 각 챔피언의 세부정보 받아오는 API 호출 메서드
    private DetailInfoDto searchChampDetail(String version, String champName) {
        //String -> URI type 변경.
        URI url = createUriComponent(CHAMP_DETAILS)
                .encode()
                .buildAndExpand(version, champName)
                .toUri();

        RequestEntity<Void> request = RequestEntity.get(url)
                .build();

        ChampInformationDto<DetailInfoDto> infoList = restTemplate.exchange(request, new ParameterizedTypeReference<ChampInformationDto<DetailInfoDto>>() {
                })
                .getBody();

        return infoList.getValue(champName);
    }

    private UriComponentsBuilder createUriComponent(String uri) {
        return UriComponentsBuilder
                .fromUriString(uri);
    }

    //버전 확인하기

}
