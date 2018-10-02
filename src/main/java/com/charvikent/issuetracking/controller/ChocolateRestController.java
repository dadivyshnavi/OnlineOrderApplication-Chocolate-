package com.charvikent.issuetracking.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.castor.core.util.Base64Decoder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.charvikent.issuetracking.config.KhaibarGasUtil;
import com.charvikent.issuetracking.dao.ChocolateDao;
import com.charvikent.issuetracking.model.Chocolate;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ChocolateRestController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ChocolateRestController .class);
	@Autowired ChocolateDao chocolateDao;
	
	@RequestMapping("/Chocolate")
	public String showChocolateOrderForm(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		LOGGER.debug("Calling Chocolate at controller");
		return null;	
	}
	
	/*@SuppressWarnings("unused")
	@PostMapping(value="/saveChocolates", consumes = "application/json", produces = "application/json")  
	public HashMap<String, String>  saveChocolate( @RequestBody Chocolate chocolate,HttpServletRequest request) 
	{
		LOGGER.debug("Calling saveChocolates at controller");
		String code =null;
		String orderId=chocolate.getOrderId();
		String customername=chocolate.getCustomerName();
		String contact=chocolate.getContact();
		String deliverydate=chocolate.getDeliveryDate();
		String deliverymode=chocolate.getDeliveryMode();
		String deliverylocation=chocolate.getDeliveryLocation();
		String weight=chocolate.getWeight();
		String modeofpayment=chocolate.getModeOfPayment();
		String notes=chocolate.getNotes();
		String quantity=chocolate.getQuantity();
		String model=imgdecoder(chocolate.getModel(),request);
		if(!chocolate.getModel().isEmpty())
		{
			chocolate.setModel(model);
		}
		chocolateDao.saveChocolates(chocolate);
		code = "success";
		HashMap<String,String> hm =new HashMap<String,String>();
		hm.put("status", code);
		return hm;
	}*/
	
	@PostMapping(value="/saveChocolates", consumes = "application/json", produces = "application/json")
	public String saveChocolates( @RequestBody Chocolate chocolate,HttpServletRequest request) throws JSONException 
	{
	LOGGER.debug("Calling saveChocolate at controller");
	JSONObject json =new JSONObject();
	String code ="";
	String id = null;
	try
	{
		Chocolate orgBean= chocolateDao.getChocolateByObject(chocolate);
		String  dummyId =null;
		if(orgBean != null){
			dummyId =orgBean.getOrderId();
		}
		if(chocolate.getOrderId()==null)
		{
			if(dummyId ==null)
			{
				
				if(!chocolate.getModel().isEmpty())
				{
					String model=imgdecoder(chocolate.getModel(),request);
					chocolate.setModel(model);
				}
				chocolateDao.saveChocolates(chocolate);
				code ="success";
				json.put("orderId", chocolate.getOrderId());
			} else
			{
				code ="exists";	
			}
		}
		else
		{
			id=chocolate.getOrderId();
			if(id == dummyId || orgBean == null)
			{
				
				if(!chocolate.getModel().isEmpty())
				{
					String model=imgdecoder(chocolate.getModel(),request);
					chocolate.setModel(model);
				}
				chocolateDao.updateChocolate(chocolate);
				code ="updated";	
			} else
			{
				code ="exists";
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println(e);
	}
	json.put("status", code);
	return String.valueOf(json);
	}
	
	@SuppressWarnings("static-access")
	private String  imgdecoder(String imgData, HttpServletRequest request) 
	 {
	    	String filepath = null;
	    	FileOutputStream osf;
	    	KhaibarGasUtil utils=new KhaibarGasUtil();
	    	String id =utils.randNum(4);
			Base64Decoder decoder = new Base64Decoder(); 
			//byte[] imgBytes = decoder.decode(imgData.split(",")[1]);
			byte[] imgBytes = decoder.decode(imgData);
			/*name=name.substring(n + 1);
			name=name+".png";*/
			long millis = System.currentTimeMillis() % 1000;
			filepath= id+millis+".png";
            //String rootPath = request.getSession().getServletContext().getRealPath("/");
			String rootPath = System.getProperty("catalina.base");
			//File dir = new File(rootPath + File.separator + "reportDocuments");
			File dir = new File(rootPath + File.separator + "webapps"+ File.separator + "images");
			 if (!dir.exists()) 
			 {
			    dir.mkdirs();
			 }
			        
			 System.out.println(dir);
			        
			  try 
			  {
			   osf = new FileOutputStream(new File(dir.getAbsolutePath() + File.separator + filepath));
			   osf.write(imgBytes);
			   osf.flush();
			  }catch (IOException e) 
			  {
			    System.out.println("error : " + e);
			  }
	           filepath= "images/"+filepath;
	    	return  filepath;	
		} 
	
	@RequestMapping(value="/getChocolatesByOrderId", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getChocolatesByOrderId( @RequestBody Chocolate chocolate) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getChocolatesByOrderId at controller");
		List <Chocolate> listOrderBeans =chocolateDao.getChocolatesByOrderId(chocolate);
		JSONObject json =new JSONObject();
			if(null != listOrderBeans)
			{
				json.put("chocolateslist", listOrderBeans);
			}
			else
				json.put("chocolateslist", "NOT_FOUND");
			
		return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getOrderStatusCount", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getOrderStatusList(Chocolate ch) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getOrderStatusCount at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<Map<String, Object>> listOrderBeans = chocolateDao.getChocolateStatusCount(ch);
		JSONObject json =new JSONObject();
		if(null != listOrderBeans)
		{
			json.put("chocolatestatuslist", listOrderBeans);
		}
		else
			json.put("chocolatestatuslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}

}
