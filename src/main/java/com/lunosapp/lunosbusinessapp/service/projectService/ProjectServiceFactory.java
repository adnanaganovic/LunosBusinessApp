package com.lunosapp.lunosbusinessapp.service.projectService;

public enum ProjectServiceFactory {

    PROJECT_SERVICE_FACTORY(new ProjectService());

    private ProjectService projectService;

    ProjectServiceFactory(ProjectService projectService){
        this.projectService = projectService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }
}
