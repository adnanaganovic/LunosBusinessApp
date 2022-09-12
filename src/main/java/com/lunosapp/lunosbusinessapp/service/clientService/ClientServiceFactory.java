package com.lunosapp.lunosbusinessapp.service.clientService;

public enum ClientServiceFactory {

    CLIENT_SERVICE_FACTORY(new ClientService());

    private ClientServiceLocal clientServiceLocal;

    ClientServiceFactory(ClientServiceLocal clientServiceLocal) {
        this.clientServiceLocal = clientServiceLocal;
    }

    public ClientServiceLocal getClientServiceLocal() {
        return clientServiceLocal;
    }
}
