package fr.epf.computer.controller;

import fr.epf.computer.domain.Company;
import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.manager.ServiceManager;

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

        List<Company> companies;
        String searchQuery = request.getParameter("search");
        if( searchQuery != null && !searchQuery.isEmpty()) {
            /* If we actually have something to using as a search query, let's just kindly
             * ask the Company Service to find all companies matching that name.
             */
            companies = companyService.searchByName(searchQuery);
        } else {
            /*
              User didn't give us anything to search on. Let's just show him all companies.
             */
            companies = companyService.getCompanies();
        }

        request.setAttribute("companies", companies);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/companies_list.jsp"));
        rd.forward(request, response);
    }
}
