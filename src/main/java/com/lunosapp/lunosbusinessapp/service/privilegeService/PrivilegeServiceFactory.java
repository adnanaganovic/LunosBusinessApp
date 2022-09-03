package com.lunosapp.lunosbusinessapp.service.privilegeService;

public enum PrivilegeServiceFactory {
    PRIVILEGE_SERVICE_FACTORY(new PrivilegeService());

    private PrivilegeServiceLocal privilegeServiceLocal;

    PrivilegeServiceFactory(PrivilegeServiceLocal privilegeServiceLocal){
        this.privilegeServiceLocal = privilegeServiceLocal;
    }

    public PrivilegeServiceLocal getPrivilegeServiceLocal() {
        return privilegeServiceLocal;
    }
}
