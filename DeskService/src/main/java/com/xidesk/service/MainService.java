package com.xidesk.service;

import com.xidesk.entity.DeskEntity;
import com.xidesk.entity.ObjectEntity;
import com.xidesk.repository.DeskRepository;
import com.xidesk.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainService {

    private final DeskRepository deskRepository;
    private final ObjectRepository objectRepository;

    @Autowired
    public MainService(
            DeskRepository deskRepository,
            ObjectRepository objectRepository
    ) {
        this.deskRepository = deskRepository;
        this.objectRepository = objectRepository;
    }

    public boolean deleteObjectsForDesk(Long userId, Long deskId) {
        Optional<DeskEntity> optionalDesk = deskRepository.findById(deskId);
        if (optionalDesk.isPresent()) {
            DeskEntity desk = optionalDesk.get();
            if (desk.getId() == userId) {
                objectRepository.deleteAllByDeskId(desk);
                return true;
            }
        }
        return false;
    }

    public boolean deleteDesk(Long userId, Long deskId) {
        if (deleteObjectsForDesk(userId, deskId)) {
            deskRepository.deleteById(deskId);
            return true;
        }
        return false;
    }

    public boolean isDeskValid(DeskEntity desk) {
        return !desk.getName().isEmpty() ||
                !desk.getName().isBlank() ||
                desk.getUser_id() != null;
    }

    public void updateObject(ObjectEntity object) {
        objectRepository.save(object);
    }

    public void updateObjects(Iterable<ObjectEntity> object) {
        objectRepository.saveAll(object);
    }

}
