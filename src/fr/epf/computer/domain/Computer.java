package fr.epf.computer.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "computer")
@NamedQuery (name = "findAllComputers", query = "Select c from Computer c")
public class Computer {

    @Id
    @GeneratedValue
    private int id;

    @Column( name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "introduced")
    private Date introduced;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "discontinued")
    private Date discontinued;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;


    public Computer() {

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

    public Date getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Date introduced) {
        this.introduced = introduced;
    }

    public Date getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Date discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
