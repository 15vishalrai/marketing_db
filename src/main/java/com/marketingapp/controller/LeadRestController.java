package com.marketingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marketingapp.entities.Lead;
import com.marketingapp.repositories.LeadRepository;

@RestController
public class LeadRestController {
	
	@Autowired
	
	
	
	private LeadRepository leadRepo;
	
	@GetMapping("listallleads")  // this will automatically convert java object into json object
	public List<Lead> getAllLeads(){
		List<Lead> leads = leadRepo.findAll(); // return data into Lead object and then save into leads refrence variable
		return leads;
	}
	
	@PostMapping("/saveApi")
	public void saveLead(@RequestBody  Lead lead) {  // request body will convert json content object into java object
		leadRepo.save(lead); 
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteLead(@PathVariable long id) {
		leadRepo.deleteById(id);
	}
	
	@PutMapping("/updateApi")
	public void updateLead(@RequestBody  Lead lead) {  // request body will convert json content object into java object
		leadRepo.save(lead);
	}
	
	@GetMapping("/getapi/{id}")
	public Lead getOneLead(@PathVariable long id) {
		
		try {
		Optional<Lead> findyById = leadRepo.findById(id);
		Lead lead=findyById.get();
		return lead;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
