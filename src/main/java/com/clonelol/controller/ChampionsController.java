package com.clonelol.controller;

import com.clonelol.controller.dto.RotationsDto;
import com.clonelol.entity.ChampList;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.clonelol.config.ApiKeyConfiguration.*;

@RestController
@RequestMapping("/lol/api/champion")
@RequiredArgsConstructor
public class ChampionsController {

    private final Gson gson;
    private final RestTemplate restTemplate;

    //모든 챔피언 정보 불러오기
    @GetMapping("/info")
    public String getChampionList(){

        URI uri = createUriComponent(CHAMP_INFO)
                .encode()
                .build().toUri();

        String result = restTemplate.getForObject(uri, String.class);

        ChampList champList1 = gson.fromJson(result, ChampList.class);

        System.out.println("champList1.getData().get(\"aatrox\").getStats() = " + champList1.getData().get("Aatrox").getStats());
        return result;
    }

    //이번주 로테이션 정보 가져오기
    @GetMapping("/rotations")
    public String getFreeChapList(Model model) {
        URI uri = createUriComponent(CHAMP_ROTATIONS)//API URI(String)를 여기다 집어넣는다.
                .queryParam("api_key", DEV_KEY)
                .encode()
                .build().toUri();   //String -> URI type 변경.

        String result = restTemplate.getForObject(uri, String.class);

        RotationsDto rotationNums = gson.fromJson(result, RotationsDto.class);

        return result;
    }

    private UriComponentsBuilder createUriComponent(String champInfo) {
        return UriComponentsBuilder
                .fromUriString(champInfo);
    }

}
