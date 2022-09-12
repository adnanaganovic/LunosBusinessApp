package com.lunosapp.lunosbusinessapp.service.privilegeService;

import com.lunosapp.lunosbusinessapp.entity.Privilege;
import com.lunosapp.lunosbusinessapp.entity.User;

import java.util.List;

public interface PrivilegeServiceLocal {
    List<Privilege> findAll();
    void removeById(Integer id);
    void create(Privilege privilege);
    void edit(Privilege privilege);

    void remove(Privilege privilege);

    Privilege find(Integer id);

}
