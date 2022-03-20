CREATE TYPE gender AS ENUM ('MALE', 'FEMALE');
CREATE TABLE employee (
	employee_id BIGSERIAL NOT NULL,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	department_id INT,
	job_title VARCHAR(255),
	gender gender,
    age INT NOT NULL
);
	
	
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('Lisa', 'Fregoso', 54, 'Development', CAST('FEMALE' as gender), 32);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('David', 'Hatcher', 54, 'Development', CAST('MALE' as gender), 19);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('John', 'Shipley', 54, 'Development', CAST('MALE' as gender), 43);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('Leslie', 'Hurtado', 27, 'HR', CAST('FEMALE' as gender), 54);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('Almeda', 'Meadows', 27, 'HR', CAST('FEMALE' as gender), 27);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('Shirley', 'Cox', 27, 'HR', CAST('FEMALE' as gender), 45);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('Keith', 'Lane', 14, 'Testing', CAST('MALE' as gender), 24);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('Joann', 'Kline', 14, 'Testing', CAST('FEMALE' as gender), 21);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('Ron', 'Sherwood', 14, 'Testing', CAST('MALE' as gender), 37);
INSERT INTO employee (first_name, last_name, department_id, job_title, gender, age)
	VALUES ('Danny', 'Macek', 14, 'Testing', CAST('MALE' as gender), 29);
