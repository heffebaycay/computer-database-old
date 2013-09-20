package fr.epf.computer.service.impl;

import fr.epf.computer.dao.CompanyDao;
import fr.epf.computer.dao.impl.CompanyDaoImpl;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Company;
import fr.epf.computer.service.CompanyService;
import fr.epf.computer.utils.CompanySortCriteria;
import fr.epf.computer.utils.SortOrder;
import fr.epf.computer.wrapper.SearchWrapper;

import java.util.List;


public class CompanyServiceImpl implements CompanyService {

    CompanyDao companyDao;

    public CompanyServiceImpl() {
        companyDao = DaoManager.INSTANCE.getCompanyDao();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Company> getCompaniesAlphabetic() {
        return companyDao.getCompaniesAlphabetic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

    /**
     * {@inheritDoc}
     */
    public SearchWrapper<Company> searchByName(String name, int offset, int nbRequested, CompanySortCriteria sortCriterion, SortOrder sortOrder) {
        return companyDao.searchByName(name, offset, nbRequested, sortCriterion, sortOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Company company) {
        companyDao.create(company);
    }

    /**
     * {@inheritDoc}
     */
    public Company findById(long id) {
        return companyDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public SearchWrapper<Company> getCompanies(int offset, int nbRequested, CompanySortCriteria sortCriterion, SortOrder sortOrder) {
        return companyDao.getCompanies(offset, nbRequested, sortCriterion, sortOrder);
    }

    /**
     * {@inheritDoc}
     */
    public void update(Company company){
    	companyDao.update(company);
    }
}
