package fr.epf.computer.dao.impl;


import fr.epf.computer.dao.ComputerDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Computer;
import fr.epf.computer.wrapper.SearchWrapper;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ComputerDaoImpl implements ComputerDao {

    @Override
    public SearchWrapper<Computer> getComputers(int offset, int nbRequested) {

        SearchWrapper<Computer> searchWrapper = null;
        List<Computer> computers;
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            computers = em.createQuery(
                    "SELECT c FROM Computer c"
            ).setFirstResult(offset)
            .setMaxResults(nbRequested)
            .getResultList()
            ;

            long totalComputerCount = (Long) em.createQuery("SELECT COUNT (c) FROM Computer c").getSingleResult();

            searchWrapper = new SearchWrapper<Computer>()
                                .setResults(computers)
                                .setTotalQueryCount(totalComputerCount);

        } catch (NoResultException e) {
            // totalComputerCount query failed
            searchWrapper = null;
        } finally {
            if( em != null )
                em.close();
        }

        return searchWrapper;
    }

    public ComputerDaoImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchWrapper<Computer> searchByName(String name, int offset, int nbRequested) {
        List<Computer> computers = null;
        long computerCount = -1;
        SearchWrapper<Computer> searchWrapper = null;

        EntityManager em = null;
        try {
            em = DaoManager.INSTANCE.getEntityManager();

            computers = em.createQuery(
                    "SELECT  c FROM Computer c WHERE c.name LIKE :compName"
            ).setParameter("compName", "%" + name + "%")
             .setFirstResult(offset)
             .setMaxResults(nbRequested)
            .getResultList();

            computerCount = (Long) em.createQuery(
                    "SELECT COUNT(c) FROM Computer c WHERE c.name LIKE :compName"
            ).setParameter("compName", "%" + name + "%")
                    .getSingleResult();

            searchWrapper = new SearchWrapper<Computer>()
                                .setResults(computers)
                                .setTotalQueryCount(computerCount);

        } catch (NoResultException e) {
            // computerCount query failed
            searchWrapper = null;
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
    public void update(Computer computer) {
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            em.getTransaction().begin();

            em.merge(computer);

            em.getTransaction().commit();
        } finally {
            if( em != null)
                em.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Computer findById(long id) {
        EntityManager em = null;
        Computer computer;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            computer = (Computer) em.createQuery(
                    "SELECT c FROM Computer c WHERE c.id = :computerId"
            ).setParameter("computerId", id)
             .getSingleResult();

        } catch (NoResultException e) {
            // No result found in the DataSource
            computer = null;

        } finally {
            if( em != null )
                em.close();
        }

        return computer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Computer> getComputers() {
        EntityManager em = null;
        List<Computer> computers = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            computers = em.createNamedQuery("findAllComputers").getResultList();
        } finally {
            if(em != null)
                em.close();
        }

        return computers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Computer computer) {
        EntityManager em = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            em.getTransaction().begin();

            em.persist(computer);

            em.getTransaction().commit();
        } finally {
            if( em != null)
                em.close();
        }
    }
}
