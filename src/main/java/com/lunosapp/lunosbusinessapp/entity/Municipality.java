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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Grupa1
 */
@Entity
@Table(name = "municipality")
//@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Municipality.findAll", query = "SELECT m FROM Municipality m"),
        @NamedQuery(name = "Municipality.findById", query = "SELECT m FROM Municipality m WHERE m.id = :id"),
        @NamedQuery(name = "Municipality.findByName", query = "SELECT m FROM Municipality m WHERE m.name = :name")})

public class Municipality implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMunicipality")
    private List<Address> addressList;
    @JoinColumn(name = "id_region", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Region idRegion;

    public Municipality() {
    }

    public Municipality(Integer id) {
        this.id = id;
    }

    public Municipality(Integer id, String name) {
        this.id = id;
        this.name = name;
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

//    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Region getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Region idRegion) {
        this.idRegion = idRegion;
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
        if (!(object instanceof Municipality)) {
            return false;
        }
        Municipality other = (Municipality) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name+", ID["+id+"]";
//        return id+" "+name+" "+ idRegion+" ";
//        return "Municipality{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", addressList=" + addressList +
//                ", idRegion=" + idRegion +
//                '}';
    }
}
