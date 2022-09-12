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
    requires java.base;

//Pakete iz jave moramo otvoriti za javaFX, Hibernate etc.
    opens com.lunosapp.lunosbusinessapp to javafx.fxml, org.jetbrains.jps.builders.java.dependencyView.TypeRepr$ClassType;
    opens com.lunosapp.lunosbusinessapp.entity to org.hibernate.orm.core, java.base;
    opens com.lunosapp.lunosbusinessapp.service to java.base;
    opens com.lunosapp.lunosbusinessapp.service.regionService to java.base;
    opens com.lunosapp.lunosbusinessapp.service.clientService to java.base;

    //Pakete iz jave moramo otvoriti za export
    exports com.lunosapp.lunosbusinessapp.entity;
    exports com.lunosapp.lunosbusinessapp;
    exports com.lunosapp.lunosbusinessapp.service;

}