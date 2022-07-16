package com.clonelol.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.net.URI;

import static com.clonelol.ApiConfiguration.CHAMP_INFO;
import static com.clonelol.ApiConfiguration.CHAMP_ROTATIONS;

@RestController
@RequestMapping("/lol/api/champion")
public class ChampionsController {

    //개발자용 API KEY 값
    String developKey = "RGAPI-48945fbf-5594-469d-8c34-c308fd84e0ff";

    BufferedReader br = null;

    //모든 챔피언 정보 불러오기
    @GetMapping("/info")
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
    @GetMapping("/rotations")
    public String getFreeChapList(Model model) {
        URI uri = UriComponentsBuilder
                .fromUriString(CHAMP_ROTATIONS)//API URI(String)를 여기다 집어넣는다.
                .queryParam(developKey)
                .encode()
                .build().toUri();   //String -> URI type 변경.

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        return result.getBody();
    }

}
