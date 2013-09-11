package fr.epf.computer.service.manager;

import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.ComputerService;
import fr.epf.computer.service.impl.CompanyServiceImpl;
import fr.epf.computer.service.impl.ComputerServiceImpl;

public enum ServiceManager {

    INSTANCE;

    private CompanyService companyService;

    private ComputerService computerService;

    private ServiceManager() {
        companyService = new CompanyServiceImpl();
        computerService = new ComputerServiceImpl();
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public ComputerService getComputerService() {
        return computerService;
    }
}
