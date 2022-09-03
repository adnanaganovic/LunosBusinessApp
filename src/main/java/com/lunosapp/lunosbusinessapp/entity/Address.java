package com.lunosapp.lunosbusinessapp.entity;

import javax.persistence.*;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "address")
//@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
        @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"),
        @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street"),
        @NamedQuery(name = "Address.findByNumber", query = "SELECT a FROM Address a WHERE a.number = :number"),
        @NamedQuery(name = "Address.findByFloor", query = "SELECT a FROM Address a WHERE a.floor = :floor")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @Column(name = "number")
    private String number;

    @Basic(optional = false)
    @Column(name = "floor")
    private String floor;

    @JoinColumn(name = "id_municipality", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Municipality idMunicipality;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAddress")
    private List<Client> clientList;

    public Address() {
    }

    public Address(Integer id) {
        this.id = id;
    }

    public Address(Integer id, String street, String number, String floor) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.floor = floor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFloor(){return floor;}

    public void setFloor(String floor){this.floor = floor;}

    public Municipality getIdMunicipality() {
        return idMunicipality;
    }

    public void setIdMunicipality(Municipality idMunicipality) {
        this.idMunicipality = idMunicipality;
    }

//    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", floor='" + floor + '\'' +
                ", idMunicipality=" + idMunicipality +
                ", clientList=" + clientList +
                '}';
    }
}

