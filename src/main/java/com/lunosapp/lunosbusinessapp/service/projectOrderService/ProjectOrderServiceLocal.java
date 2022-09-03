package com.lunosapp.lunosbusinessapp.service.projectOrderService;

import com.lunosapp.lunosbusinessapp.entity.Privilege;
import com.lunosapp.lunosbusinessapp.entity.ProjectOrder;

import java.util.List;

public interface ProjectOrderServiceLocal {

    List<ProjectOrder> findAll();
}
