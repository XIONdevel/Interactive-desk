package com.xidesk.repository;

import com.xidesk.entity.DeskEntity;
import com.xidesk.entity.ObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends JpaRepository<ObjectEntity, Long> {

    void deleteAllByDeskId(DeskEntity deskId);

}
