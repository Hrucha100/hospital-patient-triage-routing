package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;



public class PatientRoutingService implements PatientDAO {

	private ResultSet resultSet=null;
	private  PreparedStatement preparedStatement=null;
	private DBConnection dbConnection=new DBConnection();
	public Connection connection=dbConnection.getConnection();
	private List<Patient> patients =null;
	private   Patient patient = null;
	
	@Override
	public List<Patient> getPendingPatients() {
		String selectquery = "select * from hospital_patient_intake"
				+ " where transfer_status= \"Pending\" ";
		   patients = new ArrayList<Patient>();
		  
		try {
//			connection=dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(selectquery);
		    resultSet = preparedStatement.executeQuery();
		    
		 
		   while(resultSet.next()) {
			   patient=new Patient();
			
				   
				   patient.setPatient_id(resultSet.getInt(1));
				   patient.setPatient_name(resultSet.getString(2));
				   patient.setAge(resultSet.getInt(3));
				   patient.setDisease(resultSet.getString(5));
				   patient.setAdmission_type(resultSet.getString(6));
				   patient.setCondition_status(resultSet.getString(7));
				   patient.setTriage_score(resultSet.getInt(8));
				   patient.setDoctor_name(resultSet.getString(9));
				   patient.setAddmission_date(resultSet.getDate(10));
				
				   patients.add(patient);
				   
				   
			   }
		   
		
		   
		   if(patients.isEmpty()) {
			   System.out.println("Data Not Found....");
		   }
		   
		  
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
//			closeConnection();
			
		}
		
		
		return patients;
	}

	public void closeConnection() {

	    try {

	        if (resultSet != null) {
	            resultSet.close();
	        }

	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }

	        if (connection != null) {
	            connection.close();
	        }

	        System.out.println("Connection Close Successfully!!!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public Patient getEmployeeByID(int id) {
		// TODO Auto-generated method stub
		String query= "select * from hospital_patient_intake where patient_id=?";
		Patient patient = new Patient();
		try {
			//connection=dbConnection.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
			
				 patient.setPatient_id(resultSet.getInt(1));
				   patient.setPatient_name(resultSet.getString(2));
				   patient.setAge(resultSet.getInt(3));
				   patient.setDisease(resultSet.getString(5));
				   patient.setAdmission_type(resultSet.getString(6));
				   patient.setCondition_status(resultSet.getString(7));
				   patient.setTriage_score(resultSet.getInt(8));
				   patient.setDoctor_name(resultSet.getString(9));
				   patient.setAddmission_date(resultSet.getDate(10));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//closeConnection();
		}
		
		
		return patient;
	}
	
	
public boolean alreadyTransferred(int patient_id, Connection connection) {
	String query= "select * from general_care_patients where source_patient_id=?";
	String query2= "select * from critical_care_patients where source_patient_id=?";
	
		
		try {
			
			preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1, patient_id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			
			preparedStatement= connection.prepareStatement(query2);
			preparedStatement.setInt(1, patient_id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
		
		
	}


@Override
public boolean insertCriticalPatient(Patient patient, Connection con) {
	
	String insertQuery =
	        "INSERT INTO critical_care_patients "
	        + "(source_patient_id, patient_name, age, disease, "
	        + "admission_type, condition_status, triage_score, "
	        + "doctor_name, routed_at) "
	        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	Date currentDate = new Date(System.currentTimeMillis());
	try {
		 con.setAutoCommit(false);
		preparedStatement = con.prepareStatement(insertQuery);
		preparedStatement.setInt(1, patient.getPatient_id());
		preparedStatement.setString(2,patient.getPatient_name());
		preparedStatement.setInt(3, patient.getAge());
		preparedStatement.setString(4,patient.getDisease());
		preparedStatement.setString(5, patient.getAdmission_type());
		preparedStatement.setString(6, patient.getCondition_status());
		preparedStatement.setInt(7, patient.getTriage_score());
		preparedStatement.setString(8, patient.getDoctor_name());
		preparedStatement.setDate(9, currentDate);
		
		 int insertRow = preparedStatement.executeUpdate();

	        if (insertRow > 0) {

	            // For rollback testing 
//	            int updateRow = markProcessed(9999, con);
                int updateRow =markProcessed(patient.getPatient_id(), con);
	            if (updateRow > 0) {
       
	                con.commit();
                    return true;

	            } else {
	            	
	                con.rollback();

	               
	                return false;
	            }

	        } else {

	            con.rollback();

	           
	            return false;
	        }

	    
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		  return false;
	}
	
	
	
}


public int markProcessed(int patientId, Connection con) {
	
	String updateQuery = "UPDATE hospital_patient_intake "
            + "SET transfer_status = 'PROCESSED', "
            + "processed_at = NOW() "
            + "WHERE patient_id = ?";
	
	try {	
		
		preparedStatement= con.prepareStatement(updateQuery);
	preparedStatement.setInt(1, patientId);
	int row=preparedStatement.executeUpdate();
	return row;
	}
	catch(SQLException s) {
		System.out.println(s);
		return 0;
	}
	
	
	
}
@Override
public boolean insertGeneralPatient(Patient patient, Connection con) {
	String insertQuery =
	        "INSERT INTO general_care_patients "
	        + "(source_patient_id, patient_name, age, disease, "
	        + "admission_type, condition_status, triage_score, "
	        + "doctor_name, routed_at) "
	        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	Date currentDate = new Date(System.currentTimeMillis());
	try {
		 con.setAutoCommit(false);
		preparedStatement = con.prepareStatement(insertQuery);
		preparedStatement.setInt(1, patient.getPatient_id());
		preparedStatement.setString(2,patient.getPatient_name());
		preparedStatement.setInt(3, patient.getAge());
		preparedStatement.setString(4,patient.getDisease());
		preparedStatement.setString(5, patient.getAdmission_type());
		preparedStatement.setString(6, patient.getCondition_status());
		preparedStatement.setInt(7, patient.getTriage_score());
		preparedStatement.setString(8, patient.getDoctor_name());
		preparedStatement.setDate(9, currentDate);
		
		 int insertRow = preparedStatement.executeUpdate();

	        if (insertRow > 0) {

	            // For rollback testing 
	          //  int updateRow = markProcessed(9999, con);
	        	 int updateRow =markProcessed(patient.getPatient_id(), con);
	 	        
	            if (updateRow > 0) {
    
	                con.commit();
                    return true;

	            } else {
	            	
	                con.rollback();

	               
	                return false;
	            }

	        } else {

	            con.rollback();

	            
	              return false;
	        }

	    
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
	
}
	
}
