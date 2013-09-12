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
    List<Computer> searchByName(String name);

    /**
     * @see fr.epf.computer.dao.ComputerDao#create(fr.epf.computer.domain.Computer)
     */
    void create(Computer computer);
    
}
