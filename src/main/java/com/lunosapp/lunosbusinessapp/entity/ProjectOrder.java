package com.lunosapp.lunosbusinessapp.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "project_order")
//@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ProjectOrder.findAll", query = "SELECT o FROM ProjectOrder o"),
        @NamedQuery(name = "ProjectOrder.findById", query = "SELECT o FROM ProjectOrder o WHERE o.id = :id"),
        @NamedQuery(name = "ProjectOrder.findByFromDate", query = "SELECT o FROM ProjectOrder o WHERE o.installationDate = :installation_date"),
//        @NamedQuery(name = "ProjectOrder.findByPrice", query = "SELECT o FROM ProjectOrder o WHERE o.price = :price"),
        @NamedQuery(name = "ProjectOrder.findByStatus", query = "SELECT o FROM ProjectOrder o WHERE o.status = :status")})

public class ProjectOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
//    @Basic(optional = false)
//    @Column(name = "installation_date")
//    @Temporal( TemporalType.TIMESTAMP)
//    private java.util.Date installationDate;  //Promjenio i u bazi sa DATETIME u INT
    @Basic(optional = false)
    @Column(name = "installation_date")
    private Integer installationDate;

//    @Basic(optional = false)
//    @Column(name = "price")
//    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "status")
    private Integer status;
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

    public ProjectOrder(Integer id, Client idClient, Project idProject, Integer installationDate) {  // public ProjectOrder(Integer id, Client idClient, Project idProject,  Date installationDate, int status) {
        this.id = id;
        this.idClient=idClient;
        this.idProject=idProject;
        this.installationDate = installationDate;
//        this.price = price;
//        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Integer installationDate) {
        this.installationDate = installationDate;
    }
    
//    public BigDecimal getPrice() {
//        return price;
//    }

//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
//
    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Client getIdClient() {
        return idClient;
    }
    public Project getIdProject() {
        return idProject;
    }
    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }
    public void setClientId(Client idClient) {
        this.idClient = idClient;
    }
//    public void setIdProject(Project parseInt) {}

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
