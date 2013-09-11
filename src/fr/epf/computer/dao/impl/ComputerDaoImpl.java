package fr.epf.computer.dao.impl;


import fr.epf.computer.dao.ComputerDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Computer;

import javax.persistence.EntityManager;
import java.util.List;

public class ComputerDaoImpl implements ComputerDao {

    public ComputerDaoImpl() {

    }

    @Override
    public List<Computer> searchByName(String name) {
        List<Computer> computers = null;

        EntityManager em = null;
        try {
            em = DaoManager.INSTANCE.getEntityManager();

            computers = em.createQuery(
                    "SELECT  c FROM Computer c WHERE c.name LIKE :compName"
            ).setParameter("compName", "%" + name + "%")
            .getResultList();

        } finally {
            if( em != null)
                em.close();
        }

        return computers;
    }

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
