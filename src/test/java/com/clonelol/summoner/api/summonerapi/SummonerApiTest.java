package com.clonelol.summoner.api.summonerapi;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import static com.clonelol.config.ApiKeyConfiguration.DEV_KEY;
import static com.clonelol.config.ApiKeyConfiguration.MATCH_ID;

class SummonerApiTest {

    @Test
    public void tset(){
        String s = createUriComponent(MATCH_ID)
                .encode()
                .queryParam("queue", 420)
                .queryParam("type", "ranked")
                .queryParam("start", 0)
                .queryParam("count", 100)
                .queryParam("api_key", DEV_KEY)
                .buildAndExpand("fwZLSwNnrgqXaNHNV2bQFkdD3jFXqb2rmzjxQyNbl_aLqmGS7ovTUh7dC5yT4_lpPowuk0Ddx-hDUw")
                .toUri().toString();
        System.out.println(s);
    }
    private UriComponentsBuilder createUriComponent(String uri) {
        return UriComponentsBuilder
                .fromUriString(uri);
    }

}