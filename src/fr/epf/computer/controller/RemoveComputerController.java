package fr.epf.computer.controller;

import fr.epf.computer.service.ComputerService;
import fr.epf.computer.service.impl.ComputerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;


@WebServlet(name = "RemoveComputerController", value = "/computer/remove")
public class RemoveComputerController extends HttpServlet {

    ComputerService computerService;

    public RemoveComputerController() {
        computerService = new ComputerServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(computerService == null)
            return;

        boolean bSuccess = false;

        // Fetching the id parameter
        String strComputerId = request.getParameter("id");
        if( strComputerId == null || strComputerId.trim().isEmpty() )
            return;

        // Ensuring the given id looks like a long
        long computerId;
        try {
            computerId = Long.parseLong(strComputerId);
        } catch (NumberFormatException e) {
            computerId = -1;
        }

        if(computerId != -1) {
            // we probably have a valid id, let's just try to remove it from the DataSource
            bSuccess = computerService.remove(computerId);
        }

        /**
         * Returning information about the success of the removal operation in a JSON object
         */
        JSONObject objReply = new JSONObject();
        try {
            objReply.put("success", bSuccess);
        } catch (JSONException e) {
            return;
        }

        PrintWriter out = response.getWriter();
        out.print( objReply.toString() );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
