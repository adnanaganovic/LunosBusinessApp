package com.lunosapp.lunosbusinessapp.service.userService;

import com.lunosapp.lunosbusinessapp.Controller;
import com.lunosapp.lunosbusinessapp.entity.User;
import com.lunosapp.lunosbusinessapp.service.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
//        String plainPassword = Controller.instance().getLoginView().getPassword();
//
//        private static String generateHashedPassword(String passwordToHash){   // PROFA
//            String generatedPassword = null;
//            try {
//                MessageDigest md = MessageDigest.getInstance("MD5");
//                md.update(passwordToHash.getBytes());
//                byte[] bytes = md.digest();
//
//                StringBuilder sb = new StringBuilder();
//                for(int i=0; i<bytes.length; i++){
//                    sb.append(Integer.toString((bytes[i]&0xff) + 0x100, 16).substring(1));
//                }
//                generatedPassword = sb.toString();
//            }catch (NoSuchAlgorithmException e){
//                System.err.println(e.getMessage());
//            }
//            return generatedPassword;
//        }







    //    EntityManager dobijamo preko klse "Persistance"
    @Override
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
    }
}
