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

}
