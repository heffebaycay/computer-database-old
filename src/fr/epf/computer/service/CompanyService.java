package fr.epf.computer.service;

import fr.epf.computer.domain.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();
    List<Company> searchByName(String name);
    void create(Company company);
    Company findById(long id);
}
