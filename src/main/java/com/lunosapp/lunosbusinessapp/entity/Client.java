package com.lunosapp.lunosbusinessapp.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Client nikako ne ide, ima ne logičnih errora
 */
@Entity
@Table(name = "client")
//@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
        @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
        @NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name"),
        @NamedQuery(name = "Client.findBySurname", query = "SELECT c FROM Client c WHERE c.surname = :surname"),
        @NamedQuery(name = "Client.findByPhone", query = "SELECT c FROM Client c WHERE c.phone = :phone"),
        @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email"),})
public class Client implements Serializable {   //NE RADI - NE RAZUMIJEM

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;

    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)   //E-MAIL KOLONA NE MOŽE da se setuje
    @Column(name = "email")
    private String email;
    @ManyToMany(mappedBy = "clientList")
    private List<ProjectOrder> projectOrderList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient")
    private List<ProjectOrder> projectOrderList1;
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Address idAddress;
    public Client() {
    }

    public Client (Integer id) {
        this.id = id;
    }

    public Client(Integer id,  String name, String surname, String phone, String email) {   //E-MAIL KOLONA NE MOŽE
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email; //E-MAIL KOLONA NE MOŽE
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {              //E-MAIL KOLONA NE MOŽE
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

//        @XmlTransient
    public List<ProjectOrder> getProjectOrderList() {
        return projectOrderList;
    }
//
    public void setProjectOrderList(List<ProjectOrder> projectOrderList) {
        this.projectOrderList = projectOrderList;
    }
//
////    @XmlTransient
//    public List<ProjectOrder> getProjectOrderList1() {
//        return projectOrderList1;
//    }
//    public void setProjectOrderList1(List<ProjectOrder> projectOrderList1) {
//        this.projectOrderList1 = projectOrderList1;
//    }
//
    public Address getIdAddress() {
        return idAddress;
    }
//
    public void setIdAddress(Address idAddress) {
        this.idAddress = idAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name + " " + surname +
                ", ID[" + id+ "], " + idAddress.getIdMunicipality().getName();
    }
}
