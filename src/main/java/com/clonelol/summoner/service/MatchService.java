package com.clonelol.summoner.service;

import com.clonelol.champion.entity.Champion;
import com.clonelol.champion.repository.ChampionRepository;
import com.clonelol.summoner.api.summonerapi.dto.MatchSummaryDto;
import com.clonelol.summoner.api.summonerapi.dto.property.infoProperty.teamProperty.BanDto;
import com.clonelol.summoner.entity.Ban;
import com.clonelol.summoner.entity.MatchSimpleInfo;
import com.clonelol.summoner.entity.MatchSummary;
import com.clonelol.summoner.repository.BanRepository;
import com.clonelol.summoner.repository.MatchSimpleInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final MatchSimpleInfoRepository matchSimpleInfoRepository;
    private final ChampionRepository championRepository;
    private final BanRepository banRepository;
    @Transactional
    public void initializeAll(List<String> matchInfo) {
        List<MatchSimpleInfo> foundMatchID = new ArrayList<>();
        matchInfo.forEach(id -> {
            matchSimpleInfoRepository.findByMatchId(id).orElseGet(() -> {
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
            championRepository.findById(matchSummaryDto.getChampId())
                    .ifPresent(champion -> champion.addMatchSummary(matchSummary));
        });
    }


    public void temp(Set<BanDto> banDtos, int tierAvg) {
        for (BanDto banDto : banDtos) {
            Champion champion = championRepository.findById(banDto.getChampionId())
                    .orElseThrow(IllegalArgumentException::new);
            banRepository.save(new Ban(
                    champion,
                    tierAvg
            ));
        }
    }
}
