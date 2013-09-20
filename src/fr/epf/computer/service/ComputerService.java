package fr.epf.computer.service;

import fr.epf.computer.domain.Computer;
import fr.epf.computer.utils.ComputerSortCriteria;
import fr.epf.computer.utils.SortOrder;
import fr.epf.computer.wrapper.SearchWrapper;

import java.util.List;


public interface ComputerService {

    /**
     * @see fr.epf.computer.dao.ComputerDao#getComputers()
     */
    List<Computer> getComputers();

    /**
     * @see fr.epf.computer.dao.ComputerDao#searchByName(String, int, int, fr.epf.computer.utils.ComputerSortCriteria, fr.epf.computer.utils.SortOrder)
     */
    SearchWrapper<Computer> searchByName(String name, int offset, int nbRequested, ComputerSortCriteria sortCriterion, SortOrder sortOrder);

    /**
     * @see fr.epf.computer.dao.ComputerDao#create(fr.epf.computer.domain.Computer)
     */
    void create(Computer computer);

    /**
     * @see fr.epf.computer.dao.ComputerDao#getComputers(int, int, fr.epf.computer.utils.ComputerSortCriteria, fr.epf.computer.utils.SortOrder)
     */
    SearchWrapper<Computer> getComputers(int offset, int nbRequested, ComputerSortCriteria sortCriterion, SortOrder sortOrder);

    /**
     * @see fr.epf.computer.dao.ComputerDao#findById(long)
     */
    Computer findById(long id);

    /**
     * @see fr.epf.computer.dao.ComputerDao#update(fr.epf.computer.domain.Computer)
     */
    void update(Computer computer);

    /**
     * @see fr.epf.computer.dao.ComputerDao#remove(long)
     */
    boolean remove(long id);
    
}
