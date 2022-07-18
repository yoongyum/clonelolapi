package com.clonelol.service;

import com.clonelol.entity.Champion;
import com.clonelol.repository.ChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChampionsService {

    private final ChampionRepository championRepository;

    @Transactional
    public void initializeAll(List<Champion> championList){
        championRepository.saveAll(championList);
    }
}
