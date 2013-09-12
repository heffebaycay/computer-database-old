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
    public List<Computer> searchByName(String name, int offset, int nbRequested) {
        return computerDao.searchByName(name, offset, nbRequested);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Computer> getComputers() {
        return computerDao.getComputers();
    }

    @Override
    public List<Computer> getComputers(int offset, int nbRequested) {
        return computerDao.getComputers(offset, nbRequested);
    }

    @Override
    public long getTotalComputerCount() {
        return computerDao.getTotalComputerCount();
    }

    @Override
    public long getTotalComputerCountForSearch(String name) {
        return computerDao.getTotalComputerCountForSearch(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Computer computer){
        computerDao.create(computer);
    }
}
