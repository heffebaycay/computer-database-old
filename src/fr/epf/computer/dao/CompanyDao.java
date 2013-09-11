package fr.epf.computer.dao;


import fr.epf.computer.domain.Company;

import java.util.List;

public interface CompanyDao {

    List<Company> getCompanies();
    List<Company> searchByName(String name);
    void create(Company company);

}
