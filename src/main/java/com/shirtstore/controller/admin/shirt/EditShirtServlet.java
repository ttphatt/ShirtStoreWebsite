package com.shirtstore.controller.admin.shirt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shirtstore.service.ShirtServices;


@WebServlet("/admin/edit_shirt")
public class EditShirtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public EditShirtServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShirtServices shirtServices = new ShirtServices(request, response);
		
		shirtServices.editShirt();
	}

}