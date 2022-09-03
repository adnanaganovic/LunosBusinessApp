package com.lunosapp.lunosbusinessapp.service;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

//EntityManager je zamjena za session (kod web-a) bez njega ne možemo ostvariti vezu sa bazom

public abstract class AbstractService<E> {

    private Class<E> entityClass;

    public AbstractService(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

//    public void edit(E entity){
//        getEntityManager().merge(entity);
//    }
//    public void remove(E entity){
//        getEntityManager().remove(entity);
//    }
//    public E find(Object id){
//        return getEntityManager().find(entityClass, id);
//    }
//    public void create(E entity){
//        getEntityManager().persist(entity);
//    }   //OVAKO NAPISANA METODA NIJE RADILA (potrebno je navesti getTransaction().begin -> persist -> entityManager.flush() -> getTransaction().commit();)

    public void create(E entity){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        if(!entityManager.contains(entity)) {
            entityManager.persist(entity);
            entityManager.flush();
        }
        entityManager.getTransaction().commit();
    }

    public void edit(E entity){
//        EntityManager entityManager = getEntityManager();
//        entityManager.getTransaction().begin();
        getEntityManager().merge(entity);
//        getEntityManager().getTransaction().commit();
    }
    public void remove(E entity){                              //SE 1/3 objasnjenje
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        if (!entityManager.contains(entity)){
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    public E find(Integer id){
//        EntityManager entityManager = getEntityManager();
//        entityManager.getTransaction().begin();
        return getEntityManager().find(entityClass, id);
    }


    public List<E> findAll() {
        CriteriaQuery criteriaQuery = (CriteriaQuery) getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }


    public void removeById(Integer id) {
        EntityManager entityManager = getEntityManager();
        // begin transaction
        entityManager.getTransaction().begin();
        E entity = entityManager.find(entityClass, id);
        entityManager.remove(entity);
        entityManager.flush();
        // commit transaction at all
        entityManager.getTransaction().commit();
    }

}
