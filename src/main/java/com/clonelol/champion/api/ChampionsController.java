package com.clonelol.champion.api;

import com.clonelol.champion.entity.Champion;
import com.clonelol.champion.entity.Version;
import com.clonelol.champion.repository.VersionRepository;
import com.clonelol.champion.service.ChampionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.clonelol.config.ApiKeyConfiguration.*;
import static java.util.Objects.requireNonNull;

@Slf4j
@Component
@RestController
@RequiredArgsConstructor
public class ChampionsController {

    private final WebClient.Builder webClient;
    private final ChampionsService championsService;
    private final VersionRepository versionRepository;
    private final static List<Champion> freeChampion = new ArrayList<>();

    //서버 켜지면 버전이 최신인지 확인 후
    @EventListener(ApplicationReadyEvent.class)
    public void checkVersion() {

        String[] result = checkChampVersion();
        String newVersion = result[0];

        //저장된 데이터가
        //있으면 그대로 가져오고, 없으면 최신 버전을 생성하면서 챔프 정보를 불러온다.
        Version version = versionRepository.findById("Version")
                .orElseGet(() -> create(newVersion));

        //저장된 데이터가 있을 때,
        //최신 버전인지 확인하고 아니라면 업데이트 한다.
        if (!version.isLatestVersion(newVersion)) {
            updateChamp4newVersion(newVersion, version);
        }

    }

    //버전데이터가 없을경우 최초 등록 메서드
    private Version create(String newVersion) {
        Version version = versionRepository.save(Version.builder()
                .id("Version")
                .latestVersion(newVersion)
                .build());
        getChampionList(newVersion);
        return version;
    }

    //버전이 바뀔경우 기존 챔피언 정보 업데이트
    private void updateChamp4newVersion(String newVersion, Version findVersion) {
        findVersion.updateLatestVersion(newVersion);
        versionRepository.save(findVersion);
        getChampionList(newVersion);
    }

    private String[] checkChampVersion() {
        return webClient
                .baseUrl(GAME_VERSION).build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }

    public void getChampionList(String version) {
        ChampInformationDto<SimpleInfoDto> simpleChampList = webClient
                        .baseUrl(BASE_GAME_DATA)
                        .build()
                        .get()
                        .uri(uriBuilder -> uriBuilder.path(CHAMP_INFO)
                        .build(version)
                ).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ChampInformationDto<SimpleInfoDto>>() {})
                .block();

        assert simpleChampList != null;
        List<Champion> entityList = simpleChampList.getNameSet()
                .stream()
                .map(name -> searchChampDetail(version, name))
                .map(DetailInfoDto::convertToEntity)
                .collect(Collectors.toList());

        championsService.initializeAll(entityList);
    }


    // 각 챔피언의 세부정보 받아오는 API 호출 메서드
    private DetailInfoDto searchChampDetail(String version, String champName) {
        return requireNonNull(
                webClient
                .baseUrl(BASE_GAME_DATA)
                .build()
                .get()
                .uri(builder -> builder.path(CHAMP_DETAILS)
                        .build(version, champName))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ChampInformationDto<DetailInfoDto>>() {
                })
                .block()).getValue(champName);
    }
}
