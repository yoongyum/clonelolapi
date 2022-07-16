package com.clonelol.controller;

import com.clonelol.entity.ChampList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
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
public class ChampionsController {

    //모든 챔피언 정보 불러오기
    @GetMapping("/info")
    public String getChampionList() throws JsonProcessingException {

        URI uri = UriComponentsBuilder
                .fromUriString(CHAMP_INFO)
                .encode()
                .build().toUri();

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        ObjectMapper om = new ObjectMapper();

        ChampList champList = om.readValue(result, ChampList.class);

        return result;
    }


    //이번주 로테이션 정보 가져오기
    @GetMapping("/rotations")
    public String getFreeChapList(Model model) {
        URI uri = UriComponentsBuilder
                .fromUriString(CHAMP_ROTATIONS)//API URI(String)를 여기다 집어넣는다.
                .queryParam("api_key", DEV_KEY)
                .encode()
                .build().toUri();   //String -> URI type 변경.

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        return result.getBody();
    }

}
