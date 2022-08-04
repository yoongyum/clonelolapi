package com.clonelol.summoner.service;

import com.clonelol.summoner.repository.MatchSimpleInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final MatchSimpleInfoRepository matchSimpleInfoRepository;


}
