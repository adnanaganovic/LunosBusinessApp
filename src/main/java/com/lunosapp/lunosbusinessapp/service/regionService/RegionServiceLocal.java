package com.lunosapp.lunosbusinessapp.service.regionService;

import com.lunosapp.lunosbusinessapp.entity.Region;

import java.util.List;

public interface RegionServiceLocal {
    List<Region> findAll();
    void removeById(Integer id);

    void create(Region region);
    void edit(Region region);

    void remove(Region region);

    Region find(Integer id);
}
