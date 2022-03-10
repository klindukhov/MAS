package Model;

import Exception.ValidationException;

import java.io.*;
import java.util.*;

public class Cookbook implements Serializable {
    private String title;
    private Map<Long, Recipe> recipeMap = new HashMap<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || "".equals(title.trim())){
            throw new ValidationException("Recipe has to have name");
        }
        this.title = title;
    }

    public Cookbook(String title) {
        this.setTitle(title);
    }

    public Map<Long, Recipe> getRecipeMap() {
        return Collections.unmodifiableMap(recipeMap);
    }

    public List<Recipe> getRecipeList(){
        return new ArrayList<>(recipeMap.values());
    }

    public Recipe findRecipeById(long id){
        return recipeMap.get(id);
    }

    public void addRecipe(Recipe r){
        if(r == null){
            throw new ValidationException("Recipe cannot be null");
        }
        if(this.recipeMap.containsKey(r.getId())){
            return;
        }
        this.recipeMap.put(r.getId(), r);
        r.setOrigin(this);
    }

    public void removeRecipe(Recipe r){
        if(r == null){
            throw new ValidationException("Recipe cannot be null");
        }
        if(!this.recipeMap.containsKey(r.getId())){
            return;
        }
        this.recipeMap.remove(r.getId(), r);
        r.setOrigin(null);
    }

    private static final String EXTENT_FILE_NAME = "./data/Cookbook.ser";
    private static List<Cookbook> extent = new ArrayList<>();

    public static List<Cookbook> getExtent() {
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
            extent = (List<Cookbook>)ois.readObject();
        }catch(Exception e){
            System.out.println("Dish.ser seems to be empty");
        }
    }
}
