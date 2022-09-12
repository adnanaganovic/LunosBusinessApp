package com.lunosapp.lunosbusinessapp.service.regionService;

import com.lunosapp.lunosbusinessapp.entity.Region;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class RegionService extends AbstractService<Region> implements RegionServiceLocal {

    public RegionService() {
        super(Region.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }
}
