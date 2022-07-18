package com.clonelol.repository;

import com.clonelol.entity.Rotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RotationsRepository extends JpaRepository<Rotations,String> {

}
