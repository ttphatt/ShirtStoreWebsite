package com.shoestore.controller.admin.shoe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoestore.service.ShoeServices;


@WebServlet("/admin/delete_shoe")
public class DeleteShoeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteShoeServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoeServices shoeService = new ShoeServices(request, response);
		shoeService.deleteShoe();
	}

}
