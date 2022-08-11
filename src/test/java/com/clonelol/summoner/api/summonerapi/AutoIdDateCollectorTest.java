package com.clonelol.summoner.api.summonerapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.clonelol.config.ApiKeyConfiguration.DEV_KEY;
import static com.clonelol.config.ApiKeyConfiguration.USER_SOLO_RANK;

@Slf4j
@SpringBootTest
public class AutoIdDateCollectorTest {


    String[] 티어 = {"GOLD", "PLATINUM", "DIAMOND"};
    String[] 단계 = {"I", "II", "III", "IV"};

    @Test
    public void AutoRun() {
        for (var tier : 티어) {
            log.info("티어 : {}", tier);
            for (var rank : 단계) {
                log.info("랭크 : {} ", rank);
                for (int page = 1; page <= 4; page++) {
                    String result = showUri(page,tier,rank);
                    log.info("URI : {}" ,result);
                }
            }
        }
    }

    private String showUri(int page,String tier, String rank) {
        URI uri = createUriComponent(USER_SOLO_RANK)
                .encode()
                .queryParam("page", page)
                .queryParam("api_key", DEV_KEY)
                .buildAndExpand(tier, rank)
                .toUri();
        return uri.toString();
    }

    private UriComponentsBuilder createUriComponent(String uri) {
        return UriComponentsBuilder
                .fromUriString(uri);
    }
}
