package fr.epf.computer.controller;

import fr.epf.computer.domain.Company;
import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.impl.CompanyServiceImpl;
import fr.epf.computer.utils.EResult;

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

        int eResult = 0;

        // Fetching company name POST parameter
        String companyName = request.getParameter("name");
        if(companyName != null && !companyName.trim().isEmpty()) {
            // We have everything we need to create a new company, so let's do that

            Company company = new Company.Builder()
                            .name(companyName)
                            .build();
            companyService.create( company );

            // Company should have been created, so we can redirect the user to edit page of this company
            response.sendRedirect(request.getContextPath() + "/company/edit?id=" + company.getId() + "&msg=addSuccess");

        } else {
            // Error: company name is null/empty
            // Setting a flag to keep track of the error in the form and then redirecting to the form.
            eResult |= EResult.INVALID_COMPANY_NAME;
            request.setAttribute("eResult", eResult);
            request.setAttribute("companyNameValue", companyName);
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Redirecting to the "add company" form
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/add_company.jsp");
        rd.forward(request, response);
    }
}
