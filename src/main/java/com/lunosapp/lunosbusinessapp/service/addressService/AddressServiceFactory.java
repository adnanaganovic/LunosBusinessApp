package com.lunosapp.lunosbusinessapp.service.addressService;

public enum AddressServiceFactory {
    ADDRESS_SERVICE_FACTORY(new AddressService());

    private AddressServiceLocal addressServiceLocal;


    AddressServiceFactory(AddressServiceLocal addressServiceLocal) {
        this.addressServiceLocal = addressServiceLocal;
    }

    public AddressServiceLocal getAddressServiceLocal() {
        return addressServiceLocal;
    }
}
