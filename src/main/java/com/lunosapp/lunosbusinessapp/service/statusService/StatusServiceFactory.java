package com.lunosapp.lunosbusinessapp.service.statusService;

public enum StatusServiceFactory {

    STATUS_SERVICE_FACTORY(new StatusService());
    private StatusServiceLocal statusServiceLocal;


    StatusServiceFactory(StatusServiceLocal statusServiceLocal) {
        this.statusServiceLocal = statusServiceLocal;
    }

    public StatusServiceLocal getStatusServiceLocal() {
        return statusServiceLocal;
    }
}
