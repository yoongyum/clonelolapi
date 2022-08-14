package com.clonelol.summoner.api.summonerapi;

import com.clonelol.summoner.api.summonerapi.dto.SummonerIdInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.clonelol.config.ApiKeyConfiguration.DEV_KEY;
import static com.clonelol.config.ApiKeyConfiguration.ENCRYPTED_SUMMONER_ID;

@Slf4j
public class PuuidTest {

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();

        URI PuuidUri = createUriComponent(ENCRYPTED_SUMMONER_ID)
                .encode()
                .queryParam("api_key", DEV_KEY)
                .buildAndExpand(/* summonerID */)
                .toUri();

        System.out.println(PuuidUri.toString());
//        assertThat(PuuidUri.toString()).isEqualTo("https://kr.api.riotgames.com/lol/summoner/v4/summoners/No-6akKz8AucSPKtV_QUM4P8sMfXen0cMGyzZ1j_v-y_bGjb?api_key=RGAPI-eb38b7d0-dde0-4b1f-bb1f-25a5ed994163");

        SummonerIdInfoDto summonerIDinfo = restTemplate.getForObject(PuuidUri, SummonerIdInfoDto.class);
    }

    private UriComponentsBuilder createUriComponent(String uri) {
        return UriComponentsBuilder
                .fromUriString(uri);
    }
}
