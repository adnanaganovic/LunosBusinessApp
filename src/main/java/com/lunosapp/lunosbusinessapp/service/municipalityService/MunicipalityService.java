package com.lunosapp.lunosbusinessapp.service.municipalityService;

import com.lunosapp.lunosbusinessapp.entity.Municipality;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MunicipalityService extends AbstractService<Municipality> implements MunicipalityServiceLocal {
    public MunicipalityService() {
        super(Municipality.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }
}
