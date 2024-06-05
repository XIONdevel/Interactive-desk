package com.xidesk.repository;

import com.xidesk.entity.DeskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeskRepository extends JpaRepository<DeskEntity, Long> {

    Optional<DeskEntity> findDeskEntityById(Long id);
    Optional<DeskEntity> findDeskEntityByName(String name);




}
