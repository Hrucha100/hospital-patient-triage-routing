package com.kiranacademy.hospital;



import java.util.ArrayList;
import java.util.List;

public class HospitalRoutingApp {
	
	public static void main(String[] args) {
	
		
		PatientRoutingService pService = new  PatientRoutingService();
	List<Patient> patients=	pService.getPendingPatients();
//		List<Patient> list= new ArrayList<Patient>();
//		list.add(pService.getEmployeeByID(27));
		 ProcessingSummary pSummary = new ProcessingSummary(pService);
		 pSummary.getProcessingSumarry(patients);
		
		pService.closeConnection();
		

			
		

		
	}

}
