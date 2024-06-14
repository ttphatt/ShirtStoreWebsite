package com.shoestore.controller.admin.shoe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoestore.service.ShoeServices;


@WebServlet("/admin/create_shoe")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10,	//10 KB
		maxFileSize = 1024 * 300,		//300 KB
		maxRequestSize = 1024 * 1024 	//1 MB
)
public class CreateShoeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public CreateShoeServlet() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoeServices shoeService = new ShoeServices(request, response);
		shoeService.createShoe();
		
	}

}
