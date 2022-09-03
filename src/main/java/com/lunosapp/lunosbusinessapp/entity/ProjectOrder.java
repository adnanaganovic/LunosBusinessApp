package com.lunosapp.lunosbusinessapp.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "project_order")
//@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ProjectOrder.findAll", query = "SELECT r FROM ProjectOrder r"),
        @NamedQuery(name = "ProjectOrder.findById", query = "SELECT r FROM ProjectOrder r WHERE r.id = :id"),
        @NamedQuery(name = "ProjectOrder.findByFromDate", query = "SELECT r FROM ProjectOrder r WHERE r.installationDate = :installation_date"),
//        @NamedQuery(name = "ProjectOrder.findByPrice", query = "SELECT r FROM ProjectOrder r WHERE r.price = :price"),
        @NamedQuery(name = "ProjectOrder.findByStatus", query = "SELECT r FROM ProjectOrder r WHERE r.status = :status")})

public class ProjectOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "installation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date installationDate;

//    @Basic(optional = false)
//    @Column(name = "price")
//    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinTable(name = "projectorder_client", joinColumns = {
            @JoinColumn(name = "id_project_order", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "id_client", referencedColumnName = "id")})
    @ManyToMany
    private List<Client> clientList;
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Client idClient;
    @JoinColumn(name = "id_project", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Project idProject;

    public ProjectOrder() {
    }

    public ProjectOrder(Integer id) {
        this.id = id;
    }

    public ProjectOrder(Integer id, Date installationDate, int status) {
        this.id = id;
        this.installationDate = installationDate;
//        this.price = price;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }



//    public BigDecimal getPrice() {
//        return price;
//    }

//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
//
//    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Client getIdClient() {
        return idClient;
    }
//    public Client getIdClient() {
//        return idClient;
//    }

//    public void setIdClient(int idClient) {
//        this.idClient = idClient;
//    }
//    public void setIdClient(int idClient) {
//        this.idClient = idClient;
//    }

    public Project getIdProject() {
        return idProject;
    }
//    public Project getIdProject() {
//        return idProject;
//    }


//    public void setIdProject(int idProject) {
//        this.idProject = idProject;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectOrder)) {
            return false;
        }
        ProjectOrder other = (ProjectOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectOrder{" +
                "id=" + id +
                ", installationDate=" + installationDate +
//                ", price=" + price +
                ", status=" + status +
                ", clientList=" + clientList +
                ", idClient=" + idClient +
                ", idProject=" + idProject +
                '}';
    }
}
