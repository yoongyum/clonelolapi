package com.clonelol.summoner.repository;

import com.clonelol.summoner.entity.MatchSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchSummaryRepository extends JpaRepository<MatchSummary,Long> {
}
