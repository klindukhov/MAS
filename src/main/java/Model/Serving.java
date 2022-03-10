package Model;

import Exception.ValidationException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Serving  implements Serializable {
    private static List<Serving> extent = new ArrayList<>();

    private Dish dish;
    private Restaurant restaurant;

    private LocalDate startDate, endDate;

    public Serving(Dish dish, Restaurant restaurant, LocalDate startDate, LocalDate endDate) {
        this.setStartDate(startDate);
        this.endDate = endDate;
        if(!isPairUnique(dish, restaurant)){
            throw  new ValidationException("This association already exists");
        }
        setDish(dish);
        setRestaurant(restaurant);
        extent.add(this);
    }

    public Dish getDish() {
        return dish;
    }

    private void setDish(Dish dish) {
        if(dish == null){
            throw new ValidationException("Dish cannot be null");
        }
        this.dish = dish;
        dish.addServing(this);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    private void setRestaurant(Restaurant restaurant) {
        if(restaurant == null){
            throw new ValidationException("Restaurant cannot be null");
        }
        this.restaurant = restaurant;
        restaurant.addDish(this);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate == null){
            throw new ValidationException("startDate cannot be null");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void remove(){
        if(this.dish != null){
            Dish temp = this.dish;
            this.dish = null;
            temp.removeServing(this);
        }

        if(this.restaurant != null){
            Restaurant temp = this.restaurant;
            this.restaurant = null;
            temp.removeDish(this);
        }
        extent.remove(this);
    }

    public static boolean isPairUnique(Dish d, Restaurant r){
        return extent.stream()
                .filter(serving -> serving.dish == d && serving.restaurant == r)
                .count() == 0;
    }

    private static final String EXTENT_FILE_NAME = "./data/Serving.ser";

    public static List<Serving> getExtent() {
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
            extent = (List<Serving>)ois.readObject();
        }catch(Exception e){
            System.out.println("Serving.ser seems to be empty");
        }
    }
}
