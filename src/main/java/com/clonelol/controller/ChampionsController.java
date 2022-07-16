package com.clonelol.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.clonelol.ApiConfiguration.CHAMP_INFO;
import static com.clonelol.ApiConfiguration.CHAMP_ROTATIONS;
import static com.clonelol.config.ApiKeyConfiguration.DEV_KEY;

@RestController
public class ChampionsController {

    //모든 챔피언 정보 불러오기
    @GetMapping("/lol/api/champion/info")
    public String getChampionList() {

        URI uri = UriComponentsBuilder
                .fromUriString(CHAMP_INFO)
                .encode()
                .build().toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity.get(uri).build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }


    //이번주 로테이션 정보 가져오기
    @GetMapping("/lol/api/champion/rotations")
    public String getFreeChapList(Model model) {
        URI uri = UriComponentsBuilder
                .fromUriString(CHAMP_ROTATIONS + DEV_KEY)//API URI(String)를 여기다 집어넣는다.
                .encode()
                .build().toUri();   //String -> URI type 변경.

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity.get(uri).build(); //GET 요청으로 보내고 결과 값을 받아옴.

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }
}
