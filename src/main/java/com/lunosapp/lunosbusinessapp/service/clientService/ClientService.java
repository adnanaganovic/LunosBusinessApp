package com.lunosapp.lunosbusinessapp.service.clientService;

import com.lunosapp.lunosbusinessapp.entity.Client;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ClientService extends AbstractService<Client> implements ClientServiceLocal {
    public ClientService() {

        super(Client.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }
}
