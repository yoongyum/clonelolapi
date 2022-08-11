package com.clonelol.summoner.api.summonerapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.clonelol.config.ApiKeyConfiguration.DEV_KEY;
import static com.clonelol.config.ApiKeyConfiguration.USER_SOLO_RANK;

@SpringBootTest
public class SummonerIdTest {

    @Test
    public void test(){
        URI uri = createUriComponent(USER_SOLO_RANK)
                .encode()
                .queryParam("page",2)
                .queryParam("api_key", DEV_KEY)
                .buildAndExpand("DIAMOND", "I")
                .toUri();
        System.out.println(uri.toString());
    }


    private UriComponentsBuilder createUriComponent(String uri) {
        return UriComponentsBuilder
                .fromUriString(uri);
    }
}
