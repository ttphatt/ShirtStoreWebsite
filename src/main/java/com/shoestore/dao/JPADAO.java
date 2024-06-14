package com.shoestore.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPADAO<E> {
	private static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("ShoeStoreWebsite");
	}

	public JPADAO() {
	}
	
	//Thêm dữ liệu vào database
	public E create(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return entity;
	}
	
	//Cập nhật dữ liệu trong database
	public E update(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		return entity;
	}
	
	//Tìm dữ liệu trong database
	public E find(Class<E>type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		E entity = entityManager.find(type, id);
		if(entity != null) {
			entityManager.refresh(entity);
		}
		
		entityManager.close();
		return entity;
	}
	
	//Xóa dữ liệu trong database
	public void delete(Class<E>type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}
	
	//Trả về tập dữ liệu trong database bằng query
	public List<E>findWithNamedQuery(String queryName){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		List<E>res = query.getResultList();
		
		entityManager.close();
		return res;
	}
	
	public List<E>findWithNamedQuery(String queryName, int firstResult, int maxResult){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		List<E>res = query.getResultList();
		
		entityManager.close();
		return res;
	}
	
	public List<Object[]>findWithNamedQueryObjects(String queryName, int firstResult, int maxResult){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		List<Object[]>res = query.getResultList();
		
		entityManager.close();
		return res;
	}
	
	public List<E>findWithNamedQuery(String queryName, String paramName, Object paramValue){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		
		query.setParameter(paramName, paramValue);
		List<E>res = query.getResultList();
		
		entityManager.close();
		return res;
	}
	
	public List<E>findWithNamedQuery(String queryName, Map<String, Object>parameters){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		Set<java.util.Map.Entry<String, Object>>setParameters = parameters.entrySet();
	
		for(java.util.Map.Entry<String, Object>temp : setParameters) {
			query.setParameter(temp.getKey(), temp.getValue());
		}
		
		List<E>res = query.getResultList();
		
		entityManager.close();
		return res;
	}
	
	//Các function thêm vào
	public List<String>listWithNamedQuery(String queryName){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		List<String>res = query.getResultList();
		
		entityManager.close();
		return res;
	}
	
	public List<Integer>countListWithNamedQuery(String queryName){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		List<Integer>res = query.getResultList();
		
		entityManager.close();
		return res;
	}
	
	public List<Double>countListWithNamedQuery2(String queryName){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		List<Double>res = query.getResultList();
		
		entityManager.close();
		return res;
	}
	
	//Trả về sồ lượng dữ liệu có trong database bằng query
	public long countWithNamedQuery(String queryName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		
		long res = (long)query.getSingleResult();
		entityManager.close();
		return res;
	}
	
	public long countWithNamedQuery(String queryName, String parameterName, Object parameterValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(parameterName, parameterValue);
		
		long res = (long)query.getSingleResult();
		entityManager.close();
		return res;
	}
	
	public void close() {
		if(entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
}
