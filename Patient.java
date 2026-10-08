package com.kiranacademy.hospital;

import java.util.Date;

public class Patient {
	
	private int patient_id;
	private String patient_name ;
	private int age;
	private String disease;
	private String admission_type;
	private String condition_status;
	private int triage_score;
	private String doctor_name;
	private Date addmission_date;
	
	

	public Patient(int patient_id, String patient_name, int age, String disease, String admission_type,
			String condition_status, int triage_score, String doctor_name, Date addmission_date) {
		super();
		this.patient_id = patient_id;
		this.patient_name = patient_name;
		this.age = age;
		this.disease = disease;
		this.admission_type = admission_type;
		this.condition_status = condition_status;
		this.triage_score = triage_score;
		this.doctor_name = doctor_name;
		this.addmission_date = addmission_date;
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getAdmission_type() {
		return admission_type;
	}

	public void setAdmission_type(String admission_type) {
		this.admission_type = admission_type;
	}

	public String getCondition_status() {
		return condition_status;
	}

	public void setCondition_status(String condition_status) {
		this.condition_status = condition_status;
	}

	public int getTriage_score() {
		return triage_score;
	}

	public void setTriage_score(int triage_score) {
		this.triage_score = triage_score;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public Date getAddmission_date() {
		return addmission_date;
	}

	public void setAddmission_date(Date addmission_date) {
		this.addmission_date = addmission_date;
	}

	@Override
	public String toString() {
		return "Patient [patient_id=" + patient_id + ", patient_name=" + patient_name + ", age=" + age + ", disease="
				+ disease + ", admission_type=" + admission_type + ", condition_status=" + condition_status
				+ ", triage_score=" + triage_score + ", doctor_name=" + doctor_name + ", addmission_date="
				+ addmission_date + "]";
	}
	
	
	

}
