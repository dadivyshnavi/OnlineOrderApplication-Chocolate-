package com.charvikent.issuetracking.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.config.KptsUtil;
import com.charvikent.issuetracking.model.Designation;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.model.UserLogs;

@Repository

public class UserDao {
	@PersistenceContext private EntityManager em;
	@Autowired private JdbcTemplate jdbcTemplate;
	@Autowired KptsUtil kptsUtil;

	public void saveuser(User user) 
	{
		String empid =kptsUtil.randNum();
		user.setEmpId(empid);
		em.persist(user);
	}

	public List<User> getAllUsers()
	{
			String sql="select u.id,u.emp_id,u.firstname,u.lastname,u.email,u.cemail,u.contactno,u.whatsappno,u.paddress,u.caddress,u.dob,d.name as designationname,u.username,u.password,CASE WHEN u.enabled IN ('0') THEN 'Deactive' WHEN u.enabled IN ('1') THEN 'Active'  ELSE '-----' END AS enabled,u.enabled as status,u.updated_time,u.designation,u.files from users u,designation d where u.enabled='1' and u.designation=d.id order by u.updated_time desc";
			RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
			return  this.jdbcTemplate.query(sql, rowMapper);	
	}
	
	@SuppressWarnings("unchecked")
	public List<Designation> getRoles()
	{
		return em.createQuery("select designation from Designation designation").getResultList();
	}

	public User findWithName(String username,String lpassword) {
		User userbean =null;
		try {
			userbean=  (User) em.createQuery(" select u FROM User u where  enabled='1' and u.username =:custName AND u.password =:custPass ")
					.setParameter("custName", username)
					.setParameter("custPass", lpassword)
					.getSingleResult();

		} catch (Exception e) {
			return userbean;
		}
		return userbean;
	}

	public User getUserById(Integer id) {

		return em.find(User.class, id);
	}

	public void updateUser(User user) 
	{
		User users=getUserById(user.getId());
		users.setFirstname(user.getFirstname());
		users.setLastname(user.getLastname());
		users.setEmail(user.getEmail());
		users.setCemail(users.getCemail());
		users.setContactno(user.getContactno());
		users.setWhatsappno(user.getWhatsappno());
		users.setPaddress(user.getPaddress());
		users.setCaddress(user.getCaddress());
		users.setDob(user.getDob());
		users.setUsername(user.getUsername());
		users.setPassword(user.getPassword());
		users.setDesignation(user.getDesignation());
		users.setFiles(user.getFiles());
		em.merge(users);
		em.flush();
	}

	public void updatePassword(User user) {
		em.merge(user);
		em.flush();
	}

	public User find(Integer id) 
	{
		return em.find(User.class, id);
	}

	public void setLoginRecord(Integer id,String str) {
		UserLogs logs =new UserLogs();
		logs.setEmpid((String.valueOf(id)));
		logs.setSessionname(str);
		em.merge(logs);
	}

	public boolean deleteUser(Integer id, String status) 
	{
		Boolean delete=false;
		try{

			User design= (User)em.find(User.class ,id);
			   design.setEnabled(status);
			   em.merge(design);
			if(!status.equals(design.getEnabled()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String mobileno)
	{
		String hql ="From User where contactno= '"+mobileno+"' ";
		Query query = em.createQuery(hql);	
		List<User> list =query.getResultList();
		       if(list.size() >0)
		       {
		    	   return list.get(0);
		       }
		       else
		       return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findRoleByUserName(String Username)
	{
		String hql="select mr.desigrole from User as u,Designation as r,MultiRoles as mr where contactno='"+Username+"' and u.designation=r.id and r.id=mr.id ";
		Query query=em.createQuery(hql);
		List<String> results = query.getResultList();
		return results;
	}

	@SuppressWarnings("unchecked")
	public User getUserByObject(User user) {
		String hql ="from User where (username='"+user.getContactno()+"'or cemail='"+user.getCemail()+"')";
		Query query =em.createQuery(hql);
		System.out.println(hql);
		List<User>usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
        else
		return usersList.get(0);
	}

	public List<User> getInActiveList() 
	{
		String sql="select u.id,u.emp_id,u.firstname,u.lastname,u.email,u.cemail,u.contactno,u.whatsappno,u.paddress,u.caddress,u.dob,d.name as designationname,u.username,u.password,CASE WHEN u.enabled IN ('0') THEN 'Deactive' WHEN u.enabled IN ('1') THEN 'Active'  ELSE '-----' END AS enabled,u.enabled as status,u.updated_time,u.designation,u.files from users u,designation d where u.enabled='0' and u.designation=d.id order by u.updated_time desc";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		   return  this.jdbcTemplate.query(sql, rowMapper);
	}

	@SuppressWarnings("unchecked")
	public User checkUserExistOrNotbyMobile(String mobileno) {
		String hql ="from User where contactno=" +mobileno;
		Query query =em.createQuery(hql);
		System.out.println(hql);
		List<User>usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
        else
		return usersList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public User getUserDesignationById(Integer id) 
	{
		String hql ="select  u.username, d.name from designation d,users u where u.designation=d.id and u.id=:id ";
		User users =new User();
		try{
			List<Object[]> rows = em.createNativeQuery(hql).setParameter("id", id).getResultList();	
		for (Object[] row : rows) {
			users.setUsername((String) row[0]);
			users.setDesignationname((String) row[1]);		
		}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return  users;
	}
}
