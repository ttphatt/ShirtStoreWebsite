package com.shoestore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shoestore.entity.Customer;
import com.shoestore.entity.Shoe;
import com.shoestore.entity.ShoeOrder;

public class OrderDAO extends JPADAO<ShoeOrder> implements GenericDAO<ShoeOrder> {
	@Override
	public ShoeOrder create(ShoeOrder order) {
		order.setOrderDate(new Date());
		order.setStatus("Processing");
		
		return super.create(order);
	}
	
	@Override
	public ShoeOrder update(ShoeOrder order) {
		return super.update(order);
	}

	@Override
	public ShoeOrder get(Object orderId) {
		return super.find(ShoeOrder.class, orderId);
	}
	
	public ShoeOrder get(Object orderId, Integer customerId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		params.put("customerId", customerId);
		
		List<ShoeOrder>res = super.findWithNamedQuery("ShoeOrder.findByIdAndCustomer", params);
		
		if(!res.isEmpty()) {
			return res.get(0);
		}
		
		return null;
	}
	
	@Override
	public void delete(Object orderId) {
		super.delete(ShoeOrder.class, orderId);
	}

	@Override
	public List<ShoeOrder> listAll() {
		return super.findWithNamedQuery("ShoeOrder.listAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("ShoeOrder.countAll");
	}

	public List<ShoeOrder> listByCustomer(Integer customerId){
		return super.findWithNamedQuery("ShoeOrder.findByCustomer", "customerId", customerId);
	}
	
	public List<ShoeOrder> listMostRecentSales(){
		return super.findWithNamedQuery("ShoeOrder.listAll", 0, 3);
	}
	
	public long countByCustomer(int customerId) {
		return super.countWithNamedQuery("ShoeOrder.countByCustomer", "customerId", customerId);
	}
}
