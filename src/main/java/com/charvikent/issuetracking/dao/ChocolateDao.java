package com.charvikent.issuetracking.dao;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.charvikent.issuetracking.config.KptsUtil;
import com.charvikent.issuetracking.model.Chocolate;
@Repository
@Transactional
public class ChocolateDao 
{
	@PersistenceContext private EntityManager em;
	@Autowired private JdbcTemplate jdbcTemplate;
	@Autowired KptsUtil kptsUtil;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public void saveChocolates(Chocolate ch)
	{
		logger.info("saving chocolates");
		/*String orderid=kptsUtil.randNum();
		ch.setOrderId(orderid);*/
		em.persist(ch);
	}
	
	@SuppressWarnings("unchecked")
	public Chocolate getChocolateByObject(Chocolate ch) 
	{
		String hql ="from Chocolate where contact='"+ch.getContact()+"'";
		List<Chocolate> choco= em.createQuery(hql).getResultList();
		if(choco.size() > 0)
			return choco.get(0);
		return null;
	}
	
	public Chocolate getChocolateById(String id) 
	{

		return em.find(Chocolate.class, id);
	}
	public void updateChocolate(Chocolate ch) 
	{
		
		Chocolate choco = getChocolateById(ch.getOrderId());
		choco.setCustomerName(ch.getCustomerName());
		choco.setContact(ch.getContact());
		choco.setDeliveryDate(ch.getDeliveryDate());
		choco.setDeliveryLocation(ch.getDeliveryLocation());
		choco.setDeliveryMode(ch.getDeliveryMode());
		choco.setModel(ch.getModel());
		choco.setModeOfPayment(ch.getModeOfPayment());
		choco.setNotes(ch.getNotes());
		choco.setWeight(ch.getWeight());
		choco.setQuantity(ch.getQuantity());
		/*choco.setOrderId(ch.getOrderId());*/
		/*em.merge(choco);*/
		em.flush();
	}
	
	public List<Chocolate> getAllChocolates()
	{
			String sql="select * from chocolateordering";
			RowMapper<Chocolate> rowMapper = new BeanPropertyRowMapper<Chocolate>(Chocolate.class);
			return  this.jdbcTemplate.query(sql, rowMapper);	
	}

	public List<Chocolate> getChocolatesByOrderId(Chocolate chocolate) 
	{
		String sql="select * from chocolateordering where order_id='"+chocolate.getOrderId()+"'";
		RowMapper<Chocolate> rowMapper = new BeanPropertyRowMapper<Chocolate>(Chocolate.class);
		return  this.jdbcTemplate.query(sql, rowMapper);	
	}
	
	public List<Map<String, Object>> getChocolateStatusCount(Chocolate chocolate) 
	{
	String sql="select ch.chocostatus as orderedstatus,count(ch.chocostatus) as number from chocolateordering ch where ch.chocostatus='"+chocolate.getChocostatus()+"'";
	List<Map<String, Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
	return retlist;
	}		
}
