package fr.epf.computer.controller;

import fr.epf.computer.domain.Company;
import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.manager.ServiceManager;
import fr.epf.computer.wrapper.SearchWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/company/list")
public class CompanyController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    CompanyService companyService;

    public CompanyController() {
        companyService = ServiceManager.INSTANCE.getCompanyService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if( companyService == null)
            return;

        List<Company> companies = null;
        SearchWrapper<Company> searchWrapper;

        int nbCompaniesPerPage = 25;

        String strPage = request.getParameter("p");
        if( strPage == null || strPage.isEmpty() )
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
            /* If we actually have something to using as a search query, let's just kindly
             * ask the Company Service to find all companies matching that name.
             */
            searchWrapper = companyService.searchByName(searchQuery, (iPage - 1) * nbCompaniesPerPage, nbCompaniesPerPage);
            companies = searchWrapper.getResults();
            long totalCompanyCount = searchWrapper.getTotalQueryCount();

            long totalPage = (long) Math.ceil( totalCompanyCount * 1.0 / nbCompaniesPerPage );
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("totalCount", totalCompanyCount);
            request.setAttribute("searchQuery", searchQuery);

        } else {
            /*
              User didn't give us anything to search on. Let's just show him all companies.
             */
            searchWrapper = companyService.getCompanies((iPage - 1) * nbCompaniesPerPage, nbCompaniesPerPage);
            companies = searchWrapper.getResults();
            long totalCompanyCount = searchWrapper.getTotalQueryCount();
            long totalPage = (long) Math.ceil( totalCompanyCount * 1.0 / nbCompaniesPerPage);

            request.setAttribute("totalPage", totalPage);
            request.setAttribute("totalCount", totalCompanyCount);
        }

        request.setAttribute("companies", companies);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/companies_list.jsp"));
        rd.forward(request, response);
    }
}
