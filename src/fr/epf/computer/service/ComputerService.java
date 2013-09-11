package fr.epf.computer.service;

import fr.epf.computer.domain.Computer;

import java.util.List;


public interface ComputerService {
    List<Computer> getComputers();
    List<Computer> searchByName(String name);
}
