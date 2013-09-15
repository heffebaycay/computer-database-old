package fr.epf.computer.dao.impl;


import fr.epf.computer.dao.CompanyDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Company;
import fr.epf.computer.utils.CompanySortCriteria;
import fr.epf.computer.utils.SortOrder;
import fr.epf.computer.wrapper.SearchWrapper;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchWrapper<Company> getCompanies(int offset, int nbRequested, CompanySortCriteria sortCriterion, SortOrder sortOrder) {
        SearchWrapper<Company> searchWrapper = null;
        List<Company> companies = null;
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            String orderPart = generateOrderPart("c", sortCriterion, sortOrder);

            companies = em.createQuery(
                    "SELECT c FROM Company c order by " + orderPart
            ).setFirstResult(offset)
             .setMaxResults(nbRequested)
             .getResultList()
            ;

            long totalCompaniesCount = (Long) em.createQuery("SELECT COUNT(C) FROM Company c order by " + orderPart).getSingleResult();

            searchWrapper = new SearchWrapper<Company>()
                                .setResults(companies)
                                .setTotalQueryCount(totalCompaniesCount)
                    ;

        } catch ( NoResultException e) {
            // totalCompaniesCount query failed
            searchWrapper = null;
        } finally {
            if( em != null )
                em.close();
        }

        return searchWrapper;
    }

    /**
     * Generates the part of the JPQL that control sorting results
     *
     * Basically:
     *              SELECT c FROM Company c <strong>order by c.name desc</strong>
     *
     *
     * @param entityAlias     The alias of the entity. In the example above, the alias is "c"
     * @param sortCriterion   The criterion that should be used to sort results (hint: it's one of the attributes of
     *                        the Company entity)
     * @param sortOrder       The order in which the results should be sorted (asc / desc)
     * @return                A String containing the "order by" component of the query
     */
    private String generateOrderPart(String entityAlias, CompanySortCriteria sortCriterion, SortOrder sortOrder) {
        String res = entityAlias;

        switch (sortCriterion) {
            case ID:
                res += ".id";
                break;
            case NAME:
                res += ".name";
                break;
            default:
                res += ".id";
        }

        if(sortOrder.equals( SortOrder.DESC )) {
            res += " desc";
        } else {
            res += " asc";
        }

        return res;
    }

    /**
     * {@inheritDoc}
     */
    public SearchWrapper<Company> searchByName(String name, int offset, int nbRequested, CompanySortCriteria sortCriterion, SortOrder sortOrder) {
        SearchWrapper<Company> searchWrapper = null;
        long companyCount = -1;
        List<Company> companies = null;
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            String orderPart = generateOrderPart("c", sortCriterion, sortOrder);

            companies = em.createQuery(
                    "SELECT c FROM Company c WHERE c.name LIKE :compName order by " + orderPart
            ).setParameter("compName", "%" + name + "%")
             .setFirstResult(offset)
             .setMaxResults(nbRequested)
            .getResultList();
            ;

            companyCount = (Long) em.createQuery(
                    "SELECT COUNT(c) FROM Company c WHERE c.name LIKE :compName order by " + orderPart
            ).setParameter("compName", "%" + name + "%" )
            .getSingleResult()
            ;

            searchWrapper = new SearchWrapper<Company>()
                                .setResults(companies)
                                .setTotalQueryCount(companyCount)
            ;


        } catch (NoResultException e) {
            // companyCount query failed
            searchWrapper = null;
        }
        finally {
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

        } catch(NoResultException e) {
            // Query didn't return any Company
            company = null;
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

    /**
     * {@inheritDoc}
     */
    public void update(Company company){
    	EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            em.getTransaction().begin();

            em.merge(company);

            em.getTransaction().commit();
        } finally {
            if( em != null)
                em.close();
        }
    }
}
