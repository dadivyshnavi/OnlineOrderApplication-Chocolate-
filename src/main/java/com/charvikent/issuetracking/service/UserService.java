package com.charvikent.issuetracking.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.charvikent.issuetracking.config.SendSMS;
import com.charvikent.issuetracking.dao.UserDao;
import com.charvikent.issuetracking.model.Designation;
import com.charvikent.issuetracking.model.User;

@Service
@Transactional
public class UserService {

	private final static Logger logger = Logger.getLogger(AdminService.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private SendSMS smsTemplate;


    SendSMS smstemplate =new SendSMS();

	public void saveUser(User user) throws IOException
	{
		String msg =user.getFirstname()+" "+user.getLastname()+",  Successfully registered with KPTMS. \n You can login using \n Username:  "+user.getContactno()+"\n password: "+user.getPassword();
		String mbnum=user.getContactno();
		userDao.saveuser(user);
		logger.info("Sending message.......");
		smsTemplate.sendSMS(msg,mbnum);
	}

	public List<User> getAllUsers()
	{
    User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		List<User> usersListForAdmin =new ArrayList<>();
		List<User> usersListForMaster= userDao.getAllUsers();
		
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_MASTERADMIN")))
			   return usersListForMaster;
		 else 
		 {
			 for(User entry :usersListForMaster)
			 {  
					 if(entry.getId()!=(objuserBean.getId()))
					 usersListForAdmin.add(entry);
			 }
			 return usersListForAdmin;
		 }
	}
	
	public Map<Integer, String> getRoles()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Designation> rolesList= userDao.getRoles();
		for(Designation bean: rolesList){
			rolesMap.put(bean.getId(), bean.getName());
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
	}

	public User  findWithName(String username, String lpassword)
	{
		 User userdao=null;
		try {
			userdao= userDao.findWithName(username, lpassword);
		} catch (Exception e) {
			System.out.println("error occured service");
			e.printStackTrace();
		}
	return userdao;
	}

	public boolean deleteUser(Integer id,String enabled) {
		return userDao.deleteUser(id,enabled);
	}

	public User getUserById(Integer id) {
		User obj=userDao.getUserById(id);
		return obj;
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void updatePassword(User user) {
		userDao.updatePassword(user);
	}

	public void setLoginRecord(Integer id,String str) {
		userDao.setLoginRecord(id,str);
	}
	
	public User getUserByObject(User user) {
		return userDao.getUserByObject(user);
	}

	public List<User> getInActiveList() {
		return userDao.getInActiveList();
	}

	public User checkUserExistOrNotbyMobile(String mobileno) {
	
		return userDao.checkUserExistOrNotbyMobile(mobileno);
	}

	public User getUserDesignationById(Integer id) 
	{
		User obj=userDao.getUserDesignationById(id);
		return obj;
	}


}
