package fr.epf.computer.dao;

import fr.epf.computer.domain.Computer;
import fr.epf.computer.utils.ComputerSortCriterion;
import fr.epf.computer.utils.SortOrder;
import fr.epf.computer.wrapper.SearchWrapper;

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
     * @param offset The offset of the first element that should be returned
     * @param nbRequested The number of Computer elements requested
     * @return A wrapper containing both the list of matching elements as well as the total number of elements matched
     */
    SearchWrapper<Computer> searchByName(String name, int offset, int nbRequested, ComputerSortCriterion sortCriterion, SortOrder sortOrder);

    /**
     * Create a computer in the DataSource based on an instance of Computer
     *
     * @param computer The Computer object that we want to be persisted
     */
    void create (Computer computer);

    /**
     *
     * @param offset The offset of the first element that should be returned.
     * @param nbRequested The number of Computer elements requested
     * @return A wrapper containing both the list Computer elements as well as the total number of elements matched
     */
    SearchWrapper<Computer> getComputers(int offset, int nbRequested, ComputerSortCriterion sortCriterion, SortOrder sortOrder);

    /**
     * Update an already existing Computer object and persist it to the DataSource
     *
     * @param computer The Computer object that needs to be updated
     */
    void update (Computer computer);

    /**
     * This method finds a computer from the DataSource based on a given id
     *
     * @param id The id of the computer that should be returned
     * @return The matching computer
     */
    Computer findById(long id);

    /**
     * This method removes a computer from the datasource based on its Id
     * @param id Id of the computer object to be removed
     */
    void remove(long id);
}
