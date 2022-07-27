package com.clonelol.summoner.controller;

import com.clonelol.summoner.apidto.SummonerDto;
import com.google.gson.Gson;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import static com.clonelol.config.ApiKeyConfiguration.DEV_KEY;
import static com.clonelol.config.ApiKeyConfiguration.SUMMONER_SEARCH;

@RestController
public class SummonerController {

    // 소환사 전적검색
    @GetMapping("/lol/api/summoner/search/{summonerName}")
    public String searchSummoner(@PathVariable("summonerName") String summonerName, Model model, HttpServletRequest req){

        String result = "", line;
        BufferedReader br = null;
        // 소환사 정보
        SummonerDto summonerDto = null;
        // 소환사 이름을 불러와 SummonerName 에 문자열로 저장하기
//        String mySummonerName = req.getParameter("summonerName");

//        URI uri = UriComponentsBuilder
//                .fromUriString(SUMMONER_SEARCH + summonerName + "?api_key=" + DEV_KEY)
//                .encode()
//                .build()
//                .toUri();
//        RestTemplate restTemplate = new RestTemplate();
//
//        RequestEntity<Void> summonerReq = RequestEntity.get(uri).build(); //GET 요청으로 보내고 결과 값을 받아옴.
//
//        ResponseEntity<String> summonerResult = restTemplate.exchange(summonerReq,String.class);
//
//        return summonerResult.getBody();

        try{
            // RequestURL 설정하기
            String urlstr = SUMMONER_SEARCH + summonerName.replace(" ", "") +
                    "?api_key=" + DEV_KEY;

            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // GET 방식으로 데이터를 가져오기
            urlConnection.setRequestMethod("GET");
            // UTF-8 로 인코딩 후 br 에 넣어주기
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            // br 에 넣은 값이 비어있지 않을 때 까지 result 에 값 넣어주기
            while((line = br.readLine()) != null) {
                result += line;
            }

            // Gson 으로 바꿔야함!!
            Gson gson = new Gson();
            summonerDto = gson.fromJson(result, SummonerDto.class);

            String accountId = summonerDto.getAccountId();          // 소환사 account ID
            int profileIconId = summonerDto.getProfileIconId();     // 소환사 profileIconId
            long revisionDate = summonerDto.getRevisionDate();      // 소환사 revisionDate
            String name = summonerDto.getName();                    // 소환사 name
            String id = summonerDto.getId();                        // 소환사 id
            String puuid = summonerDto.getPuuid();                  // 소환사 puuid
            long summonerLevel = summonerDto.getSummonerLevel();    // 소환사 레벨

            System.out.println(summonerDto.toString());

            model.addAttribute("accountId",accountId);
            model.addAttribute("profileIconId",profileIconId);
            model.addAttribute("revisionDate",revisionDate);
            model.addAttribute("SummonerName",summonerName);
            model.addAttribute("name",name);
            model.addAttribute("id",id);
            model.addAttribute("puuid",puuid);
            model.addAttribute("summonerLevel",summonerLevel);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "/";
    }
}
