package com.clonelol.summoner.api.summonerapi;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.clonelol.config.ApiKeyConfiguration.DEV_KEY;
import static com.clonelol.config.ApiKeyConfiguration.ENCRYPTED_SUMMONER_ID;

public class PuuidTest {

    @Test
    public void test(){
        URI PuuidUri = createUriComponent(ENCRYPTED_SUMMONER_ID)
                .encode()
                .queryParam("api_key", DEV_KEY)
                .buildAndExpand("No-6akKz8AucSPKtV_QUM4P8sMfXen0cMGyzZ1j_v-y_bGjb")
                .toUri();
        System.out.println(PuuidUri.toString());
    }

    private UriComponentsBuilder createUriComponent(String uri) {
        return UriComponentsBuilder
                .fromUriString(uri);
    }
}
