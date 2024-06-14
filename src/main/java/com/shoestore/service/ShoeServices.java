package com.shoestore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.shoestore.dao.RateDAO;
import com.shoestore.dao.ShoeDAO;
import com.shoestore.dao.TypeDAO;
import com.shoestore.entity.Shoe;
import com.shoestore.entity.Type;

public class ShoeServices {
	private ShoeDAO shoeDAO;
	private TypeDAO typeDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ShoeServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		
		shoeDAO = new ShoeDAO();
		typeDAO = new TypeDAO();
	}
	
	//Back-end modules
	
	//Liệt kê danh sách các loại giày không kèm theo thông báo
	public void listShoes() throws ServletException, IOException {
		listShoes(null);
	}
	
	//Liệt kê danh sách các loại giày có kèm theo thông báo
	public void listShoes(String message) throws ServletException, IOException {
		List<Shoe> listShoes = shoeDAO.listAll();
		request.setAttribute("listShoes", listShoes);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String path = "shoe_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
	
	//Show ra form để thêm giày mới
	public void showShoeNewForm() throws ServletException, IOException {
		//Lấy ra danh sách các loại giày
		List<Type> listType = typeDAO.listAll();
		
		//Đẩy danh sách các loại giày qua view
		request.setAttribute("listType", listType);
		
		String path = "shoe_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}	

	public void createShoe() throws ServletException, IOException {
		String shoeName = request.getParameter("shoeName");
		Shoe existShoe = shoeDAO.findByName(shoeName);
		
		//Kiểm tra trùng tên giày vì tên giày là unique key
		if(existShoe != null) { 
			String message = "Could not create new shoes since there is already a pair of shoes with the name: " + shoeName; 
			listShoes(message);
			return;
		} 
		
		Shoe newShoe = new Shoe();
		
		//Lấy dữ liệu từ view và lưu 1 object shoe = newShoe
		readFields(newShoe);
		
		//Đẩy objecy shoe vừa tạo xuống model để thêm vào database
		Shoe createdShoe = shoeDAO.create(newShoe);
		
		//Đẩy ra view thông báo thành công
		if(createdShoe.getShoeId() > 0) {
			String message = "A new pair of shoes has been created successfully";
			
			//Refresh lại bằng cách liệt kê lại các đôi giày
			listShoes(message);
		}
	}

	public void editShoe() throws ServletException, IOException {
		//Đổ dữ liệu vào combo box Type
		List<Type> listType = typeDAO.listAll();
		request.setAttribute("listType", listType);
		
		Integer shoeId = Integer.parseInt(request.getParameter("id"));
		Shoe shoe = shoeDAO.get(shoeId);
		
		//Đẩy ra view object shoe đã có dữ liệu để edit 
		request.setAttribute("shoe", shoe);
		
		String path = "shoe_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
	
	//Đọc dữ liệu từ view và lưu vào object shoe (tham số)
	public void readFields(Shoe shoe) throws ServletException, IOException {
		Integer typeId = Integer.parseInt(request.getParameter("type"));
		String shoeName = request.getParameter("shoeName");
		String brand = request.getParameter("brand");
		String description = request.getParameter("description");
		Float shoePrice = Float.parseFloat(request.getParameter("shoePrice"));
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date releasedDate;
		
		try {
			releasedDate = df.parse(request.getParameter("releasedDate"));
		}
		catch(ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Error parsing released date (format is: yyyy-MM-dd");
		}
		

		Type type = typeDAO.get(typeId);
		
		shoe.setType(type);
		shoe.setShoeName(shoeName);
		shoe.setBrand(brand);
		shoe.setDescription(description);
		shoe.setShoePrice(shoePrice);
		shoe.setReleasedDate(releasedDate);
		
		Part part = request.getPart("shoeImage");
		
		if(part != null && part.getSize() > 0) {
			//Lấy size của file ảnh
			long size = part.getSize();
			
			//Tạo ra 1 mảng kiểu byte có độ dài là size của ảnh vừa lấy ở trên
			byte[] imageBytes = new byte[(int)size];
			
			//Tạo 1 input stream mới từ part
			InputStream inputStream = part.getInputStream();
			
			//Đổ dữ liệu từ input stream vào mảng kiểu byte
			inputStream.read(imageBytes);
			inputStream.close();
		
			//Object shoe sẽ lưu hình ảnh = Lưu chuỗi byte của hình ảnh
			shoe.setShoeImage(imageBytes);
		}
		
	}
	
	//Cập nhật dữ liệu của shoe
	public void updateShoe() throws ServletException, IOException {
		Integer shoeId = Integer.parseInt(request.getParameter("shoeId"));
		String shoeName = request.getParameter("shoeName");
		
		Shoe existShoe = shoeDAO.get(shoeId);
		Shoe shoeByName = shoeDAO.findByName(shoeName);
		
		//Tồn tại 1 đôi giày khác cùng tên
		if(shoeByName != null && !existShoe.equals(shoeByName)) {
			String message = "Unable to update this pair of shoes because there is another pair shoes has the name: " + shoeName;
			listShoes(message);
			return;
		}
		
		//Đọc dữ liệu từ view vào object existShoe
		readFields(existShoe);
		
		//Cập nhật dữ liệu xuống database
		shoeDAO.update(existShoe);
		
		String message = "The pair of shoes has been updated successfully";
		listShoes(message);
	}

	public void deleteShoe() throws ServletException, IOException {
		Integer shoeId = Integer.parseInt(request.getParameter("id"));
		
		RateDAO rateDAO = new RateDAO();
		long numberOfRate = rateDAO.countByShoe(shoeId);
		
		long numberOfOrder = shoeDAO.countByOrderDetail(shoeId);
		
		String message = "";
		
		//Nếu tồn tại đánh giá của đôi giày chọn để xóa -> Không xóa
		if(numberOfRate > 0) {
			message = "Unable to delete the pair of shoes with id " + shoeId + ". Because it has been rated";
		}
		
		//Nếu tồn tại đơn hàng của đôi giày chọn để xóa -> Không xóa
		else if(numberOfOrder > 0) {
			message = "Unable to delete the pair of shoes with id " + shoeId + ". Because it has been ordered";
		}
		else {
			shoeDAO.delete(shoeId);
			message = "The pair of shoes with id: " + shoeId + " has been deleted successfully";
		}
		listShoes(message);
	}
	
	//Front-end modules:
	//Liệt kê giày theo loại
	public void listShoesByType() throws ServletException, IOException {
		int typeId = Integer.parseInt(request.getParameter("id"));
		List<Shoe> listShoes = shoeDAO.listByType(typeId);
		Type type = typeDAO.get(typeId);

		request.setAttribute("type", type);
		request.setAttribute("listShoes", listShoes);

		String path = "frontend/shoes_list_by_type.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
	
	//Xem chi tiết 1 đôi giày
	public void viewShoeDetail() throws ServletException, IOException {
		int shoeId = Integer.parseInt(request.getParameter("id"));
		Shoe shoe = shoeDAO.get(shoeId);

		request.setAttribute("shoe", shoe);
	
		String path = "frontend/shoe_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
	
	//Tìm kiếm giày theo từ khóa
	public void search() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Shoe>resList = null;
		
		if(keyword.equals("")) {
			resList = shoeDAO.listAll();
		}
		else {
			resList = shoeDAO.search(keyword);
		}
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", resList);
		
		String path = "frontend/search_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
