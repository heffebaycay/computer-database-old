package fr.epf.computer.dao;


import fr.epf.computer.domain.Company;
import fr.epf.computer.utils.CompanySortCriteria;
import fr.epf.computer.utils.SortOrder;
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
     * @param name           Any substring of the name of the company/companies we're looking for
     * @param offset         The offset of the first company element that should be returned
     * @param nbRequested    The total number of elements requested
     * @param sortCriterion  The criterion that should be used to sort results (hint: it's one of the attributes of
     *                       the Company entity)
     * @param sortOrder      The order in which the results should be sorted
     * @return               A SearchWrapper element containing both the results as a List and the total number of
     *                       elements matched by the query
     */
    SearchWrapper<Company> searchByName(String name, int offset, int nbRequested, CompanySortCriteria sortCriterion, SortOrder sortOrder);

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

    /**
     * Queries the DataSource for nbRequested elements starting at the offset by the parameter with the same name.
     *
     * @param offset            The offset of the first Company element that should be returned
     * @param nbRequested       The total number of elements requested
     * @param sortCriterion     The criterion that should be used to sort results (hint: it's one of the attributes of
     *                          the Company entity)
     * @param sortOrder         The order in which the results should be sorted
     * @return                  A SearchWrapper element containing both the results as a List and the total number of
     *                          elements matched by the query
     */
    SearchWrapper<Company> getCompanies(int offset, int nbRequested, CompanySortCriteria sortCriterion, SortOrder sortOrder);

    /**
     * Update a Company already stored in the DataSource.
     *
     * @param company The updated Company object
     */
    void update(Company company);

}
