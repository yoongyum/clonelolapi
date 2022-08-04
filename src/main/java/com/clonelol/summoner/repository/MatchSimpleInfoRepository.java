package com.clonelol.summoner.repository;

import com.clonelol.summoner.entity.MatchSimpleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchSimpleInfoRepository extends JpaRepository<MatchSimpleInfo,Long> {
    Optional<MatchSimpleInfo> findByMatchId(String matchId);
}
