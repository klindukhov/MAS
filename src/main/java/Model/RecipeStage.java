package Model;

import Exception.ValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeStage implements Serializable {
    private static List<RecipeStage> extent = new ArrayList<>();

    private int number;
    private String name;
    private Recipe recipe;
    private String description;

    public RecipeStage(int number, String name, Recipe recipe, String description) {
        setNumber(number);
        setName(name);
        setRecipe(recipe);
        setDescription(description);
        extent.add(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null || "".equals(description.trim())){
            throw new ValidationException("Description cant be null");
        }
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new ValidationException("Recipe stage has to have name");
        }
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    private void setRecipe(Recipe recipe) {
        if(recipe == null){
            throw new ValidationException("Recipe cannot be null");
        }
        this.recipe = recipe;
        recipe.addStage(this);
    }


    public void delete(){
        extent.remove(this);
        this.recipe.removeStage(this);
    }

    @Override
    public String toString() {
        return "RecipeStage{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", recipe=" + recipe +
                '}';
    }

    private static final String EXTENT_FILE_NAME = "./data/RecipeStage.ser";

    public static List<RecipeStage> getExtent() {
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
            extent = (List<RecipeStage>)ois.readObject();
        }catch(Exception e){
            System.out.println("RecipeStage.ser seems to be empty");
        }
    }
}
