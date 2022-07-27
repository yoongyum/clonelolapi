package com.clonelol.champion.repository;

import com.clonelol.champion.entity.Rotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RotationsRepository extends JpaRepository<Rotations,String> {
    Optional<Rotations> findById(String RotationID);
}
