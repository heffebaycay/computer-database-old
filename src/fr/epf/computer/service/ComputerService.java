package fr.epf.computer.service;

import fr.epf.computer.domain.Computer;

import java.util.List;


public interface ComputerService {

    /**
     * @see fr.epf.computer.dao.ComputerDao#getComputers()
     */
    List<Computer> getComputers();

    /**
     * @see fr.epf.computer.dao.ComputerDao#searchByName(String)
     */
    List<Computer> searchByName(String name, int offset, int nbRequested);

    /**
     * @see fr.epf.computer.dao.ComputerDao#create(fr.epf.computer.domain.Computer)
     */
    void create(Computer computer);

    List<Computer> getComputers(int offset, int nbRequested);

    long getTotalComputerCount();

    long getTotalComputerCountForSearch(String name);
    
}
