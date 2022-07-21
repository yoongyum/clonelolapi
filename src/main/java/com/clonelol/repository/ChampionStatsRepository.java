package com.clonelol.repository;

import com.clonelol.entity.ChampionStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionStatsRepository extends JpaRepository<ChampionStats,Long> {
}
