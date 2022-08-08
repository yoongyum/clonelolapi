package com.clonelol.summoner.service;

import com.clonelol.summoner.api.summonerapi.dto.SummonerIdInfoDto;
import com.clonelol.summoner.repository.SummonerIdInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class SummonerService {

    private final SummonerIdInfoRepository summonerIdInfoRepository;

    //데이터로 받은 유저 ID DTO 저장
    public void initializeAllIdInfo(List<SummonerIdInfoDto> summonerIdInfoDtos) {
        summonerIdInfoDtos.forEach(summonerIdInfoDto -> {
            //중복 검사
            summonerIdInfoRepository.findById(summonerIdInfoDto.getSummonerId())
                    .orElseGet(() -> summonerIdInfoRepository.save(summonerIdInfoDto.convertToEntity()));
        });
    }
}
