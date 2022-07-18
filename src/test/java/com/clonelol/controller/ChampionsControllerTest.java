package com.clonelol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.clonelol.config.ApiKeyConfiguration.CHAMP_INFO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
class ChampionsControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper om;
//    @Autowired
//    ChampionsController championsController;

    @Test
    void getChampionList() throws Exception {

        mockMvc.perform(get("/lol/api/champion/info"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    void test1() {
        String s = UriComponentsBuilder.fromUriString("123")
                .buildAndExpand("321")
                .encode().toUriString();
        System.out.println("s = " + s);
    }

    @Test
    void test2() {

        URI uri = UriComponentsBuilder.fromUriString(CHAMP_INFO)
                .encode()
                .build().toUri();

        String s = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body("123")
                .toString();

        System.out.println("s = " + s);
    }

}