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
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('David', 'Hatcher', 54, 'Development', CAST('MALE' as gender));
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('John', 'Shipley', 54, 'Development', CAST('MALE' as gender));
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('Leslie', 'Hurtado', 27, 'HR', CAST('FEMALE' as gender));
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('Almeda', 'Meadows', 27, 'HR', CAST('FEMALE' as gender));
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('Shirley', 'Cox', 27, 'HR', CAST('FEMALE' as gender));
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('Keith', 'Lane', 14, 'Testing', CAST('MALE' as gender));
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('Joann', 'Kline', 14, 'Testing', CAST('FEMALE' as gender));
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('Ron', 'Sherwood', 14, 'Testing', CAST('MALE' as gender));
INSERT INTO employee (first_name, last_name, department_id, job_title, gender)
	VALUES ('Danny', 'Macek', 14, 'Testing', CAST('MALE' as gender));