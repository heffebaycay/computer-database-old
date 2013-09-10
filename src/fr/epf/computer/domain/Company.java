package fr.epf.computer.domain;


import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( name = "company")
@NamedQuery (name = "findAllCompanies", query = "Select c from Company c")
public class Company {

    public Company() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private int id;

    private String nom;
}
