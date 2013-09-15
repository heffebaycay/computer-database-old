package fr.epf.computer.controller;

import fr.epf.computer.domain.Company;
import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.impl.CompanyServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/company/add")
public class AddCompanyController extends HttpServlet {

    CompanyService companyService;

    public AddCompanyController() {
        companyService = new CompanyServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Fetching company name POST parameter
        String companyName = request.getParameter("name");
        if(companyName != null && !companyName.isEmpty()) {
            // We have everything we need to create a new company, so let's do that
            companyService.create(
                    new Company.Builder()
                    .name(companyName)
                    .build()
            );

            // Company should have been created, so we can redirect the user to the list of companies
            response.sendRedirect(request.getContextPath() + "/company/list");

        } else {
            // Error: company name is null/empty
            // Setting a flag to keep track of the error in the form and then redirecting to the form.
            request.setAttribute("bNameValid", false);
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Redirecting to the "add company" form
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/add_company.jsp");
        rd.forward(request, response);
    }
}
