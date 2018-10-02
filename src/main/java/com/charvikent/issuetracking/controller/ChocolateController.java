package com.charvikent.issuetracking.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.charvikent.issuetracking.config.FilesStuff;
import com.charvikent.issuetracking.dao.ChocolateDao;
import com.charvikent.issuetracking.model.Chocolate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ChocolateController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger( ChocolateController .class);
	@Autowired ChocolateDao chocolateDao; 
	@Autowired FilesStuff fileTemplate; 
	
	@RequestMapping("/choco")
	public String  ChocolateOrders( @ModelAttribute("chocof") Chocolate choco,Model model ,HttpServletRequest request) 
	{
		LOGGER.debug("Calling chocolate at controller");
		List<Chocolate> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("chocof",new Chocolate());
		try {
			listOrderBeans = chocolateDao.getAllChocolates();
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
		return "chocolate";
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/choco" ,method = RequestMethod.POST)
	public String saveChocolate(@Valid @ModelAttribute  Chocolate ch, BindingResult bindingresults,
			RedirectAttributes redir,@RequestParam("file1") MultipartFile[] uploadedFiles) throws IOException {
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		String id = null;
		try
		{
			Chocolate userBean =null;
			if( userBean.getOrderId()!="" &&  userBean.getOrderId() != null)
			{
				 userBean=  (Chocolate) chocolateDao.getChocolateByObject(ch);
			
			}
			String  dummyId =null;
			if(userBean != null){
				dummyId = userBean.getOrderId();
			}
			
			if(ch.getOrderId()==null)
			{
				if(dummyId ==null)
				{
					try {
						int filecount =0;
						for(MultipartFile multipartFile : uploadedFiles) {
							String fileName = multipartFile.getOriginalFilename();
							if(!multipartFile.isEmpty())
							{
							filecount++;
							multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
							}
						}
						if(filecount>0)
			        	 {
							ch.setModel(fileTemplate.concurrentFileNames());
			        		 fileTemplate.clearFiles();
			        		 
			        	 }
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					chocolateDao.saveChocolates(ch);
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
				id=ch.getOrderId();
				if(id == dummyId || userBean == null)
				{
					try {
						int filecount =0;
						for(MultipartFile multipartFile : uploadedFiles) {
							String fileName = multipartFile.getOriginalFilename();
							if(!multipartFile.isEmpty())
							{
							filecount++;
							multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
							}
						}
						if(filecount>0)
			        	 {
							ch.setModel(fileTemplate.concurrentFileNames());
			        		 fileTemplate.clearFiles(); 
			        	 }
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					chocolateDao.updateChocolate(ch);
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
		return "redirect:choco";
	}

}
