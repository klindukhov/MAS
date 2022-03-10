package Model;

import Exception.ValidationException;

import java.io.*;
import java.util.*;

public class Recipe implements Serializable {
    private static List<Recipe> extent = new ArrayList<>();

    private long id;
    private String name;
    private Cookbook origin;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Integer> ingredientAmounts = new ArrayList<>();
    private static Set<Ingredient> availableIngredients = new HashSet<>();
    private Set<RecipeStage> stages = new HashSet<>();

    public Recipe(int id, String name, Ingredient i, int amount, String desc) {
        this.setId(id);
        this.setName(name);
        addIngredient(i, amount);
        addStage(new RecipeStage(1, "prep", this, desc));
        extent.add(this);
    }

    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(this.ingredients);
    }

    public void addIngredient(Ingredient ingredient, int amount) {
        if(ingredient == null){
            throw new ValidationException("Ingredient cannot be null");
        }
        this.ingredients.add(ingredient);
        this.ingredientAmounts.add(amount);
    }

    public void removeIngredient(Ingredient ingredient) {
        if(this.ingredients.size() < 2){
            throw new ValidationException("Dish must have at least one ingredient");
        }
        this.ingredientAmounts.remove(ingredientAmounts.get(ingredients.indexOf(ingredient)));
        this.ingredients.remove(ingredient);
    }

    public int getKcal(){
        int t=0;
        int amount = 0;
        for (Ingredient i: ingredients) {
            t+=i.getKcal()*ingredientAmounts.get(ingredients.indexOf(i));
            amount+=ingredientAmounts.get(ingredients.indexOf(i));
        }
        return t/amount*100;
    }

    public int getCost(){
        int t=0;
        int amount = 0;
        for (Ingredient i: ingredients) {
            t+=i.getCost()*ingredientAmounts.get(ingredients.indexOf(i));
            amount+=ingredientAmounts.get(ingredients.indexOf(i));
        }
        return t/amount*100;
    }



    public static Set<Ingredient> getAvailableIngredients() {
        return Collections.unmodifiableSet(availableIngredients);
    }

    public static void addAvailableIngredient(Ingredient ingredient) {
        if(ingredient == null){
            throw new ValidationException("ingredient cannot be null");
        }
        availableIngredients.add(ingredient);
    }

    public static void removeAvailableIngredient(Ingredient ingredient) {
        if(availableIngredients.size() < 2){
            throw new ValidationException("There must be at least one available ingredient");
        }
        availableIngredients.remove(ingredient);
    }

    public Cookbook getOrigin() {
        return origin;
    }

    public void setOrigin(Cookbook origin) {
        if(this.origin == origin){
            return;
        }

        if(this.origin == null){
            this.origin = origin;
            origin.addRecipe(this);
        }else if(origin == null){
            this.origin.removeRecipe(this);
        }else{
            this.origin.removeRecipe(this);
            this.origin = origin;
            origin.addRecipe(this);
        }
        this.origin = origin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new ValidationException("Recipe has to have name");
        }
        this.name = name;
    }


    //Composition association________________________________________

    public Set<RecipeStage> getStages() {
        return Collections.unmodifiableSet(stages);
    }

    public void addStage(RecipeStage s){
        if(s == null){
            throw new ValidationException("Stage cannot be null");
        }
        if(s.getRecipe() != this){
            throw new ValidationException("Stage not related to this recipe");
        }
        this.stages.add(s);
    }

    public void removeStage(RecipeStage s){
        if(s == null){
            throw new ValidationException("Stage cannot be null");
        }
        if(!this.stages.contains(s)){
            return;
        }
        stages.remove(s);
        s.delete();
    }

    public void delete(){
        if(extent.contains(this)){
            extent.remove(this);
        }
        this.origin.removeRecipe(this);
        Set<RecipeStage> tmp = new HashSet<>();
        for(RecipeStage s : stages){
            tmp.add(s);
        }
        for(RecipeStage s : tmp){
            stages.remove(s);
            s.delete();
        }
    }

    private static final String EXTENT_FILE_NAME = "./data/Recipe.ser";

    public static List<Recipe> getExtent() {
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
            extent = (List<Recipe>)ois.readObject();
        }catch(Exception e){
            System.out.println("Recipe.ser seems to be empty");
        }
    }
}
