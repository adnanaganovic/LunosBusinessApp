package com.lunosapp.lunosbusinessapp.service.userService;

import com.lunosapp.lunosbusinessapp.entity.User;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

//Cilj je da niko van ovog paketa ne vidi UserService klasu
//Paketno-privatna vidljivost, kada se obriše "public" ispred "class"
//Pošto implementira interface, mora i njegovu metodu (inače se crveni)

class UserService extends AbstractService<User> implements UserServiceLocal {

    public UserService() {
        super(User.class);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        if (password == null || password.isEmpty()) {
            return null;
        }
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        try {
            User user = (User) query.getSingleResult();
//            hash.verify      (kada napravimo hash funkciju, ovdje ubacujemo tu funkciju verify)
            if (user != null && password.equals(user.getPassword())) {
                return user;

            }
        }catch (NoResultException e){
            System.err.println("Not exist user with: " + username);
            return null;
        }
        return null;

    }


    //    EntityManager dobijamo preko klse "Persistance"
    @Override
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }
}
