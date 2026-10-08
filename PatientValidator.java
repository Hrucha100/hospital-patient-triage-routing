package com.kiranacademy.hospital;



import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientValidator {
	private PatientRoutingService psService;
	private List<Patient> patients =null;
	private List<String>  vmsg =new ArrayList<String>();
	private Connection connection;
	
	int criticalCount = 0;
	int generalCount = 0;
	int validationFailed = 0;
	int skippedDuplicate = 0;

	
	

	public PatientValidator(PatientRoutingService psService) {
	    this.psService = psService;
	    this.connection = psService.connection;
	}
	
	
	public boolean isCritical(Patient patient) {
		if(patient.getCondition_status().equalsIgnoreCase("Critical"))
			return true;
		else if(patient.getAdmission_type().equalsIgnoreCase("Emergency") && patient.getTriage_score()>=7)
			return true;
		else if(patient.getCondition_status().equalsIgnoreCase("Moderate") && patient.getTriage_score()>=8)
		    return true;
		else
			return false;
	}

	
	public List<String> validatePatient(Patient patient){
		
		
		
		 Date currentDate = new Date();
		
			    if(patient.getPatient_name().length()<3 || patient.getPatient_name().isBlank())
			    {
			    	 vmsg.add("Patient " + patient.getPatient_id()
		               + "-> FAILED ->Invalid Patient Name");
			    	 validationFailed++;
			    	
			    	    } 
			    else  if(patient.getDisease().isBlank()) {
			    	 vmsg.add("Patient " + patient.getPatient_id()
		               + "-> FAILED ->Invalid disease Name");
			    	 validationFailed++;
			    	
			    }
			    else  if(patient.getDoctor_name().isBlank()) {
			    	
			    	 vmsg.add("Patient " + patient.getPatient_id()
		               + "-> FAILED ->Invalid Doctor Name");
			    	 validationFailed++;
			    	 
			    }
			    
			    else  if(patient.getAddmission_date().after(currentDate)) {
				  vmsg.add("Patient " + patient.getPatient_id()
	               + "-> FAILED ->Invalid admission_date");
				  validationFailed++;
				  
				 
			  }
			  
			 
				 
			    else if(psService.alreadyTransferred(patient.getPatient_id(),connection)) {
			   vmsg.add("Patient " + patient.getPatient_id()
               + " SKIPPED -> Already processed");
			   skippedDuplicate++;
			  
			   
			   }
		   
			    else if(isCritical(patient)) {
		if( psService.insertCriticalPatient(patient, connection))
		{
			vmsg.add("Patient " + patient.getPatient_id()
            + "-> CRITICAL CARE -> SUCCESS");
			  criticalCount++;
		}
		else {
			vmsg.add("Patient " + patient.getPatient_id()
            + "-> CRITICAL CARE -> ROLLBACK -> Source update/Destination insert failed.");
		}
			  }else {
				  if(psService.insertGeneralPatient(patient, connection)) {
				  vmsg.add("Patient " + patient.getPatient_id()
	               + "-> GENERAL CARE -> SUCCESS");
				  generalCount++;
				  }
					else {
						vmsg.add("Patient " + patient.getPatient_id()
			            + "-> GENERAL CARE  -> ROLLBACK -> Source update/Destination insert failed.");
					}
			  }
		
		
		return vmsg;
		
	}

}
