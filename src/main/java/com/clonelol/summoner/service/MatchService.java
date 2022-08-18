package com.clonelol.summoner.service;

import com.clonelol.champion.repository.ChampionRepository;
import com.clonelol.summoner.api.summonerapi.dto.MatchSummaryDto;
import com.clonelol.summoner.entity.MatchSimpleInfo;
import com.clonelol.summoner.entity.MatchSummary;
import com.clonelol.summoner.repository.MatchSimpleInfoRepository;
import com.clonelol.summoner.repository.MatchSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final MatchSimpleInfoRepository matchSimpleInfoRepository;
    private final MatchSummaryRepository matchSummaryRepository;
    private final ChampionRepository championRepository;

    @Transactional
    public void initializeAll(List<String> matchInfo){
        List<MatchSimpleInfo> foundMatchID = new ArrayList<>();
        matchInfo.stream().forEach(id->{
            matchSimpleInfoRepository.findByMatchId(id).orElseGet(()->{
                foundMatchID.add(new MatchSimpleInfo(id));
                return null;
            });
        });
        matchSimpleInfoRepository.saveAll(foundMatchID);
    }

    @Transactional
    public void initializeAllForSummary(List<MatchSummaryDto> matchSummaryDtos) {
        matchSummaryDtos.forEach(matchSummaryDto -> {
            MatchSummary matchSummary = matchSummaryDto.convertToEntity();
            championRepository.findById(matchSummaryDto.getChampId()).ifPresent(champion -> {
                champion.addMatchSummary(matchSummary);
            });
        });
    }
}
