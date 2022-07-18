package com.clonelol.repository;

import com.clonelol.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, Long> {

}
