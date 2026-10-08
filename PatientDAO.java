package com.kiranacademy.hospital;


import java.sql.Connection;
import java.util.List;
 


public interface PatientDAO {
	
	
	public List<Patient> getPendingPatients();
	

	boolean alreadyTransferred(int patientId, Connection connection);
	
	boolean insertCriticalPatient(Patient patient, Connection con);
	boolean insertGeneralPatient(Patient patient, Connection con);
	
	public int markProcessed(int patientId, Connection con);

}
