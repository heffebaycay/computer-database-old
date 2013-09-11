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

        // Boolean controlling whether all form inputs are valid
        boolean bEverythingOkay = true;

        // Computer name
        String name = request.getParameter("name");
        // Storing given value in case something goes wrong and we need to display it back to the user
        request.setAttribute("computerNameValue", name);
        if(name == null || name.isEmpty()) {
            // Error: invalid computer name
            request.setAttribute("bValidComputerName", false);
            bEverythingOkay = false;
        }


    	// Introduced & Discontinued dates
    	String recup = request.getParameter("dateIntroduced"); // recup en string puis convertion en Date
        // Storing given value in case something goes wrong and we need to display it back to the user
        request.setAttribute("dateIntroducedValue", recup);

    	SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");
        sdf.setLenient(false);
    	Date introduced = null;
    	try{
    		introduced = sdf.parse(recup);
    	}catch(ParseException e){
    		
    	}
        if(introduced == null) {
            // Error: date introduced is invalid
            request.setAttribute("bValidDateIntroduced", false);
            bEverythingOkay = false;
        }

    	String recup2 = request.getParameter("dateDiscontinued"); // recup en string ici aussi
        // Storing given value in case something goes wrong and we need to display it back to the user
        request.setAttribute("dateDiscontinuedValue", recup2);

    	Date discontinued = null;
    	try{
    		discontinued = sdf.parse(recup2);
    	}catch(ParseException e){
    		
    	}
        if(discontinued == null) {
            // Error: date discontinued is invalid
            request.setAttribute("bValidDateDiscontinued", false);
            bEverythingOkay = false;
        }

    	
    	// Company
    	String companyRecup = request.getParameter("company");
        // Storing given value in case something goes wrong and we need to display it back to the user
        request.setAttribute("companyIdValue", companyRecup);

        Company company = null;
    	try{
    		int companyId = Integer.parseInt(companyRecup);
            company = companyService.findById(companyId);
    	}catch(NumberFormatException e){

    	}
        if(company == null) {
            // Error: invalid company selected
            request.setAttribute("bValidCompany", false);
            bEverythingOkay = false;
        }

        if( bEverythingOkay ) {
            computerService.create( new Computer.Builder()
                    .name(name)
                    .introduced(introduced)
                    .discontinued(discontinued)
                    .company(company)
                    .build());
            response.sendRedirect("/computer/list");
        }
        else {
            request.setAttribute("bEverythingOkay", false);
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
