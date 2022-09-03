module com.lunosapp.lunosbusinessapp {

//    MODULI - pošto je javaFX razložena na više paketa, moramo navesti koje pakete sve koristimo u projektu
    requires javafx.controls;
    requires java.naming;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.persistence;
    requires java.xml.bind;
    requires java.sql;
    requires java.sql.rowset;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires javax.annotation;

//Pakete iz jave moramo otvoriti za javaFX, Hibernate etc.
    opens com.lunosapp.lunosbusinessapp to javafx.fxml;
    opens com.lunosapp.lunosbusinessapp.entity to org.hibernate.orm.core;

    //Pakete iz jave moramo otvoriti za export
    exports com.lunosapp.lunosbusinessapp.entity;
    exports com.lunosapp.lunosbusinessapp;

}