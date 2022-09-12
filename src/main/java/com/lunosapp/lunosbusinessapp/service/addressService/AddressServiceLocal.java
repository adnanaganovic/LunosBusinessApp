package com.lunosapp.lunosbusinessapp.service.addressService;

import com.lunosapp.lunosbusinessapp.entity.Address;
import com.lunosapp.lunosbusinessapp.entity.Municipality;

import java.util.List;

public interface AddressServiceLocal {
    List<Address> findAll();
    void removeById(Integer id);

    void create(Address municipality);
    void edit(Address address);

    void remove(Address address);

    Address find(Integer id);
}
