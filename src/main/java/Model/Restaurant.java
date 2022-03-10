package Model;

import Exception.ValidationException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Restaurant implements Serializable {
    private String name;
    private Address address;
    private String contactNumber;
    private Set<Employee> emps = new HashSet<>();
    private Set<Serving> menu = new HashSet<>();

    public Restaurant(String name, Address address, String contactNumber, Employee emp, Dish d) {
        setName(name);
        setAddress(address);
        setContactNumber(contactNumber);
        addEmployee(emp);
        addDish(new Serving(d, this, LocalDate.now(), null));

        extent.add(this);
    }

    public void addEmployee(Employee e){
        if(e == null){
            throw new ValidationException("Employee cannot be null");
        }
        emps.add(e);
    }



    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Set<Serving> getDishes() {
        return Collections.unmodifiableSet(menu);
    }

    public Set<Employee> getEmps() {
        return Collections.unmodifiableSet(emps);
    }

    public void addDish(Serving s){
        if(s == null){
            throw new ValidationException("Dish cannot be null");
        }
        if(s.getRestaurant() != this){
            throw new ValidationException("Dish is not related to this restaurant");
        }
        this.menu.add(s);
    }

    public void removeDish(Serving s){
        if(s == null){
            throw new ValidationException("Dish cannot be null");
        }
        if(!this.menu.contains(s)){
            return;
        }
        this.menu.remove(s);
        s.remove();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new ValidationException("Restaurant has to have name");
        }
        this.name = name;
    }

    private static final String EXTENT_FILE_NAME = "./data/Restaurant.ser";
    private static List<Restaurant> extent = new ArrayList<>();

    public static List<Restaurant> getExtent() {
        return Collections.unmodifiableList(extent);
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
            extent = (List<Restaurant>)ois.readObject();
        }catch(Exception e){
            System.out.println("Restaurant.ser seems to be empty");
        }
    }
}
