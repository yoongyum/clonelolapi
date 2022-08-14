package com.clonelol.summoner.api.summonerapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import static com.clonelol.config.ApiKeyConfiguration.GAME_VERSION;

@Slf4j
public class WebClientTest {

    @Autowired
    private WebClient webClient;

    @Test
    @DisplayName("webclient 도입 테스트")
    public void test2() {
        webClient = WebClient.create();
        String[] list = webClient.get()
                .uri(GAME_VERSION)
                .retrieve()
                .bodyToMono(String[].class)
                .block();
        for (var s : list){
            System.out.println(s);
        }
    }
}
