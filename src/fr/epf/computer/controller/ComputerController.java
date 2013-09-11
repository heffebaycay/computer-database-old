package fr.epf.computer.controller;

import fr.epf.computer.domain.Computer;
import fr.epf.computer.service.ComputerService;
import fr.epf.computer.service.manager.ServiceManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/computer/list")
public class ComputerController extends HttpServlet {

    ComputerService computerService;

    public ComputerController() {
        computerService = ServiceManager.INSTANCE.getComputerService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if( computerService == null)
            return;

        String searchQuery = request.getParameter("search");

        List<Computer> computers = null;
        if( searchQuery != null && !searchQuery.isEmpty()) {

            computers = computerService.searchByName(searchQuery);

        } else {
            computers = computerService.getComputers();
        }

        request.setAttribute("computers", computers);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/computers_list.jsp"));
        rd.forward(request, response);

    }
}
