package fr.epf.computer.controller;

import fr.epf.computer.domain.Computer;
import fr.epf.computer.service.ComputerService;
import fr.epf.computer.service.manager.ServiceManager;
import fr.epf.computer.wrapper.ComputerSearchWrapper;

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

        List<Computer> computers = null;
        ComputerSearchWrapper searchWrapper = null;

        int nbComputerPerPage = 25;

        String strPage = request.getParameter("p");
        if(strPage == null || strPage.isEmpty())
            strPage = "1";

        int iPage;
        try {
            iPage = Integer.parseInt(strPage);
        } catch ( NumberFormatException e ) {
            iPage = 1;
        }

        request.setAttribute("currentPage", iPage);

        String searchQuery = request.getParameter("search");
        if( searchQuery != null && !searchQuery.isEmpty()) {
            // User queried specific computers
            searchWrapper = computerService.searchByName(searchQuery, (iPage - 1) * nbComputerPerPage, nbComputerPerPage);
            computers = searchWrapper.getComputers();
            long totalComputerCount = searchWrapper.getTotalQueryCount();

            long totalPage = (long) Math.ceil( totalComputerCount * 1.0 / nbComputerPerPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("searchQuery", searchQuery);

        } else {
            // Display all computers
            searchWrapper = computerService.getComputers( (iPage - 1) * nbComputerPerPage, nbComputerPerPage );
            computers = searchWrapper.getComputers();
            long totalComputerCount = searchWrapper.getTotalQueryCount();
            long totalPage = (long) Math.ceil( totalComputerCount * 1.0 / nbComputerPerPage );
            request.setAttribute("totalPage", totalPage);
        }

        request.setAttribute("computers", computers);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/computers_list.jsp"));
        rd.forward(request, response);

    }
}
