package com.shoestore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shoestore.entity.Rate;

public class RateDAO extends JPADAO<Rate> implements GenericDAO<Rate> {

	@Override
	public Rate create(Rate rate) {
		rate.setRateTime(new Date());
		return super.create(rate);
	}
	
	@Override
	public Rate get(Object rateId) {
		return super.find(Rate.class, rateId);
	}

	@Override
	public void delete(Object rateId) {
		super.delete(Rate.class, rateId);
		
	}

	@Override
	public List<Rate> listAll() {
		return super.findWithNamedQuery("Rate.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Rate.countAll");
	}

	public Rate findByCustomerAndShoe(Integer customerId, Integer shoeId) {
		Map<String, Object>params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		params.put("shoeId", shoeId);
		
		List<Rate>res = super.findWithNamedQuery("Rate.findByCustomerAndShoe", params);
	
		if(!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
	
	public List<Rate> listMostRecentRates(){
		return super.findWithNamedQuery("Rate.findAll", 0, 3);
	}
	
	public long countByShoe(int shoeId) {
		return super.countWithNamedQuery("Rate.countByShoe", "shoeId", shoeId);
	}
	
	public long countByCustomer(int customerId) {
		return super.countWithNamedQuery("Rate.countByCustomer", "customerId", customerId);
	}
	
	public List<String> listRatingStars(){
		return super.listWithNamedQuery("Rate.listRatingStars");
	}
	
	public List<Integer> countRatingStars(){
		return super.countListWithNamedQuery("Rate.countRatingStars");
	}
}
