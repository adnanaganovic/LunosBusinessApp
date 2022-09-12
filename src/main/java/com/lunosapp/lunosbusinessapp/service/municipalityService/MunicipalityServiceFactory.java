package com.lunosapp.lunosbusinessapp.service.municipalityService;

public enum MunicipalityServiceFactory {
    MUNICIPALITY_SERVICE_FACTORY(new MunicipalityService());
    private MunicipalityServiceLocal municipalityServiceLocal;

    MunicipalityServiceFactory(MunicipalityServiceLocal municipalityServiceLocal){
        this.municipalityServiceLocal = municipalityServiceLocal;
    }

    public MunicipalityServiceLocal getMunicipalityServiceLocal() {
        return municipalityServiceLocal;
    }
}
