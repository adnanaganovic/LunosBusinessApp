package com.lunosapp.lunosbusinessapp.service.statusService;

import com.lunosapp.lunosbusinessapp.entity.Status;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class StatusService extends AbstractService<Status> implements StatusServiceLocal {
    public StatusService() {
        super(Status.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }
}
