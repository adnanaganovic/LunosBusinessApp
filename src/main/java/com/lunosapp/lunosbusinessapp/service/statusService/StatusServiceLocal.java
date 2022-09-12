package com.lunosapp.lunosbusinessapp.service.statusService;

import com.lunosapp.lunosbusinessapp.entity.Region;
import com.lunosapp.lunosbusinessapp.entity.Status;

import java.util.List;

public interface StatusServiceLocal {

    List<Status> findAll();
    void removeById(Integer id);

    void create(Status status);
    void edit(Status status);

    void remove(Status status);

    Status find(Integer id);
}
