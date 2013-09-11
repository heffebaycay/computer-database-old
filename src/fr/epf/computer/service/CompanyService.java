package fr.epf.computer.service;

import fr.epf.computer.domain.Company;

import java.util.List;

public interface CompanyService {

    /**
     * @see fr.epf.computer.dao.CompanyDao#getCompanies()
     */
    List<Company> getCompanies();


    /**
     *
     * @see fr.epf.computer.dao.CompanyDao#searchByName(String)
     */
    List<Company> searchByName(String name);

    /**
     * @see fr.epf.computer.dao.CompanyDao#create(fr.epf.computer.domain.Company)
     */
    void create(Company company);

    /**
     * @see fr.epf.computer.dao.CompanyDao#findById(long)
     */
    Company findById(long id);
}
