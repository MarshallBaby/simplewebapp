package by.marshallbaby.simplewebapp.dto;

import org.springframework.data.annotation.Id;

public class Employee {
    @Id
    private Long employeeId;
    private String firstName;
    private Gender gender;

    public Employee(){};

    public Employee(Long employeeId, String firstName, Gender gender){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.gender = gender;
    }

    public Employee(String firstName, Gender gender){
        this.firstName = firstName;
        this.gender = gender;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + " firstName=" + firstName +
                " gender=" + gender;
    }
}
