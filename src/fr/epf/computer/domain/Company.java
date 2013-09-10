package fr.epf.computer.domain;


import javax.persistence.*;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;
}
