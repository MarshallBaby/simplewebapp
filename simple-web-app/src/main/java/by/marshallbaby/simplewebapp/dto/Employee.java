package by.marshallbaby.simplewebapp.dto;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
// TODO: уточнить про Enum Cast
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@DynamicUpdate
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Integer departmentId;
    private String jobTitle;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    @Type(type = "pgsql_enum")
    private Gender gender;
    @Min(value = 18, message = "Age must be 18+")
    private Integer age;

    public Employee(){}

    public Employee(Long employeeId,
                    String firstName,
                    String lastName,
                    Integer departmentId,
                    String jobTitle,
                    Gender gender,
                    Integer age){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.age = age;
    }

    public Employee(
                    String firstName,
                    String lastName,
                    Integer departmentId,
                    String jobTitle,
                    Gender gender,
                    Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.age = age;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
