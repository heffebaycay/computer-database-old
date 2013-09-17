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
	
	public EditCompanyController(){
		companyService = ServiceManager.INSTANCE.getCompanyService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean bEverythingOkay = true;
		
		String newName = request.getParameter("name"); // Getting the new company name
		if(newName == null || newName.trim().isEmpty()){ // Verify the name
			bEverythingOkay = false;
			request.setAttribute("bEverythingOkay", false);
		}
		
		// We need to get the ID to edit the company
		String strCompanyId = request.getParameter("companyId");
		// Verifying if ID is a long :
		long test;
		try{
			test = Long.parseLong(strCompanyId);
		}catch(NumberFormatException e){
			System.out.println(e.getMessage()); // Show exception message
			response.sendRedirect(request.getContextPath() + "/company/add");
			return;
		}
		
		if(bEverythingOkay) {
			Company foundCompany = companyService.findById(test);
			if(foundCompany == null){
				response.sendRedirect(request.getContextPath() + "/company/add");
				return;
			}
			
			foundCompany.setName(newName);
			
			companyService.update(foundCompany);
			
			request.setAttribute("bEverythingOkay", true);
			request.setAttribute("id", strCompanyId);
			doGet(request, response);
		} else {
			request.setAttribute("id", strCompanyId);
			doGet(request, response);
		}
		
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// We need to get the ID to edit the company
		String strCompanyId = request.getParameter("id");
		if(strCompanyId == null){
			strCompanyId = (String)request.getAttribute("id");
		}
		// Verifying if ID is a long :
		long test;
		try{
			test = Long.parseLong(strCompanyId);
		}catch(NumberFormatException e){
			System.out.println(e.getMessage()); // Show exception message
			response.sendRedirect(request.getContextPath() + "/company/add");
			return;
		}
		Company foundCompany = companyService.findById(test);
		if(foundCompany == null){
			response.sendRedirect(request.getContextPath() + "/company/add");
			return;
		}
	
		request.setAttribute("company", foundCompany);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/edit_company.jsp");

        rd.forward(request, response);
        
	}

}
