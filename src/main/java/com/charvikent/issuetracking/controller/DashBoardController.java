package com.charvikent.issuetracking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.charvikent.issuetracking.dao.DashBoardDao;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.service.DashBoardService;
import com.charvikent.issuetracking.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class DashBoardController {
	@Autowired HttpSession session;
	@Autowired DashBoardService dashBoardService;
	@Autowired private UserService userService;
	@Autowired DashBoardDao dashBoardDao;
	@RequestMapping("/dashBoard")
	public String showDashBoardPage(Model model,HttpServletRequest request,HttpSession session) throws JsonProcessingException
	{
		 model.addAttribute("lastLoginTime", dashBoardService.getLastLoginTime());
		 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 User userDesignation= userService.getUserDesignationById(objuserBean.getId());
		 session.setAttribute("userDesignationSession", userDesignation);
		return "dashBoard";
	}
	
	}
	