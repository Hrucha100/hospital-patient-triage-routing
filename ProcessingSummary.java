package com.kiranacademy.hospital;

import java.util.List;

public class ProcessingSummary {
	 PatientRoutingService psService;
	    PatientValidator pValidator;

	    public ProcessingSummary(PatientRoutingService psService) {
	        this.psService = psService;
	        this.pValidator = new PatientValidator(psService);
	    }
	    
	List<String> vm=null;
	  public void getProcessingSumarry(List<Patient> patients) {
		  
		  for(Patient patient:patients) {
			 vm= pValidator.validatePatient(patient);
		  }
		  
		  System.out.println("\nKIRAN ACADEMY - HOSPITAL PATIENT ROUTING");
         System.out.println("----------------------------------------");
		  for(String m:vm) {
			  System.out.println(m);
		  }
		  System.out.println("\nPROCESSING SUMMARY");
		  System.out.println("----------------------------------------");

		  System.out.println("Total Pending Records : " + patients.size());
		  System.out.println("Critical Care : " + pValidator.criticalCount);
		  System.out.println("General Care : " + pValidator.generalCount);
		  System.out.println("Validation Failed : " + pValidator.validationFailed);
		  System.out.println("Skipped Duplicate : " + pValidator.skippedDuplicate);
		  System.out.println("Successfully Processed: " + (pValidator.criticalCount+pValidator.generalCount));
		  
	  }
}
