package com.lunosapp.lunosbusinessapp.service.clientService;

import com.lunosapp.lunosbusinessapp.entity.Client;

import java.util.List;

public interface ClientServiceLocal {

    List<Client> findAll();

    void create(Client client);

    void removeById(Integer id);

    void edit(Client client);

    void remove(Client client);

    Client find(Integer id);
}
