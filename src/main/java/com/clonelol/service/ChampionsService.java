package com.clonelol.service;

import com.clonelol.controller.dto.RotationsDto;
import com.clonelol.entity.Champion;
import com.clonelol.entity.Rotations;
import com.clonelol.repository.ChampionRepository;
import com.clonelol.repository.RotationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChampionsService {

    private final ChampionRepository championRepository;
    private final RotationsRepository rotationsRepository;

    @Transactional
    public void initializeAll(List<Champion> championList){
        championRepository.saveAll(championList);
    }

//    @Transactional
    public void updateRotations(RotationsDto rotationsDto){
        var res = rotationsRepository.findById("rotations");
        if(res.isEmpty()){//최초 생성
            System.out.println("없음");
            rotationsRepository.save(rotationsDto.convertToEntity());
        }else{
            Rotations rotations = res.get();

            //기존에 있던 로테이션 챔피언 정보 삭제
            rotations.deleteFreeChampions();

            rotations.updateMaxLevel(rotationsDto.getMaxNewPlayerLevel());

            for (Long freeChampionId : rotationsDto.getFreeChampionIds()) {
                //로테이션 Id를 통해서 챔피언을 불러온다.
                Champion champion = championRepository.findById(freeChampionId).get();

                //챔피언을 로테이션에 조인한다.
                rotations.addFreeChampions(champion);
            }
            rotationsRepository.save(rotations);
            System.out.println("로테이션 목록: ");
            for (Champion freeChampion : rotations.getFreeChampions()) {
                System.out.println(freeChampion.getId()+" : "+freeChampion.getNameKr());
            }
        }
    }
}
