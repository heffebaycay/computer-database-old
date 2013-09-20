package fr.epf.computer.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epf.computer.domain.Company;
import fr.epf.computer.domain.Computer;
import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.ComputerService;
import fr.epf.computer.service.manager.ServiceManager;
import fr.epf.computer.utils.EResult;

import java.text.ParseException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/computer/add")
public class AddComputerController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ComputerService computerService;
    CompanyService companyService;

    public AddComputerController(){

        computerService = ServiceManager.INSTANCE.getComputerService();

        companyService = ServiceManager.INSTANCE.getCompanyService();
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

        if( eResult == 0 ) {
            // Everything went according to plan, so we can build a full instance of Computer
            // ComputerService will take care of persisting that new instance

            Computer computer = new Computer.Builder()
                                .name(name)
                                .discontinued(discontinued)
                                .introduced(introduced)
                                .company(company)
                                .build();
            computerService.create( computer );

            // All done, let's go to the edit page of this computer
            response.sendRedirect(request.getContextPath() + "/computer/edit?id=" + computer.getId() + "&msg=addSuccess");
        }
        else {
            // Tough luck, user didn't fill the form with valid input
            // Let's just redirect him back to the form and show him what he did wrong
            request.setAttribute("eResult", eResult);
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Companies list send in request :
        request.setAttribute("companies", companyService.getCompanies());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/add_computer.jsp");
        rd.forward(request, response);
    }
}
