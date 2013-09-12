package fr.epf.computer.service.impl;


import fr.epf.computer.dao.ComputerDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Computer;
import fr.epf.computer.service.ComputerService;
import fr.epf.computer.wrapper.ComputerSearchWrapper;

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
    public ComputerSearchWrapper searchByName(String name, int offset, int nbRequested) {
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
    public ComputerSearchWrapper getComputers(int offset, int nbRequested) {
        return computerDao.getComputers(offset, nbRequested);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Computer computer){
        computerDao.create(computer);
    }
}
