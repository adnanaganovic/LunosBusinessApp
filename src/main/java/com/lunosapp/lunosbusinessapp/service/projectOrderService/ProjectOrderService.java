package com.lunosapp.lunosbusinessapp.service.projectOrderService;

import com.lunosapp.lunosbusinessapp.entity.ProjectOrder;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;

public class ProjectOrderService extends AbstractService<ProjectOrder> implements ProjectOrderServiceLocal{

    public ProjectOrderService() {
        super(ProjectOrder.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return null;
    }
}
