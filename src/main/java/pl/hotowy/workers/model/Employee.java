package pl.hotowy.workers.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private double salary;
    private LocalDate employedFrom;

    public Employee(String firstName, String lastName, String pesel, double salary, LocalDate employedFrom) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.salary = salary;
        this.employedFrom = employedFrom;
    }

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getEmployedFrom() {
        return employedFrom;
    }

    public void setEmployedFrom(LocalDate employedFrom) {
        this.employedFrom = employedFrom;
    }
}

