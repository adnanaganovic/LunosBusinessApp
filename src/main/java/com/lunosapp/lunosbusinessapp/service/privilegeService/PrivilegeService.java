package com.lunosapp.lunosbusinessapp.service.privilegeService;

import com.lunosapp.lunosbusinessapp.entity.Privilege;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

class PrivilegeService extends AbstractService<Privilege> implements PrivilegeServiceLocal{

    public PrivilegeService() {
        super(Privilege.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }

//    @Override
//    public void create(Privilege privilege) {
//
//    }

}
