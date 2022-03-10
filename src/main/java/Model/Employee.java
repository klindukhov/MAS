package Model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Exception.ValidationException;

public abstract class Employee implements Serializable {
    private String empID;
    private String name;
    private String surname;
    private double wage;
    private int workHours;
    private String phoneNumber;
    private LocalDate birthdate;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Employee(String empID, String name, String surname, String phoneNumber, LocalDate birthdate, LocalDate startDate, double wage, int workHours) {
        setEmpID(empID);
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
        setBirthdate(birthdate);
        setStartDate(startDate);
        setWage(wage);
        setWorkHours(workHours);

    }

    public abstract double getPay();

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        if(empID == null || "".equals(empID.trim())){
            throw new ValidationException("ID cannot be null");
        }
        this.empID = empID;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null || "".equals(phoneNumber.trim())){
            throw new ValidationException("Phone number cannot be null");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new ValidationException("Name cannot be null");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null || "".equals(surname.trim())){
            throw new ValidationException("Surname cannot be null");
        }
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        if(birthdate == null){
            throw new ValidationException("Birthdate cannot be null");
        }
        this.birthdate = birthdate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate == null){
            throw new ValidationException("StartDate cannot be null");
        }
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        if(finishDate == null){
            throw new ValidationException("FinishDate cannot be null");
        }
        this.finishDate = finishDate;
    }

    private static final String EXTENT_FILE_NAME = "./data/Employee.ser";
    private static List<Employee> extent = new ArrayList<>();

    public static List<Employee> getExtent() {
        return Collections.unmodifiableList(extent);
    }
    public static List<Employee> getModifiableExtent() {
        return extent;
    }

    public static void saveExtent(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXTENT_FILE_NAME))){
            oos.writeObject(extent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadExtent(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXTENT_FILE_NAME))){
            extent = (List<Employee>)ois.readObject();
        }catch(Exception e){
            System.out.println("Employee.ser seems to be empty");
        }
    }
}
