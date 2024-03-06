package com.example.employeeproject;

public class Employee {
    private int emp_id;
    private String emp_name;
    private double emp_salary;

    public double Fed_Tax = 0.07;
    public double Prv_Tax = 0.09;

    public Employee(int emp_id, String emp_name, double emp_salary) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_salary = emp_salary;
    }

    public double calculateTotalTax()
    {
        return (getEmp_salary()*Fed_Tax)+(getEmp_salary()*Prv_Tax);
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public double getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(double emp_salary) {
        this.emp_salary = emp_salary;
    }
}
