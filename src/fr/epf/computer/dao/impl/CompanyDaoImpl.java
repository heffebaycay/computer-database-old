package fr.epf.computer.dao.impl;


import fr.epf.computer.dao.CompanyDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Company;

import javax.persistence.EntityManager;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    public CompanyDaoImpl() {

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> getCompanies() {
        EntityManager em = null;
        List<Company> companies = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            companies = em.createNamedQuery("findAllCompanies").getResultList();
        } finally {
            if(em != null)
                em.close();
        }

        return companies;
    }

    public List<Company> searchByName(String name) {
        List<Company> companies = null;
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            companies = em.createQuery(
                    "SELECT c FROM Company c WHERE c.name LIKE :compName"
            ).setParameter("compName", "%" + name + "%")
            .getResultList();
            ;
        } finally {
            if( em != null)
                em.close();
        }

        return companies;
    }
}
