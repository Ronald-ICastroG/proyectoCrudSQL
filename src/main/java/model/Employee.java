package model;

public class Employee {

    private Integer empId;
    private String empFirstName;

    private String empSurName;

    private String empMSurName;

    private String empEmail;
    private Float empSalary;


    public Employee(){

    }

    public Employee(Integer empId, String empFirstName, String empSurName, String empMSurName, String empEmail, Float empSalary) {
        this.empId = empId;
        this.empFirstName = empFirstName;
        this.empSurName = empSurName;
        this.empMSurName = empMSurName;
        this.empEmail = empEmail;
        this.empSalary = empSalary;
    }


    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpSurName() {
        return empSurName;
    }

    public void setEmpSurName(String empSurName) {
        this.empSurName = empSurName;
    }

    public String getEmpMSurName() {
        return empMSurName;
    }

    public void setEmpMSurName(String empMSurName) {
        this.empMSurName = empMSurName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Float getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Float empSalary) {
        this.empSalary = empSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empFirstName='" + empFirstName + '\'' +
                ", empSurName='" + empSurName + '\'' +
                ", empMSurName='" + empMSurName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", empSalary=" + empSalary +
                '}';
    }
}
