package fr.epf.computer.service.impl;


import fr.epf.computer.dao.ComputerDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Computer;
import fr.epf.computer.service.ComputerService;

import java.util.List;

public class ComputerServiceImpl implements ComputerService {

    ComputerDao computerDao;

    public ComputerServiceImpl() {
        computerDao = DaoManager.INSTANCE.getComputerDao();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Computer> searchByName(String name) {
        return computerDao.searchByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Computer> getComputers() {
        return computerDao.getComputers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Computer computer){
        computerDao.create(computer);
    }
}
