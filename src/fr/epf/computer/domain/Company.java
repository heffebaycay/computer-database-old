package fr.epf.computer.domain;


import javax.persistence.*;

@Entity
@Table( name = "company")
@NamedQuery (name = "findAllCompanies", query = "Select c from Company c")
public class Company {

    public Company() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    private long id;

    @Column(name = "name")
    private String name;

    public static class Builder {
        private Company company;

        public Builder() {
            company = new Company();
        }

        public Builder id(long id) {
            company.setId(id);
            return this;
        }

        public Builder name(String name) {
            company.setName(name);
            return this;
        }

        public Company build() {
            return company;
        }

    }
}
