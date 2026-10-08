
create database hospital_Task;
use hospital_Task;
create table hospital_patient_intake(
patient_id INT primary key auto_increment,
patient_name VARCHAR(100) not null,
age INT not null,
gender VARCHAR(10) not null,
disease VARCHAR(120) not null,
admission_type VARCHAR(20) not null,
condition_status VARCHAR(20) not null,
triage_score INT not null,
 doctor_name VARCHAR(100) not null,
 admission_date DATE not null,
 mobile VARCHAR(15) not null,
 transfer_status VARCHAR(20)not null default 'Pending',
 processed_at DATETIME null,
 created_at DATETIME default current_timestamp,
--  Age Constraint
constraint chk_age check (age between 0 and 120),

--  Gender constraint
constraint chk_gender check (gender in ('Male','Female','Other')),

-- admission type Contraint
constraint chk_admission_type
check( admission_type in ('Emergency','Regular')),

-- Condition Constraint
constraint chk_condition_status 
check ( condition_status in ('Critical' , 'Moderate' , 'Stable')),

--  Triage Score Constriant
 constraint chk_triage_score
 check (triage_score between 1 and 10),
 
 
--  Mobile Number Constriant

constraint chk_mobile
check ( mobile regexp '^[0-9]{10}$'),

-- Transfer Status Constraint
constraint chk_transfer_status
check (transfer_status in ('PENDING','PROCESSED'))

);


CREATE TABLE critical_care_patients (

    critical_id INT PRIMARY KEY AUTO_INCREMENT,

    source_patient_id INT NOT NULL UNIQUE,

    patient_name VARCHAR(100) NOT NULL,

    age INT NOT NULL,

    disease VARCHAR(120) NOT NULL,

    admission_type VARCHAR(20) NOT NULL,

    condition_status VARCHAR(20) NOT NULL,

    triage_score INT NOT NULL,

    doctor_name VARCHAR(100) NOT NULL,

    routed_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_critical_patient
        FOREIGN KEY (source_patient_id)
        REFERENCES hospital_patient_intake(patient_id)
);

CREATE TABLE general_care_patients (

    general_id INT PRIMARY KEY AUTO_INCREMENT,

    source_patient_id INT NOT NULL UNIQUE,

    patient_name VARCHAR(100) NOT NULL,

    age INT NOT NULL,

    disease VARCHAR(120) NOT NULL,

    admission_type VARCHAR(20) NOT NULL,

    condition_status VARCHAR(20) NOT NULL,

    triage_score INT NOT NULL,

    doctor_name VARCHAR(100) NOT NULL,

    routed_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_general_patient
        FOREIGN KEY (source_patient_id)
        REFERENCES hospital_patient_intake(patient_id)
);


INSERT INTO hospital_patient_intake
(patient_name, age, gender, disease, admission_type,
 condition_status, triage_score, doctor_name,
 admission_date, mobile, transfer_status)
VALUES

('Rahul Patil', 35, 'Male', 'Fever', 'Regular',
 'Stable', 4, 'Dr. Amit Joshi', '2026-09-25', '9876543210', 'PENDING'),

('Priya Shah', 28, 'Female', 'Pneumonia', 'Emergency',
 'Moderate', 9, 'Dr. Neha Sharma', '2026-09-26', '9876543211', 'PENDING'),

('Amit Jadhav', 52, 'Male', 'Heart Attack', 'Regular',
 'Critical', 6, 'Dr. Raj Mehta', '2026-09-26', '9876543212', 'PENDING'),

('Sneha More', 42, 'Female', 'Asthma', 'Emergency',
 'Stable', 8, 'Dr. Pooja Kulkarni', '2026-09-27', '9876543213', 'PENDING'),

('Raj Kumar', 31, 'Male', 'Migraine', 'Regular',
 'Moderate', 5, 'Dr. Sanjay Patil', '2026-09-27', '9876543214', 'PENDING'),

('Neha Patil', 24, 'Female', 'Viral Infection', 'Regular',
 'Stable', 3, 'Dr. Amit Joshi', '2026-09-28', '9876543215', 'PENDING'),

('Vikas Deshmukh', 61, 'Male', 'Chest Pain', 'Emergency',
 'Moderate', 7, 'Dr. Raj Mehta', '2026-09-28', '9876543216', 'PENDING'),

