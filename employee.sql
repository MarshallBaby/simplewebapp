CREATE TYPE gender AS ENUM ('MALE', 'FEMALE');
CREATE TABLE employee (
	employee_id BIGSERIAL NOT NULL,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	department_id INT,
	job_title VARCHAR(255),
	gender gender);
	
	
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('Lisa', 'Fregoso', 54, 'Development', CAST('FEMALE' as gender));