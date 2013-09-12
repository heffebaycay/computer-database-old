package fr.epf.computer.service;

import fr.epf.computer.domain.Computer;
import fr.epf.computer.wrapper.ComputerSearchWrapper;

import java.util.List;


public interface ComputerService {

    /**
     * @see fr.epf.computer.dao.ComputerDao#getComputers()
     */
    List<Computer> getComputers();

    /**
     * @see fr.epf.computer.dao.ComputerDao#searchByName(String, int, int)
     */
    ComputerSearchWrapper searchByName(String name, int offset, int nbRequested);

    /**
     * @see fr.epf.computer.dao.ComputerDao#create(fr.epf.computer.domain.Computer)
     */
    void create(Computer computer);

    ComputerSearchWrapper getComputers(int offset, int nbRequested);
    
}
