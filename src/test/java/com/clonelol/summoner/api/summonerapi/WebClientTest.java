package com.clonelol.summoner.api.summonerapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.clonelol.config.ApiKeyConfiguration.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class WebClientTest {

    @Autowired
    private WebClient.Builder webClient;

    @Test
    @DisplayName("webclient 도입 테스트")
    public void test2() {
        log.info("retrieve 사용");
        String[] list = webClient.baseUrl(GAME_VERSION).build().get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String[].class)
                .block();

        assert list != null;
        log.info("최신버전: {}", list[0]);

        log.info("exchangeTo 사용");
        String[] list2 = webClient.build().get()
                .uri(GAME_VERSION)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(res -> {
                    if (res.statusCode().equals(HttpStatus.OK)) {
                        return res.bodyToMono(String[].class);
                    } else {
                        return res.createException().flatMap(Mono::error);
                    }
                }).block();

        assert list2 != null;
        log.info("최신버전: {}", list2[0]);
    }
    @Test
    @DisplayName("파라미터 테스트")
    public void test3(){
        String list = webClient.baseUrl("https://kr.api.riotgames.com").build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/lol/league/v4/entries/RANKED_SOLO_5x5/{tier}/{rank}")
                            .queryParam("page",1)
                            .queryParam("api_key", DEV_KEY)
                            .build("DIAMOND","I"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(list.toString());
    }
}
