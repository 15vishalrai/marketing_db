package com.marketingapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketingapp.entities.Lead;
import com.marketingapp.repositories.LeadRepository;

@Service
public class LeadServiceimpl implements LeadService {

	@Autowired
	private LeadRepository leadRepo;
	
	
	@Override
	public void saveLead(Lead lead) {
		leadRepo.save(lead);

	}


	@Override
	public List<Lead> getLeads() {
		List<Lead> leads = leadRepo.findAll(); // store database table into object and then object address store in a list
		return leads;
	}


	@Override
	public void deleteLead(long id) {
		leadRepo.deleteById(id);
		
	}
	
	
	@Override
	public Lead findOneLead(long id) {
		Optional<Lead> findById = leadRepo.findById(id);
		Lead lead = findById.get();
		return lead;
		
	}


	

}
