package com.clonelol.summoner.repository;

import com.clonelol.summoner.entity.MatchSimpleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchSimpleInfoRepository extends JpaRepository<MatchSimpleInfo,Long> {
}
