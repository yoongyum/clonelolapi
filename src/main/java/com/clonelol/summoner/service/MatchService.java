package com.clonelol.summoner.service;

import com.clonelol.summoner.entity.MatchSimpleInfo;
import com.clonelol.summoner.repository.MatchSimpleInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final MatchSimpleInfoRepository matchSimpleInfoRepository;

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
}
