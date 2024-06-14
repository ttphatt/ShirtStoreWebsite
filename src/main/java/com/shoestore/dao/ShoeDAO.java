package com.shoestore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;

import com.shoestore.entity.Shoe;

public class ShoeDAO extends JPADAO<Shoe> implements GenericDAO<Shoe> {

	public ShoeDAO() {
	}
 
	@Override
	public Shoe create(Shoe shoe) {
		return super.create(shoe);
	}
	
	@Override
	public Shoe update(Shoe existShoe) {
		return super.update(existShoe);
	}
	
	@Override
	public Shoe get(Object shoeId) {
		return super.find(Shoe.class, shoeId);
	}

	@Override
	public void delete(Object shoeId) {
		super.delete(Shoe.class, shoeId);
	}

	@Override
	public List<Shoe> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Shoe.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Shoe.countAll");
	}
	
	public Shoe findByName(String shoeName) {
		List<Shoe> res = super.findWithNamedQuery("Shoe.findByShoeName", "shoeName", shoeName);
		
		if(!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
	
	public List<Shoe>listByType(int typeId){
		return super.findWithNamedQuery("Shoe.findByType", "typeId", typeId);
	}
	
	public List<Shoe>listNewShoes(){
		return super.findWithNamedQuery("Shoe.findNew", 0, 4);
	}
	
	public List<Shoe>search(String keyword){
		return super.findWithNamedQuery("Shoe.search", "keyword", keyword);
	}
	
	public long countByType(int typeId) {
		return super.countWithNamedQuery("Shoe.countByType", "typeId", typeId);
	}
	
	public List<Shoe> listBestSellingShoes(){
		return super.findWithNamedQuery("OrderDetail.listBestSelling", 0, 4);
	}
	
	public List<Shoe> listMostFavoredShoes(){
		List<Shoe> mostFavoredShoes = new ArrayList<Shoe>();
		List<Object[]> result = super.findWithNamedQueryObjects("Rate.listMostFavoredShoes", 0, 4);
		
		if(!result.isEmpty()) {
			for(Object[] elements : result) {
				Shoe shoe = (Shoe) elements[0];
				mostFavoredShoes.add(shoe);
			}
		}
		
		return mostFavoredShoes;
	}
	
	public long countByOrderDetail(int shoeId) {
		return super.countWithNamedQuery("OrderDetail.countByShoe", "shoeId", shoeId);
	}
	
	public List<Integer> countShoesByTypes(){
		return super.countListWithNamedQuery("Shoe.countByType2");
	}
	
	public List<String> listSoldShoeName(){
		return super.listWithNamedQuery("Shoe.listSoldShoeName");
	}
	
	public List<Integer> listEachShoeRevenue(){
		return super.countListWithNamedQuery("Shoe.listEachShoeRevenue");
	}
}
