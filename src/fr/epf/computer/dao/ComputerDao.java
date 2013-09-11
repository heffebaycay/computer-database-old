package fr.epf.computer.dao;

import fr.epf.computer.domain.Computer;

import java.util.List;


public interface ComputerDao {

    /**
     * This method returns the list of all computers stored in the DataSource.
     *
     * @return The list of all computers
     */
    List<Computer> getComputers();

    /**
     * Searches the DataSource for computers whose names match the name argument.
     * This method is case insensitive.
     *
     * @param name Any substring of the name of the computer(s) we're looking for
     * @return A list of matching computers
     */
    List<Computer> searchByName(String name);

    /**
     * Create a computer in the DataSource based on an instance of Computer
     *
     * @param computer The Computer object that we want to be persisted
     */
    void create (Computer computer);
}
