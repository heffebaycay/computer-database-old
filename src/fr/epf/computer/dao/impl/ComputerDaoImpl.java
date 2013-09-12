package fr.epf.computer.dao.impl;


import fr.epf.computer.dao.ComputerDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Computer;

import javax.persistence.EntityManager;
import java.util.List;

public class ComputerDaoImpl implements ComputerDao {

    private long totalComputerCount;

    @Override
    public List<Computer> getComputers(int offset, int nbRequested) {

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

            this.totalComputerCount = (Long) em.createQuery("SELECT COUNT (c) FROM Computer c").getSingleResult();

        } finally {
            if( em != null )
                em.close();
        }

        return computers;
    }

    @Override
    public long getTotalComputerCount() {
        // todo: Handle case where totalComputerCout hasn't been set
        return totalComputerCount;
    }

    public ComputerDaoImpl() {
        totalComputerCount = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Computer> searchByName(String name, int offset, int nbRequested) {
        List<Computer> computers = null;

        EntityManager em = null;
        try {
            em = DaoManager.INSTANCE.getEntityManager();

            computers = em.createQuery(
                    "SELECT  c FROM Computer c WHERE c.name LIKE :compName"
            ).setParameter("compName", "%" + name + "%")
             .setFirstResult(offset)
             .setMaxResults(nbRequested)
            .getResultList();

        } finally {
            if( em != null)
                em.close();
        }

        return computers;
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

    public long getTotalComputerCountForSearch(String name) {
        EntityManager em = null;
        long computerCount = -1;

        try {
            em = DaoManager.INSTANCE.getEntityManager();

            computerCount = (Long) em.createQuery(
                    "SELECT COUNT(c) FROM Computer c WHERE c.name LIKE :compName"
            ).setParameter("compName", "%" + name + "%")
             .getSingleResult();
        } finally {
            if( em != null)
                em.close();
        }

        return computerCount;
    }
}
