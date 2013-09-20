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
import fr.epf.computer.utils.EResult;

@WebServlet("/company/edit") // need change
public class EditCompanyController extends HttpServlet{
	
	CompanyService companyService;
	
	public EditCompanyController(){
		companyService = ServiceManager.INSTANCE.getCompanyService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean bEverythingOkay = true;

        int eResult = 0;
		
		String newName = request.getParameter("name"); // Getting the new company name
        request.setAttribute("companyNameValue", newName);
		if(newName == null || newName.trim().isEmpty()){ // Verify the name
            // Setting the right EResult flag
            eResult |= EResult.INVALID_COMPANY_NAME;
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


		if(eResult == 0) {
			Company foundCompany = companyService.findById(test);
			if(foundCompany == null){
				response.sendRedirect(request.getContextPath() + "/company/add");
				return;
			}
			foundCompany.setName(newName);

			companyService.update(foundCompany);

            request.setAttribute("eResult", eResult);
			request.setAttribute("id", strCompanyId);
			doGet(request, response);
		} else {
            request.setAttribute("eResult", eResult);
			request.setAttribute("id", strCompanyId);
			doGet(request, response);
		}
		
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Checking whether user was redirected after creating a company
        String addOk = request.getParameter("msg");
        if(addOk != null && addOk.equals("addSuccess") ) {
            request.setAttribute("bAddSuccess", true);
        }

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
