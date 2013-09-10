package fr.epf.computer.service.manager;

import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.impl.CompanyServiceImpl;

public enum ServiceManager {

    INSTANCE;

    private CompanyService companyService;

    private ServiceManager() {
        companyService = new CompanyServiceImpl();
    }

    public CompanyService getCompanyService() {
        return companyService;
    }
}
