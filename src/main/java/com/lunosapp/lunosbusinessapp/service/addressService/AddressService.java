package com.lunosapp.lunosbusinessapp.service.addressService;

import com.lunosapp.lunosbusinessapp.entity.Address;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class AddressService extends AbstractService<Address> implements AddressServiceLocal {
    public AddressService() {
        super(Address.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }
}
