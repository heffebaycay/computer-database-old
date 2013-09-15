package fr.epf.computer.service.impl;


import fr.epf.computer.dao.ComputerDao;
import fr.epf.computer.dao.manager.DaoManager;
import fr.epf.computer.domain.Computer;
import fr.epf.computer.service.ComputerService;
import fr.epf.computer.utils.ComputerSortCriterion;
import fr.epf.computer.utils.SortOrder;
import fr.epf.computer.wrapper.SearchWrapper;

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
    public SearchWrapper<Computer> searchByName(String name, int offset, int nbRequested, ComputerSortCriterion sortCriterion, SortOrder sortOrder) {
        return computerDao.searchByName(name, offset, nbRequested, sortCriterion, sortOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Computer> getComputers() {
        return computerDao.getComputers();
    }

    @Override
    public SearchWrapper<Computer> getComputers(int offset, int nbRequested, ComputerSortCriterion sortCriterion, SortOrder sortOrder) {
        return computerDao.getComputers(offset, nbRequested, sortCriterion, sortOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Computer computer) {
        computerDao.update(computer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(long id) {
        computerDao.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Computer findById(long id) {
        return computerDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Computer computer){
        computerDao.create(computer);
    }
}
