package com.clonelol.service;

import com.clonelol.controller.dto.RotationsDto;
import com.clonelol.entity.Champion;
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
    public void initializeAll(List<Champion> championList) {
        championRepository.saveAll(championList);
    }

    @Transactional
    public void updateRotations(RotationsDto rotationsDto) {
        rotationsRepository.findById("rotations")
                .ifPresent(rotations -> {
                    List<Champion> championList = rotations.getFreeChampions();
                    for (Champion champion : championList) {
                        champion.deleteRotations();
                    }
                    rotations.deleteFreeChampions();

                    rotations.updateMaxLevel(rotationsDto.getMaxNewPlayerLevel());

                    //로테이션 Id를 통해서 챔피언을 불러온다.
                    //챔피언을 로테이션에 조인한다.
                    rotationsDto.getFreeChampionIds()
                            .stream()
                            .map(freeId -> championRepository.findById(freeId).get())
                            .forEach(champion -> champion.setRotations(rotations));

                    rotationsRepository.save(rotations);
                    return;
                });
        rotationsRepository.save(rotationsDto.convertToEntity());
    }
}
