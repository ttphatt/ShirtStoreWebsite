package com.shirtstore.controller.admin.warehouse;

import com.shirtstore.service.ImportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/create_import")
public class CreateImportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateImportServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ImportService importService = new ImportService(request, response);
            importService.createImport();
    }
}