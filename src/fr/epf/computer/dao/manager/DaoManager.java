package fr.epf.computer.dao.manager;

import fr.epf.computer.dao.CompanyDao;
import fr.epf.computer.dao.impl.CompanyDaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum DaoManager {

    INSTANCE;

    private CompanyDao companyDao;

    private EntityManagerFactory emf;

    private DaoManager() {
        emf = Persistence.createEntityManagerFactory("computerDBPU");
        companyDao = new CompanyDaoImpl();
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


}
