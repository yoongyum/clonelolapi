package com.clonelol.summoner.controller;

import com.clonelol.summoner.api.summonerapi.dto.SummonerDto;
import com.clonelol.summoner.apidto.LeagueEntryDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.clonelol.config.ApiKeyConfiguration.*;

@RestController
public class SummonerController {

    // 소환사 전적검색
    @GetMapping("/summoner/search/{summonerName}")
    public String searchSummoner(@PathVariable("summonerName") String summonerName, Model model, HttpServletRequest req){

        String result = "", line;
        BufferedReader br = null;
        // 소환사 정보
        SummonerDto summonerDto = null;


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

            // Gson 으로 SummonerDto 정보 받아오기
            Gson gson = new Gson();
            summonerDto = gson.fromJson(result, SummonerDto.class);

            String accountId = summonerDto.getAccountId();          // 소환사 account ID
            int profileIconId = summonerDto.getProfileIconId();     // 소환사 profileIconId
            long revisionDate = summonerDto.getRevisionDate();      // 소환사 revisionDate
            String name = summonerDto.getName();                    // 소환사 name
            String id = summonerDto.getId();                        // 소환사 id
            String puuid = summonerDto.getPuuid();                  // 소환사 puuid
            long summonerLevel = summonerDto.getSummonerLevel();    // 소환사 레벨

            model.addAttribute("profileIconId",profileIconId);
            model.addAttribute("summonerLevel",summonerLevel);
            model.addAttribute("name",name);

            // 랭크정보 불러 오기
            urlstr = SUMMONER_DETAIL + id + "?api_key=" + DEV_KEY;
            url = new URL(urlstr);
            result = "";
            line = null;

            urlConnection = (HttpURLConnection) url.openConnection();
            // GET 방식으로 데이터를 가져오기
            urlConnection.setRequestMethod("GET");
            // UTF-8 로 인코딩 후 br 에 넣어주기
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            // br 에 넣은 값이 비어있지 않을 때 까지 result 에 값 넣어주기
            while((line = br.readLine()) != null) {
                result += line;
            }

            gson = new Gson();
            // 배열 형태로 리턴되기때문에 List 타입추론
            List<LeagueEntryDto> leagueEntryDto = null;
            leagueEntryDto = gson.fromJson(result, new TypeToken<List<LeagueEntryDto>>(){}.getType());

            String leagueId = leagueEntryDto.get(0).getLeagueId();         // 리그이름
            String summonerId = leagueEntryDto.get(0).getSummonerId();     // 소환사 암호화 명
            String tier = leagueEntryDto.get(0).getTier();                 // 티어(골드, 브론즈)
            String rank = leagueEntryDto.get(0).getRank();                 // 랭크(1,2,3,4)
            int leaguePoints = leagueEntryDto.get(0).getLeaguePoints();    // LP
            int wins = leagueEntryDto.get(0).getWins();                    // 승
            int losses = leagueEntryDto.get(0).getLosses();                // 패

            // 승률
            int winningRate = (int)(((double)wins / (double)(wins + losses)) * 100);

            model.addAttribute("leagueId", leagueId);
            model.addAttribute("summonerId", summonerId);
            model.addAttribute("tier", tier);
            model.addAttribute("rank", rank);
            model.addAttribute("leaguePoints", leaguePoints);
            model.addAttribute("wins", wins);
            model.addAttribute("losses", losses);
            model.addAttribute("winningRate", winningRate);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "/";
    }
}
