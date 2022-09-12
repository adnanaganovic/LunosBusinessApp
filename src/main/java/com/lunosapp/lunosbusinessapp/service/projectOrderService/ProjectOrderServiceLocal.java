package com.lunosapp.lunosbusinessapp.service.projectOrderService;

import com.lunosapp.lunosbusinessapp.entity.Privilege;
import com.lunosapp.lunosbusinessapp.entity.ProjectOrder;
import com.lunosapp.lunosbusinessapp.entity.Region;

import java.util.List;

public interface ProjectOrderServiceLocal {

    public List<ProjectOrder> findAll();
    void removeById(Integer id);

    void create(ProjectOrder projectOrder);
    void edit(ProjectOrder projectOrder);

    void remove(ProjectOrder projectOrder);

    ProjectOrder find(Integer id);
}