('Pooja Shinde', 37, 'Female', 'Diabetes', 'Regular',
 'Stable', 4, 'Dr. Neha Sharma', '2026-09-29', '9876543217', 'PENDING'),

('Rohit Pawar', 45, 'Male', 'Hypertension', 'Regular',
 'Moderate', 6, 'Dr. Sanjay Patil', '2026-09-29', '9876543218', 'PENDING'),

('Kavita More', 55, 'Female', 'Stroke', 'Emergency',
 'Critical', 10, 'Dr. Raj Mehta', '2026-09-30', '9876543219', 'PENDING'),

('Sagar Kulkarni', 29, 'Male', 'Food Poisoning', 'Regular',
 'Stable', 2, 'Dr. Amit Joshi', '2026-09-30', '9876543220', 'PENDING'),

('Anjali Patil', 33, 'Female', 'Dengue', 'Emergency',
 'Moderate', 8, 'Dr. Pooja Kulkarni', '2026-10-01', '9876543221', 'PENDING'),

('Mahesh Jadhav', 67, 'Male', 'Breathing Problem', 'Emergency',
 'Stable', 9, 'Dr. Neha Sharma', '2026-10-01', '9876543222', 'PENDING'),

('Riya Deshmukh', 22, 'Female', 'Cold and Cough', 'Regular',
 'Stable', 3, 'Dr. Sanjay Patil', '2026-10-02', '9876543223', 'PENDING'),

('Nitin Pawar', 48, 'Male', 'Kidney Stone', 'Regular',
 'Moderate', 5, 'Dr. Amit Joshi', '2026-10-02', '9876543224', 'PENDING'),

('Swati Shinde', 39, 'Female', 'Appendicitis', 'Emergency',
 'Critical', 7, 'Dr. Raj Mehta', '2026-10-03', '9876543225', 'PENDING'),

('Deepak More', 58, 'Male', 'Liver Infection', 'Regular',
 'Moderate', 8, 'Dr. Pooja Kulkarni', '2026-10-03', '9876543226', 'PENDING'),

('Meena Patil', 46, 'Female', 'Anemia', 'Regular',
 'Stable', 4, 'Dr. Neha Sharma', '2026-10-04', '9876543227', 'PENDING'),

('Akash Shah', 26, 'Male', 'Fracture', 'Emergency',
 'Stable', 7, 'Dr. Sanjay Patil', '2026-10-04', '9876543228', 'PENDING'),

('Sunita Jadhav', 63, 'Female', 'Heart Disease', 'Regular',
 'Critical', 5, 'Dr. Raj Mehta', '2026-10-05', '9876543229', 'PENDING');
 
 INSERT INTO hospital_patient_intake
(patient_name, age, gender, disease, admission_type,
 condition_status, triage_score, doctor_name,
 admission_date, mobile, transfer_status)
VALUES

('Swara', 23, 'Female', 'Anemia', 'Regular',
 'Stable', 4, 'Dr. Raj Mehta', '2026-05-25', '9876543210', 'PROCESSED');
 
 INSERT INTO general_care_patients
(
    source_patient_id,
    patient_name,
    age,
    disease,
    admission_type,
    condition_status,
    triage_score,
    doctor_name
)
VALUES
(
    1,
    'Rahul Patil',
    35,
    'Fever',
    'Regular',
    'Stable',
    4,
    'Dr. Amit Joshi'
);
 
 SELECT * FROM hospital_task.hospital_patient_intake;
use hospital_task;
update  hospital_patient_intake
				set processed_at=null ;
                
                INSERT INTO hospital_patient_intake
(patient_name, age, gender, disease, admission_type,
 condition_status, triage_score, doctor_name,
 admission_date, mobile, transfer_status)
VALUES
('Ronak', 30, 'Male', 'Fever', 'Regular',
 'Stable', 5, 'Dr Test', CURDATE(),
 '9876543231', 'PENDING');
--  Using Java:
--   1>Name: blank or shorter than 3 characters
--   2>disease must not be blank.
--  3> doctor_name must not be blank
--  4>admission_date must not be a future date
--  5>already exists in either destination table, the record must not be inserted again.