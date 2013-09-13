package fr.epf.computer.controller;

import fr.epf.computer.service.ComputerService;
import fr.epf.computer.service.impl.ComputerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RemoveComputerController", value = "/computer/remove")
public class RemoveComputerController extends HttpServlet {

    ComputerService computerService;

    public RemoveComputerController() {
        computerService = new ComputerServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(computerService == null)
            return;

        String strComputerId = request.getParameter("id");
        if( strComputerId == null || strComputerId.trim().isEmpty() )
            return;

        long computerId;
        try {
            computerId = Long.parseLong(strComputerId);
        } catch (NumberFormatException e) {
            computerId = -1;
        }

        if(computerId != -1) {
            computerService.remove(computerId);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
