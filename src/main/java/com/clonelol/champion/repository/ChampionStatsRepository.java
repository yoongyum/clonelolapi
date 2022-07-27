package com.clonelol.champion.repository;

import com.clonelol.champion.entity.ChampionStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionStatsRepository extends JpaRepository<ChampionStats,Long> {
}
