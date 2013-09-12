package fr.epf.computer.dao.manager;

import fr.epf.computer.dao.CompanyDao;
import fr.epf.computer.dao.ComputerDao;
import fr.epf.computer.dao.impl.CompanyDaoImpl;
import fr.epf.computer.dao.impl.ComputerDaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum DaoManager {

    INSTANCE;

    private CompanyDao companyDao;

    private ComputerDao computerDao;

    private EntityManagerFactory emf;

    private DaoManager() {
        emf = Persistence.createEntityManagerFactory("computerDBPU");
        companyDao = new CompanyDaoImpl();
        computerDao = new ComputerDaoImpl();
    }

    /**
     * Returns an instance of the DAO for the Company entity
     * @return
     */
    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    /**
     * Returns an instance of the DAO for the the Computer entity
     * @return
     */
    public ComputerDao getComputerDao() {
        return computerDao;
    }

    /**
     * Returns an instance of the Entity Manager
     * @return
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


}
