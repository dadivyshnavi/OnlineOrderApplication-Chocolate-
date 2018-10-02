package com.charvikent.issuetracking.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.charvikent.issuetracking.model.ChocolatesStatus;

public class ChocoStatusDao 
{
	@PersistenceContext private EntityManager em;

	public void saveChocolateStatus(ChocolatesStatus ch) 
	{
		em.persist(ch);	
	}
	
	@SuppressWarnings("unchecked")
	public List<ChocolatesStatus> getChocolateStatus()
	 {  
		return (List<ChocolatesStatus>)em.createQuery("SELECT chocostatus FROM ChocolatesStatus chocolatesStatus").getResultList(); 
	 }
}
