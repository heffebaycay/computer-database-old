package fr.epf.computer.dao.impl;


import fr.epf.computer.dao.CompanyDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Company;
import fr.epf.computer.wrapper.SearchWrapper;

import javax.persistence.EntityManager;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    public CompanyDaoImpl() {

    }


    /**
     * {@inheritDoc}
     */
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

    @Override
    public SearchWrapper<Company> getCompanies(int offset, int nbRequested) {
        SearchWrapper<Company> searchWrapper = null;
        List<Company> companies = null;
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            companies = em.createQuery(
                    "SELECT c FROM Company c"
            ).setFirstResult(offset)
             .setMaxResults(nbRequested)
             .getResultList()
            ;

            long totalCompaniesCount = (Long) em.createQuery("SELECT COUNT(C) FROM Company c").getSingleResult();

            searchWrapper = new SearchWrapper<Company>()
                                .setResults(companies)
                                .setTotalQueryCount(totalCompaniesCount)
                    ;

        } finally {
            if( em != null )
                em.close();
        }

        return searchWrapper;
    }

    /**
     * {@inheritDoc}
     */
    public SearchWrapper<Company> searchByName(String name, int offset, int nbRequested) {
        SearchWrapper<Company> searchWrapper = null;
        long companyCount = -1;
        List<Company> companies = null;
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            companies = em.createQuery(
                    "SELECT c FROM Company c WHERE c.name LIKE :compName"
            ).setParameter("compName", "%" + name + "%")
             .setFirstResult(offset)
             .setMaxResults(nbRequested)
            .getResultList();
            ;

            companyCount = (Long) em.createQuery(
                    "SELECT COUNT(c) FROM Company c WHERE c.name LIKE :compName"
            ).setParameter("compName", "%" + name + "%" )
            .getSingleResult()
            ;

            searchWrapper = new SearchWrapper<Company>()
                                .setResults(companies)
                                .setTotalQueryCount(companyCount)
            ;


        } finally {
            if( em != null)
                em.close();
        }

        return searchWrapper;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Company findById(long id) {
        EntityManager em = null;
        Company company = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            company = (Company)  em.createQuery(
                    "SELECT c FROM Company c WHERE c.id = :id"
            ).setParameter("id", id)
            .getSingleResult();
            ;

        } finally {
            if (em != null)
                em.close();
        }

        return company;
    }

    /**
     * {@inheritDoc}
     */
    public void create(Company company) {
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            em.getTransaction().begin();

            em.persist(company);

            em.getTransaction().commit();
        } finally {
            if( em != null)
                em.close();
        }
    }
}
