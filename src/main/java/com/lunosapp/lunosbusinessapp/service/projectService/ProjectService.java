package com.lunosapp.lunosbusinessapp.service.projectService;

import com.lunosapp.lunosbusinessapp.entity.Project;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ProjectService extends AbstractService<Project> {

    ProjectService() {                           //OVO SE AUTOMATSKI IMPLEMENTIRA KADA SE EXTENDA ABSTRACSERVICE, KONSTRUKTOR PAKETNO VIDLJIV
        super(Project.class);
    }

    @Override
    protected EntityManager getEntityManager() {             //OVO SE AUTOMATSKI IMPLEMENTIRA KADA SE EXTENDA ABSTRACSERVICE, SAMO ISPRAVITI UNUTAR METODE
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }
}
