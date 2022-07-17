package com.clonelol.service;

import com.clonelol.entity.ChampionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionsService {

    public void saveAll(List<ChampionDto> championList) {

        championList.stream()
                .map()
    }
}
