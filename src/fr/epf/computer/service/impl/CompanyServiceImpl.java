package fr.epf.computer.service.impl;

import fr.epf.computer.dao.CompanyDao;
import fr.epf.computer.dao.impl.CompanyDaoImpl;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Company;
import fr.epf.computer.service.CompanyService;

import java.util.List;


public class CompanyServiceImpl implements CompanyService {

    CompanyDao companyDao;

    public CompanyServiceImpl() {
        companyDao = DaoManager.INSTANCE.getCompanyDao();
    }

    @Override
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

    public List<Company> searchByName(String name) {
        return companyDao.searchByName(name);
    }

    @Override
    public void create(Company company) {
        companyDao.create(company);
    }
}
