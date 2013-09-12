package fr.epf.computer.dao;


import fr.epf.computer.domain.Company;
import fr.epf.computer.wrapper.SearchWrapper;

import java.util.List;

public interface CompanyDao {

    /**
     * This method returns the list of all companies stored in the DataSource.
     *
     * @return  The list of all companies
     */
    List<Company> getCompanies();

    /**
     * Searches the DataSource for companies whose names match the name argument.
     * This method is case insensitive.
     *
     * @param name Any substring of the name of the company/companies we're looking for
     * @return      A list of companies who match the given name.
     */
    SearchWrapper<Company> searchByName(String name, int offset, int nbRequested);

    /**
     * Create a company in the DataSource based on an instance of Company
     *
     * @param company The company object that we want to be persisted
     */
    void create(Company company);

    /**
     * Find a Company from the DataSource based on its id
     *
     * @param id The id of the company
     * @return   The company which id matches the id argument
     */
    Company findById(long id);


    SearchWrapper<Company> getCompanies(int offset, int nbRequested);
    
    void update(Company company);

}
