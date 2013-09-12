package fr.epf.computer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epf.computer.domain.Company;
import fr.epf.computer.service.CompanyService;
import fr.epf.computer.service.impl.CompanyServiceImpl;
import fr.epf.computer.service.manager.ServiceManager;

@WebServlet("/company/edit") // need change
public class EditCompanyController extends HttpServlet{
	
	CompanyService companyService;
	Company company;
	
	public EditCompanyController(){
		companyService = ServiceManager.INSTANCE.getCompanyService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newName = request.getParameter("name"); // Getting the new company name
		if(newName != null && !newName.isEmpty()){ // Verify the name
			company.setName(newName); // Editing the company name
			response.sendRedirect("/company/list"); // Redirect to the companies list
		}else {
            // Todo: Handle error
            return;
        }
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// We need to get the ID to edit the company
		String recup = request.getParameter("id");
		// Verifying if ID is a long :
		try{
			long test = Long.parseLong(recup);
			Company foundCompany = companyService.findById(test);
			if(foundCompany != null){
				company = foundCompany;
				request.setAttribute("company", company);
			}
		}catch(NumberFormatException e){
			System.out.println(e.getMessage()); // Show exception message
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/edit_company.jsp");

        rd.forward(request, response);
        
	}

}
