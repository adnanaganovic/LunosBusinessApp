package com.lunosapp.lunosbusinessapp.service.projectOrderService;

public enum ProjectOrderServiceFactory {

    PROJECT_ORDER_SERVICE_FACTORY(new ProjectOrderService());

    private ProjectOrderService projectOrderService;


    ProjectOrderServiceFactory(ProjectOrderService projectOrderService) {
        this.projectOrderService = projectOrderService;
    }

    public ProjectOrderService getProjectOrderService() {
        return projectOrderService;
    }
}
