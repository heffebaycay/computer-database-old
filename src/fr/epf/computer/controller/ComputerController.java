package fr.epf.computer.controller;

import fr.epf.computer.domain.Computer;
import fr.epf.computer.service.ComputerService;
import fr.epf.computer.service.manager.ServiceManager;
import fr.epf.computer.utils.ComputerSortCriterion;
import fr.epf.computer.utils.SortOrder;
import fr.epf.computer.wrapper.SearchWrapper;

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


        // Sorting arguments
        String gSortBy = request.getParameter("sortBy");
        String gSortOrder = request.getParameter("order");
        SortOrder sortOrder;
        ComputerSortCriterion sortCriterion;

        // Setting up sort order
        if(gSortOrder != null && gSortOrder.equals("desc")) {
            sortOrder = SortOrder.DESC;
            request.setAttribute("sortOrder", gSortOrder);
        } else {
            sortOrder = SortOrder.ASC;
            request.setAttribute("sortOrder", "asc");
        }

        // Setting up sort criterion
        if( gSortBy != null && !gSortBy.trim().isEmpty()) {
            if( gSortBy.equals("id") ) {
                sortCriterion = ComputerSortCriterion.ID;
                request.setAttribute("sortCriterion", gSortBy);
            } else if( gSortBy.equals("name") ) {
                sortCriterion = ComputerSortCriterion.NAME;
                request.setAttribute("sortCriterion", gSortBy);
            } else if (gSortBy.equals("dateIntroduced")) {
                sortCriterion = ComputerSortCriterion.DATE_INTRODUCED;
                request.setAttribute("sortCriterion", gSortBy);
            } else if (gSortBy.equals("dateDiscontinued")) {
                sortCriterion = ComputerSortCriterion.DATE_DISCONTINUED;
                request.setAttribute("sortCriterion", gSortBy);
            } else if (gSortBy.equals("company")) {
                sortCriterion = ComputerSortCriterion.COMPANY_NAME;
                request.setAttribute("sortCriterion", gSortBy);
            } else {
                // Default criterion
                sortCriterion = ComputerSortCriterion.ID;
                request.setAttribute("sortCriterion", "id");
            }
        } else {
            // Setting to default criterion
            sortCriterion = ComputerSortCriterion.ID;
            request.setAttribute("sortCriterion", "id");
        }


        List<Computer> computers = null;
        SearchWrapper<Computer> searchWrapper;

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
            searchWrapper = computerService.searchByName(searchQuery, (iPage - 1) * nbComputerPerPage, nbComputerPerPage, sortCriterion, sortOrder);
            computers = searchWrapper.getResults();
            long totalComputerCount = searchWrapper.getTotalQueryCount();

            long totalPage = (long) Math.ceil( totalComputerCount * 1.0 / nbComputerPerPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("totalCount", totalComputerCount);
            request.setAttribute("searchQuery", searchQuery);

        } else {
            // Display all computers
            searchWrapper = computerService.getComputers( (iPage - 1) * nbComputerPerPage, nbComputerPerPage, sortCriterion, sortOrder );
            computers = searchWrapper.getResults();
            long totalComputerCount = searchWrapper.getTotalQueryCount();
            long totalPage = (long) Math.ceil( totalComputerCount * 1.0 / nbComputerPerPage );
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("totalCount", totalComputerCount);
        }

        request.setAttribute("computers", computers);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/computers_list.jsp"));
        rd.forward(request, response);

    }
}
