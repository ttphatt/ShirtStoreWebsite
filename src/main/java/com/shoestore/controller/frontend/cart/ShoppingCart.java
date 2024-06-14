package com.shoestore.controller.frontend.cart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.shoestore.entity.Shoe;

public class ShoppingCart {
	private Map<Shoe, Integer>cart = new HashMap<Shoe, Integer>();
	
	//Thêm giày vào giỏ hàng
	public void addItem(Shoe shoe) {
		if(cart.containsKey(shoe)) {
			Integer quantity = cart.get(shoe) + 1;
			cart.put(shoe, quantity);
		}
		else {
			cart.put(shoe, 1);
		}
	}
	
	public Map<Shoe, Integer>getItems(){
		return this.cart;
	}
	
	//Bỏ giày ra khỏi giỏ hàng
	public void removeItem(Shoe shoe) {
		cart.remove(shoe);
	}
	
	//Tính tổng số lượng hàng có trong giỏ hàng
	public int getTotalQuantity() {
		int total = 0;
		int quantity = 0;
		
		Iterator<Shoe>iterator = cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Shoe next = iterator.next();
			quantity = cart.get(next);
			total += quantity;
		}
		
		return total;
	}
	
	//Tính tổng giá tiền của giỏ hàng
	public float getTotalAmount() {
		float total = 0.0f;
		int quantity = 0;
		float subTotal = 0.0f;
		
		Iterator<Shoe>iterator = cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Shoe shoe = iterator.next();
			quantity = cart.get(shoe);
			subTotal = quantity * shoe.getShoePrice();
			total += subTotal;
		}
		
		return total;
	}
	
	//Loại bỏ hết sản phẩm trong giỏ hàng
	public void clearCart() {
		cart.clear();
	}
	
	//Tìm tổng số lượng sản phẩm có trong giỏ hàng
	public int getTotalItems() {
		return cart.size();
	}
	
	//Cập nhật lại giỏ hàng
	public void updateCart(int[] shoeIds, int[] quantities) {
		Shoe shoe;
		Integer value;
		
		for(int i = 0; i < shoeIds.length; i++) {
			shoe = new Shoe(shoeIds[i]);
			value = quantities[i];
			cart.put(shoe, value);
		}
	}
}
