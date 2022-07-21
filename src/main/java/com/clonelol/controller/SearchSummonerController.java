package com.clonelol.controller;

import com.clonelol.controller.config.ApiKeyConfiguration;
import com.clonelol.controller.dto.SummonerDTO;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@RestController
public class SearchSummonerController extends ApiKeyConfiguration {

    // 소환사 전적검색
    @GetMapping("/lol/api/summoner/search/{summonerName}")
    public String searchSummoner(@PathVariable("summonerName") String summonerName, Model model, HttpServletRequest req){

        String result = "", line;
        BufferedReader br = null;
        // 소환사 정보
        SummonerDTO summonerDTO = null;
        // 소환사 이름을 불러와 SummonerName 에 문자열로 저장하기
//        String mySummonerName = req.getParameter("summonerName");

        URI uri = UriComponentsBuilder
                .fromUriString(SUMMONER_SEARCH + summonerName + "?api_key=" + DEV_KEY)
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> summonerReq = RequestEntity.get(uri).build(); //GET 요청으로 보내고 결과 값을 받아옴.

        ResponseEntity<String> summonerResult = restTemplate.exchange(summonerReq,String.class);

        return summonerResult.getBody();

//        try{
//            // RequestURL 설정하기
//            String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"
//                    + summonerName.replace(" ", "") +"?api_key=" + DEV_KEY;
//
//            URL url = new URL(urlstr);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            // GET 방식으로 데이터를 가져오기
//            urlConnection.setRequestMethod("GET");
//            // UTF-8 로 인코딩 후 br 에 넣어주기
//            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
//
//            // br 에 넣은 값이 비어있지 않을 때 까지 result 에 값 넣어주기
//            while((line = br.readLine()) != null) {
//                result += line;
//            }
//
//            // Gson 으로 바꿔야함!!
//            JsonParser jsonParser = new JsonParser();                         // json 으로 받아온 객체들을 parsing
//            JsonObject jResult =  (JsonObject) jsonParser.parse(result);

//            int profileIconId = jResult.get("profileIconId").getAsInt();      // 소환사 profileIconId
//            String name = jResult.get("name").getAsString();                  // 소환사 name
//            String puuid = jResult.get("puuid").getAsString();                // 소환사 puuid
//            long summonerLevel = jResult.get("summonerLevel").getAsLong();    // 소환사 레벨
//            long revisionDate = jResult.get("revisionDate").getAsLong();      // 소환사 revisionDate
//            String id = jResult.get("id").getAsString();                      // 소환사 id
//            String accountId = jResult.get("accountId").getAsString();        // 소환사 account ID
//
//            summonerDTO = new SummonerDTO(accountId,profileIconId,revisionDate,name,id,puuid,summonerLevel); // 가져온객체를 생성자에 전달
//
//            model.addAttribute("SummonerName",summonerName);
//            model.addAttribute("profileIconId",profileIconId);
//            model.addAttribute("name",name);
//            model.addAttribute("puuid",puuid);
//            model.addAttribute("summonerLevel",summonerLevel);
//            model.addAttribute("revisionDate",revisionDate);
//            model.addAttribute("id",id);
//            model.addAttribute("accountId",accountId);
//
//
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return "/";
    }
}
