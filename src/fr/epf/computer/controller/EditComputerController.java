package fr.epf.computer.controller;

import fr.epf.computer.domain.Company;
import fr.epf.computer.domain.Computer;
import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.ComputerService;
import fr.epf.computer.service.impl.CompanyServiceImpl;
import fr.epf.computer.service.impl.ComputerServiceImpl;
import fr.epf.computer.utils.EResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EditComputerController", value = "/computer/edit")
public class EditComputerController extends HttpServlet {

    ComputerService computerService;
    CompanyService companyService;

    public EditComputerController() {
        computerService = new ComputerServiceImpl();
        companyService  = new CompanyServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int eResult = 0;

        // Computer name
        String name = request.getParameter("name");
        // Storing given value in case something goes wrong and we need to display it back to the user
        request.setAttribute("computerNameValue", name);
        if(name == null || name.isEmpty()) {
            // Error: invalid computer name
            eResult |= EResult.INVALID_COMPUTER_NAME;
        }


        // Introduced & Discontinued dates
        String recup = request.getParameter("dateIntroduced"); // Getting the String then convert into Date
        // Storing given value in case something goes wrong and we need to display it back to the user
        request.setAttribute("dateIntroducedValue", recup);

        // Human readable format: YYYY-MM-DD (Year-Month-Date)
        SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");

        // SDF, don't be lenient, bro.
        // SDF should only parse what it's told to
        sdf.setLenient(false);

        Date introduced = null;
        try{
            introduced = sdf.parse(recup);
        }catch(ParseException e){

        }
        if(introduced == null) {
            // Error: date introduced is invalid
            eResult |= EResult.INVALID_COMPUTER_INTRODUCED_DATE;
        }

        String recup2 = request.getParameter("dateDiscontinued"); // Get the String here too
        // Storing given value in case something goes wrong and we need to display it back to the user

        request.setAttribute("dateDiscontinuedValue", recup2);

        Date discontinued = null;
        try{
            discontinued = sdf.parse(recup2);
        }catch(ParseException e){

        }
        if(discontinued == null) {
            // Error: date discontinued is invalid
            eResult |= EResult.INVALID_COMPUTER_DISCONTINUED_DATE;
        }


        // Company
        String companyRecup = request.getParameter("company");
        // Storing given value in case something goes wrong and we need to display it back to the user
        request.setAttribute("companyIdValue", companyRecup);

        Company company = null;
        try{
            long companyId = Long.parseLong(companyRecup);
            company = companyService.findById(companyId);
        }catch(NumberFormatException e){

        }
        if(company == null) {
            // Error: invalid company selected
            eResult |= EResult.INVALID_COMPANY;
        }

        String strComputerId = request.getParameter("computerId");
        long computerId;
        try {
            computerId = Long.parseLong(strComputerId);
        } catch ( NumberFormatException e) {
            // Id parameter provided isn't of type "long", so we won't be able to do anything useful with it
            // Might as well redirect the user to the Computer add page
            response.sendRedirect(request.getContextPath() + "/computer/add");
            return;
        }

        if( eResult == 0 ) {
            // Everything went according to plan, so we can build a full instance of Computer
            // ComputerService will take care of persisting that new instance

            // Fetching the Computer object based on the given Id.
            Computer computer = computerService.findById(computerId);
            if( computer == null) {
                // Unable to find any computer based on the given id
                // Redirecting the user to the "Add" page.
                response.sendRedirect(request.getContextPath() + "/computer/add");
                return;
            }
            computer.setName(name);
            computer.setCompany(company);
            computer.setDiscontinued(discontinued);
            computer.setIntroduced(introduced);

            computerService.update(computer);

        }
        // All done, let's go back to the edit form
        request.setAttribute("eResult", eResult);
        request.setAttribute("id", strComputerId );
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // We definitely need both a ComputerService and a CompanyService here
        if(computerService == null && companyService != null)
            return;

        // Checking whether user was redirected after creating a computer
        String addOk = request.getParameter("msg");
        if(addOk != null && addOk.equals("addSuccess") ) {
            request.setAttribute("bAddSuccess", true);
        }

        // Getting computer Id transmitted via a GET parameter
        String strComputerId = request.getParameter("id");
        if(strComputerId == null) {
            strComputerId = (String) request.getAttribute("id");
        }
        long computerId;
        try {
            computerId = Long.parseLong(strComputerId);
        } catch ( NumberFormatException e) {
            // Id parameter provided isn't of type "long", so we won't be able to do anything useful with it
            // Might as well redirect the user to the Computer add page
            response.sendRedirect(request.getContextPath() + "/computer/add");
            return;
        }

        // Fetching the Computer object based on the given Id.
        Computer computer = computerService.findById(computerId);
        if( computer == null) {
            // Unable to find any computer based on the given id
            // Redirecting the user to the "Add" page.
            response.sendRedirect(request.getContextPath() + "/computer/add");
            return;
        }

        request.setAttribute("computer", computer);

        // Fetching the list of all Companies
        List<Company> companies = companyService.getCompanies();
        request.setAttribute("companies", companies);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/edit_computer.jsp");
        rd.forward(request, response);
    }
}
