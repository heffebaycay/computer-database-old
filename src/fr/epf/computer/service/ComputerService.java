package fr.epf.computer.service;

import fr.epf.computer.domain.Computer;
import fr.epf.computer.wrapper.SearchWrapper;

import java.util.List;


public interface ComputerService {

    /**
     * @see fr.epf.computer.dao.ComputerDao#getComputers()
     */
    List<Computer> getComputers();

    /**
     * @see fr.epf.computer.dao.ComputerDao#searchByName(String, int, int)
     */
    SearchWrapper<Computer> searchByName(String name, int offset, int nbRequested);

    /**
     * @see fr.epf.computer.dao.ComputerDao#create(fr.epf.computer.domain.Computer)
     */
    void create(Computer computer);

    SearchWrapper<Computer> getComputers(int offset, int nbRequested);

    /**
     * @see fr.epf.computer.dao.ComputerDao#findById(long)
     */
    Computer findById(long id);

    /**
     * @see fr.epf.computer.dao.ComputerDao#update(fr.epf.computer.domain.Computer)
     */
    void update(Computer computer);
    
}
