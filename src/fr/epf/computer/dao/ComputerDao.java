package fr.epf.computer.dao;

import fr.epf.computer.domain.Computer;

import java.util.List;


public interface ComputerDao {
    List<Computer> getComputers();
}