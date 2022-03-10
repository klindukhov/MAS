package Model;

import Exception.ValidationException;

import java.io.*;
import java.util.*;

public class Dish implements Serializable {
    private static final String EXTENT_FILE_NAME = "./data/Dish.ser";
    private static List<Dish> extent = new ArrayList<>();

    private String name;
    private int portionSize;
    private Recipe recipe;
    private Set<Serving> servings = new HashSet<>();

    public Dish(String name, int portionSize, Recipe recipe) {
        this.setName(name);
        setPortionSize(portionSize);
        setRecipe(recipe);

        extent.add(this);
    }

    public Dish(String name){
        setName(name);
        extent.add(this);
    }

    public int getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(int portionSize) {
        this.portionSize = portionSize;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new ValidationException("Dish has to have name");
        }
        this.name = name;
    }

    public int getDishKcal() {
        return recipe.getKcal()/100*portionSize;
    }

    public static List<Dish> getExtent() {
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
            extent = (List<Dish>)ois.readObject();
        }catch(Exception e){
            System.out.println("Dish.ser seems to be empty");
        }
    }

    //A derived attribute
    public double getDishCost(){
        return recipe.getCost()/100*portionSize;
    }

    //Class method
    public static List<Dish> findByIngredient(String ingredientName){
        List<Dish> dishes= new ArrayList<>();
        for (Dish dish: extent) {
            for(Ingredient i: dish.getRecipe().getIngredients()){
                if(i.getName().equals(ingredientName)){
                    dishes.add(dish);
                    break;
                }
            }
        }
        return dishes;
    }

    public Set<Serving> getServings() {
        return Collections.unmodifiableSet(servings);
    }

    public void addServing(Serving s){
        if(s == null){
            throw new ValidationException("Serving cannot be null");
        }
        if(s.getDish() != this){
            throw new ValidationException("Restaurant is not related to this dish");
        }
        this.servings.add(s);
    }

    public void removeServing(Serving s){
        if(s == null){
            throw new ValidationException("Serving cannot be null");
        }
        if(!this.servings.contains(s)){
            return;
        }
        this.servings.remove(s);
        s.remove();
    }

    @Override
    public String toString() {
        return name +" Kcal: " + getDishKcal();
    }
}

