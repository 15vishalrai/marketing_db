package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.dto.LeadData;
import com.marketingapp.entities.Lead;
import com.marketingapp.services.LeadService;
import com.marketingapp.utility.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private EmailService emailService;
	
	
	@RequestMapping("/viewLeadPage")  // It is like @Webservlet annotation
	public String viewSaveLeadPage() {
		return "new_lead";   //telling to load this page
		
		
	}
//	@RequestMapping("/saveLead")
//	public String saveoneLead(@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastName,@RequestParam("email")String email,@RequestParam("mobile")long mobile) {
//		Lead l=new Lead();
//		l.setFirstName(firstName);
//		l.setLastName(lastName);
//		l.setEmail(email);
//		l.setMobile(mobile);
//		leadService.saveLead(l);
//		return "new_lead"; // Means i have to stay on this page after getting the values
//	}
	 // first method to read user data by using request paramaters
	
//	@RequestMapping("/saveLead")
//	public String SaveOneLead(LeadData leadData) { // DTO class purpose is to store form data to this class
//		Lead l =new Lead();
//		l.setFirstName(leadData.getFirstName());
//		l.setLastName(leadData.getLastName());
//		l.setEmail(leadData.getEmail());
//		l.setMobile(leadData.getMobile());
//		leadService.saveLead(l);
//		// Here we are copying data from data transfer object to entity class object and then to database
//		
//		return "new_lead"; // page name on which it should redirect to
//		
//	}
 
	
	//Second method to get the user data using model attribute it binds form data with entity class object
	
	
	@RequestMapping("/saveLead")
	public String SaveOneLead(@ModelAttribute Lead lead, ModelMap model)
	{
		leadService.saveLead(lead);// This will call service layer
		emailService.sendSimpleMessage(lead.getEmail(),"Test","how are you");
		model.addAttribute("msg", "Lead is saved");
		return "new_lead";
		//lead.getEmail() will take the user entered email
	}
	//model map model will work like request.set attribute and request.getattribtue
	
	
	@RequestMapping("/listall")
	public String listAll(ModelMap model) {
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads",leads);
		System.out.println(leads);
		return "lead_search_result";
		}
	
	
	@RequestMapping("/delete")
	public String deleteOneLocation(@RequestParam("id") long id,ModelMap model) {
		leadService.deleteLead(id);
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads", leads);
		return"lead_search_result";
	}
	
	@RequestMapping("/update")
	public String updateOneLocation(@RequestParam("id") long id,ModelMap model) {
		Lead lead = leadService.findOneLead(id);
		model.addAttribute("lead",lead);
		return"update_lead1";
	}
	
	@RequestMapping("/updateLead")
	public String updateLead(LeadData lead,ModelMap model) {   // This is done by data transfer object DTO
		Lead l=new Lead();
		l.setId(lead.getId());
		l.setFirstName(lead.getFirstName());
		l.setLastName(lead.getLastName());
		l.setEmail(lead.getEmail());
		l.setMobile(lead.getMobile());
		
		leadService.saveLead(l);
		
		model.addAttribute("msg","lead is updated!!");
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads",leads);
		return"lead_search_result";
	}
	

	
	
		
	

}
