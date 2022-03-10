package Model;

import Exception.ValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ingredient implements Serializable {
    private String name;
    private double cost;
    private int kcal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new ValidationException("Ingredient has to have name");
        }
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Ingredient(String name, double cost, int kcal) {
        setName(name);
        setCost(cost);
        setKcal(kcal);
    }

    @Override
    public String toString() {
        return name;
    }

    private static final String EXTENT_FILE_NAME = "./data/Ingredient.ser";
    private static List<Ingredient> extent = new ArrayList<>();

    public static List<Ingredient> getExtent() {
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
            extent = (List<Ingredient>)ois.readObject();
        }catch(Exception e){
            System.out.println("Ingredient.ser seems to be empty");
        }
    }
}
