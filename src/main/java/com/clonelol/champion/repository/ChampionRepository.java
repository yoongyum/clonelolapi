package com.clonelol.champion.repository;

import com.clonelol.champion.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, Long> {

}
