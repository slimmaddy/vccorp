package com.mycompany.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private String hire_date;

    public Employee(int id, String first_name, String last_name, String gender, Date hire_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        this.hire_date = sf.format(hire_date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(first_name, employee.first_name) &&
                Objects.equals(last_name, employee.last_name) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(hire_date, employee.hire_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, gender, hire_date);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", hire_date=" + hire_date +
                '}';
    }
}
