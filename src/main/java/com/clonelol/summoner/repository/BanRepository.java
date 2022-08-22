package com.clonelol.summoner.repository;

import com.clonelol.summoner.entity.Ban;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanRepository extends JpaRepository<Ban, Long> {
}
