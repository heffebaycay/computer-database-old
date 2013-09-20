package fr.epf.computer.service;

import fr.epf.computer.domain.Company;
import fr.epf.computer.utils.CompanySortCriteria;
import fr.epf.computer.utils.SortOrder;
import fr.epf.computer.wrapper.SearchWrapper;

import java.util.List;

public interface CompanyService {

    /**
     * @see fr.epf.computer.dao.CompanyDao#getCompanies()
     */
    List<Company> getCompanies();

    /**
     * @see fr.epf.computer.dao.CompanyDao#getCompaniesAlphabetic()
     */
    List<Company> getCompaniesAlphabetic();

    /**
     *
     * @see fr.epf.computer.dao.CompanyDao#searchByName(String, int, int, fr.epf.computer.utils.CompanySortCriteria, fr.epf.computer.utils.SortOrder)
     */
    SearchWrapper<Company> searchByName(String name, int offset, int nbRequested, CompanySortCriteria sortCriterion, SortOrder sortOrder);

    /**
     * @see fr.epf.computer.dao.CompanyDao#create(fr.epf.computer.domain.Company)
     */
    void create(Company company);

    /**
     * @see fr.epf.computer.dao.CompanyDao#findById(long)
     */
    Company findById(long id);

    /**
     * @see fr.epf.computer.dao.CompanyDao#getCompanies(int, int, fr.epf.computer.utils.CompanySortCriteria, fr.epf.computer.utils.SortOrder)
     */
    SearchWrapper<Company> getCompanies(int offset, int nbRequested, CompanySortCriteria sortCriterion, SortOrder sortOrder);

    /**
     * @see fr.epf.computer.dao.CompanyDao#update(fr.epf.computer.domain.Company)
     */
    void update(Company company);
}
