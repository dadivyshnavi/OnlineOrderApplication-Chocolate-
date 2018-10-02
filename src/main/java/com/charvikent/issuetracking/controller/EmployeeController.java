package com.charvikent.issuetracking.controller;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.charvikent.issuetracking.config.FilesStuff;
import com.charvikent.issuetracking.config.MailTemplate;
import com.charvikent.issuetracking.config.SendSMS;
import com.charvikent.issuetracking.dao.UserDao;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeController {
	@Autowired SendSMS sendSMS;
	@Autowired private UserService userService;
	@Autowired FilesStuff fileTemplate;
	@Autowired UserDao userDao;
	@Autowired HttpSession session;
	@Autowired MailTemplate mailTemplate;
	
	@RequestMapping("/users")
	public String homeUser(Model model,HttpServletRequest request) {
		List<User> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("userf", new User());
		model.addAttribute("roles", userService.getRoles());
		model.addAttribute("allUsers", userService.getAllUsers());
		try {
			listOrderBeans = userService.getAllUsers();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "user";
	}

	@RequestMapping(value = "/users" ,method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute  User user, BindingResult bindingresults,
			RedirectAttributes redir,@RequestParam("file1") MultipartFile[] uploadedFiles) throws IOException {
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		int id = 0;
		try
		{
			User userBean=null;
			if(user.getId()!=null)
			{
			  userBean= userService.getUserByObject(user);
			}
			int dummyId =0;
			if(userBean != null){
				dummyId = userBean.getId();
			}
			if(user.getId()==null)
			{
				if(dummyId ==0)
				{
					try {
						int filecount =0;
						for(MultipartFile multipartFile : uploadedFiles) {
							String fileName = multipartFile.getOriginalFilename();
							if(!multipartFile.isEmpty())
							{
							filecount++;
							user.setFiles(fileTemplate.concurrentFileNames());
							multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
							}
						}
						if(filecount>0)
			        	 {
			        		 user.setFiles(fileTemplate.concurrentFileNames());
			        		 fileTemplate.clearFiles();
			        		 
			        	 }
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					user.setEnabled("1");
					userService.saveUser(user);
					redir.addFlashAttribute("msg", "Record Inserted Successfully");
					redir.addFlashAttribute("cssMsg", "success");
				} else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}
			}
			else
			{
				id=user.getId();
				if(id == dummyId || userBean == null)
				{
					try {
						int filecount =0;
						for(MultipartFile multipartFile : uploadedFiles) {
							String fileName = multipartFile.getOriginalFilename();
							if(!multipartFile.isEmpty())
							{
							filecount++;
							user.setFiles(fileTemplate.concurrentFileNames());
							multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
							}
						}
						if(filecount>0)
			        	 {
			        		 user.setFiles(fileTemplate.concurrentFileNames());
			        		 fileTemplate.clearFiles(); 
			        	 }
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					userService.updateUser(user);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");

				} else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "redirect:users";
	}

	@RequestMapping(value = "/deleteUser")
	public @ResponseBody String deleteEmployee(User  objUser,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<User> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objUser.getId() != 0){
 				delete = userService.deleteUser(objUser.getId(),objUser.getEnabled());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
			listOrderBeans = userService.getAllUsers();
			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {

				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);
		}
		return String.valueOf(jsonObj);
	}

	@RequestMapping("/changePassword")
	public String changePasswordHome(@ModelAttribute("changePassword") User user){
		return "changePassword";
	}
	@RequestMapping(value="/changePassword", method= RequestMethod.POST )
	public String changePassword(@ModelAttribute("changePassword") User user,RedirectAttributes redir,HttpServletRequest request){
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User users = userService.getUserById(objuserBean.getId());
		if(users.getPassword().equals(user.getPassword())) {
			users.setPassword(user.getCpassword());
			userService.updatePassword(users);
			redir.addFlashAttribute("msg", "Password Updated Successfully");
			redir.addFlashAttribute("cssMsg", "warning");
			return "redirect:changePassword";
		}else {
			request.setAttribute("msg", "You Entered Wrong Password");
			request.setAttribute("cssMsg", "warning");
			return "changePassword";
		}
	}

	@RequestMapping("/editProfile")
	public String editProfileHome(@ModelAttribute("editProfile") User user,Model model){
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String id=String.valueOf(objuserBean.getId());
		model.addAttribute("editProfile", userService.getUserById(objuserBean.getId()));
		return "editProfile";
	}
	@RequestMapping(value="/editProfile", method= RequestMethod.POST )
	public String editProfile(@ModelAttribute("editProfile") User user,RedirectAttributes redir,HttpServletRequest request){
		User emp = userService.getUserById(user.getId());
		emp.setFirstname(user.getFirstname());
		emp.setLastname(user.getLastname());
		emp.setContactno(user.getContactno());
		emp.setEmail(user.getEmail());
		userService.updateUser(emp);
		redir.addFlashAttribute("msg", "Your Details Updated Successfully");
		redir.addFlashAttribute("cssMsg", "warning");
			return "editProfile";
	}

	@RequestMapping(value = "/inActiveUser")
	public @ResponseBody String getAllActiveOrInactiveUsers(User  objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<User> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getEnabled().equals("0"))
				listOrderBeans = userService.getInActiveList();
				else
				listOrderBeans = userService.getAllUsers();
			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);
		}
		return String.valueOf(jsonObj);
	}

	@RequestMapping(value = "/getresetpassword" ,method = RequestMethod.POST)
	public @ResponseBody Boolean getResetUserPassword(Model model,HttpServletRequest request) throws IOException, MessagingException 
	{
				/*LOGGER.debug("Calling getresetpassword at controller");*/
					System.out.println("enter to getresetpassword");
					String contactno=request.getParameter("contactno");
			User custbean2=userService.checkUserExistOrNotbyMobile(contactno);
					if(custbean2 != null)
					{
						sendSMS.sendSMS(custbean2.getPassword(),contactno);
						mailTemplate.resetPassword(custbean2);
						return true;	
					}
					else
						return false;
				} 
}
