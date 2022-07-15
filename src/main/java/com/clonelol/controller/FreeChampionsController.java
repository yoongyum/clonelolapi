package com.clonelol.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.net.URI;

import static com.clonelol.ApiConfiguration.CHAMP_ROTATIONS;

@RestController
public class FreeChampionsController {
    
    //개발자용 API KEY 값
    String developKey = "RGAPI-48945fbf-5594-469d-8c34-c308fd84e0ff";

    BufferedReader br = null;
    
    //이번주 로테이션 정보 가져오기
    @GetMapping("/lol/api/champion/rotations")
    public String getFreeChapList(Model model){
        URI uri = UriComponentsBuilder
                .fromUriString(CHAMP_ROTATIONS + developKey)//API URI(String)를 여기다 집어넣는다.
                .encode()
                .build().toUri();   //String -> URI type으로 변경.

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity.get(uri).build(); //GET 요청으로 보내고 결과 값을 받아옴.

        ResponseEntity<String> result = restTemplate.exchange(req,String.class);

        return result.getBody();

//        try{
//            apiURL = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key="+developKey;
//            riotURL = new URL(apiURL);
//            urlConnection = (HttpURLConnection) riotURL.openConnection();
//            urlConnection.setRequestMethod("GET");
//
//            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
//
//            String result="";
//            String line="";
//            while((line=br.readLine()) != null) {
//                result += line;
//            }
//
////            JSONObject jsonObject = new JSONObject(result);
////            JSONArray rotations = jsonObject.JSONObject("freeChampionIds");
////
////            for (int i=0 ; i < rotations.length(); i++){
////                JSONObject object = rotations.getJSONObject(i);
////
////            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "";
    }
}
