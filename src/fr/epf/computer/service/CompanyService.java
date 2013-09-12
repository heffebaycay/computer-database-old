package fr.epf.computer.service;

import fr.epf.computer.domain.Company;
import fr.epf.computer.wrapper.SearchWrapper;

import java.util.List;

public interface CompanyService {

    /**
     * @see fr.epf.computer.dao.CompanyDao#getCompanies()
     */
    List<Company> getCompanies();


    /**
     *
     * @see fr.epf.computer.dao.CompanyDao#searchByName(String, int, int)
     */
    SearchWrapper<Company> searchByName(String name, int offset, int nbRequested);

    /**
     * @see fr.epf.computer.dao.CompanyDao#create(fr.epf.computer.domain.Company)
     */
    void create(Company company);

    /**
     * @see fr.epf.computer.dao.CompanyDao#findById(long)
     */
    Company findById(long id);

    SearchWrapper<Company> getCompanies(int offset, int nbRequested);
    
    void update(Company company);
}
