package com.clonelol.summoner.service;

import com.clonelol.summoner.api.summonerapi.dto.SummonerIdInfoDto;
import com.clonelol.summoner.entity.SummonerIdInfo;
import com.clonelol.summoner.repository.SummonerIdInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class SummonerService {

    private final SummonerIdInfoRepository summonerIdInfoRepository;

    //데이터로 받은 유저 ID DTO 저장
    public void initializeAllIdInfo(List<SummonerIdInfoDto> summonerIdInfoDtos) {
        log.info("받은 Dto 갯수 : {}", summonerIdInfoDtos.size());
        AtomicInteger cnt = new AtomicInteger(); //중복 카운트
        summonerIdInfoDtos.forEach(summonerIdInfoDto -> {
            Optional<SummonerIdInfo> summ = summonerIdInfoRepository.findById(summonerIdInfoDto.getSummonerId());
            if (summ.isPresent()) {
                cnt.getAndIncrement();
            } else {
                summonerIdInfoRepository.save(summonerIdInfoDto.convertToEntity());
            }
        });
        log.info("중복된 ID 수 : {}", cnt);
//        summonerIdInfoDtos.forEach(summonerIdInfoDto -> {
//            //중복 검사
//            summonerIdInfoRepository.findById(summonerIdInfoDto.getSummonerId())
//                    .orElseGet(() -> summonerIdInfoRepository.save(summonerIdInfoDto.convertToEntity()));
//        });
    }
}
