package fr.epf.computer.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	
    	String name = request.getParameter("name");
    	
    	String recup = request.getParameter("dateIntroduced"); // recup en string puis convertion en Date
    	SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");
    	try{
    		Date introduced = sdf.parse(recup);
    	}catch(ParseException e){
    		
    	}
    	
    	String recup2 = request.getParameter("dateDiscontinued"); // recup en string ici aussi
    	try{
    		Date discontinued = sdf.parse(recup);
    	}catch(ParseException e){
    		
    	}
    	
    	String company = request.getParameter("company");
    	
    	//Test de validite des champs
    	if(name != null && !name.isEmpty() && company != null && !company.isEmpty())
    		computerService.create(new Computer.Builder().name(name).company(null).build());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	//Envoi de la liste des companies dans la requete :
    	request.setAttribute("companies", companyService.getCompanies());
    	
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/add_computer.jsp");
        rd.forward(request, response);
    }
}
