package com.lunosapp.lunosbusinessapp.service.regionService;

public enum RegionServiceFactory {
    REGION_SERVICE_FACTORY(new RegionService());
    private RegionServiceLocal regionServiceLocal;
    RegionServiceFactory(RegionServiceLocal regionServiceLocal){
        this.regionServiceLocal = regionServiceLocal;
    }

    public RegionServiceLocal getRegionServiceLocal() {
        return regionServiceLocal;
    }
}
