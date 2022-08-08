package com.clonelol.summoner.repository;

import com.clonelol.summoner.entity.SummonerIdInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummonerIdInfoRepository extends JpaRepository<SummonerIdInfo, String> {
}
