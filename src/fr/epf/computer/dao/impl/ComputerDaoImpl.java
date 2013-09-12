package fr.epf.computer.dao.impl;


import fr.epf.computer.dao.ComputerDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Computer;
import fr.epf.computer.wrapper.ComputerSearchWrapper;

import javax.persistence.EntityManager;
import java.util.List;

public class ComputerDaoImpl implements ComputerDao {

    @Override
    public ComputerSearchWrapper getComputers(int offset, int nbRequested) {

        ComputerSearchWrapper searchWrapper;
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

            searchWrapper = new ComputerSearchWrapper.Builder()
                                .computers(computers)
                                .totalQueryCount(totalComputerCount)
                                .build();

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
    public ComputerSearchWrapper searchByName(String name, int offset, int nbRequested) {
        List<Computer> computers = null;
        long computerCount = -1;
        ComputerSearchWrapper searchWrapper = null;

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

            searchWrapper = new ComputerSearchWrapper.Builder()
                            .computers(computers)
                            .totalQueryCount(computerCount)
                            .build();


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
