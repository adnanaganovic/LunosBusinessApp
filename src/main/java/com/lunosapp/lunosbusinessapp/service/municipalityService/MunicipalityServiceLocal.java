package com.lunosapp.lunosbusinessapp.service.municipalityService;

import com.lunosapp.lunosbusinessapp.entity.Municipality;

import java.util.List;

public interface MunicipalityServiceLocal {
    public List<Municipality> findAll();
    void removeById(Integer id);

    void create(Municipality municipality);
    void edit(Municipality municipality);

    void remove(Municipality municipality);

    Municipality find(Integer id);
}
