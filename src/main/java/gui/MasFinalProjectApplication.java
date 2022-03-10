package gui;

import Model.*;
import gui.controllers.MainWindowController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.time.LocalDate;

@SpringBootApplication
public class MasFinalProjectApplication {

    public static void main(String[] args) {
        loadExtent();

//        Restaurant r1 = new Restaurant("r1", new Address("warsaw", "r", "2", "42332"), "23456789", new Cook("213", "n1", "s1", "32", LocalDate.now(), LocalDate.now(), 2.3, 12, CookPosition.CHEF), new Dish("n1", 150, new Recipe(12345, "r1", new Ingredient("tomato", 30, 22), 50, "desc")));
//        r1.addDish(new Serving(new Dish("n12", 50, new Recipe(12345, "r1", new Ingredient("tomato", 30, 22), 50, "desc")), r1, LocalDate.now(), LocalDate.now()));
//        r1.addDish(new Serving(new Dish("n13", 250, new Recipe(12345, "r1", new Ingredient("tomato", 30, 22), 50, "desc")), r1, LocalDate.now(), LocalDate.now()));
//        r1.addDish(new Serving(new Dish("n14", 350, new Recipe(12345, "r1", new Ingredient("tomato", 30, 22), 50, "desc")), r1, LocalDate.now(), LocalDate.now()));
//        Restaurant r2 = new Restaurant("r2", new Address("warsaw", "r", "2", "42332"), "23456789", new Cook("213", "n1", "s1", "32", LocalDate.now(), LocalDate.now(), 2.3, 12, CookPosition.CHEF), new Dish("n2", 200, new Recipe(1243345, "r2", new Ingredient("parsley", 55, 12), 40, "desc")));
//        r2.addDish(new Serving(new Dish("n22", 50, new Recipe(12345, "r2", new Ingredient("tomato", 30, 22), 50, "desc")), r2, LocalDate.now(), LocalDate.now()));
//        r2.addDish(new Serving(new Dish("n23", 250, new Recipe(12345, "r2", new Ingredient("tomato", 30, 22), 50, "desc")), r2, LocalDate.now(), LocalDate.now()));
//        r2.addDish(new Serving(new Dish("n24", 350, new Recipe(12345, "r2", new Ingredient("tomato", 30, 22), 50, "desc")), r2, LocalDate.now(), LocalDate.now()));
//        Restaurant r3 = new Restaurant("r3", new Address("warsaw", "r", "2", "42332"), "23456789", new Cook("213", "n1", "s1", "32", LocalDate.now(), LocalDate.now(), 2.3, 12, CookPosition.CHEF), new Dish("n3", 250, new Recipe(1243345, "r3", new Ingredient("pepper", 34, 23), 60, "desc")));
//        r3.addDish(new Serving(new Dish("n32", 50, new Recipe(12345, "r3", new Ingredient("tomato", 30, 22), 50, "desc")), r3, LocalDate.now(), LocalDate.now()));
//        r3.addDish(new Serving(new Dish("n33", 250, new Recipe(12345, "r3", new Ingredient("tomato", 30, 22), 50, "desc")), r3, LocalDate.now(), LocalDate.now()));
//        r3.addDish(new Serving(new Dish("n34", 350, new Recipe(12345, "r3", new Ingredient("tomato", 30, 22), 50, "desc")), r3, LocalDate.now(), LocalDate.now()));

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MasFinalProjectApplication.class).headless(false).run(args);

        SwingUtilities.invokeLater( () -> {
            ctx.getBean(MainWindowController.class).showGUI();
        });
        saveExtent();
    }

    private static void loadExtent(){
        Ingredient.loadExtent();
        Restaurant.loadExtent();
        Cookbook.loadExtent();
        Recipe.loadExtent();
        Dish.loadExtent();
        RecipeStage.loadExtent();
        Serving.loadExtent();
        Employee.loadExtent();
    }

    private static void saveExtent(){
        Ingredient.saveExtent();
        Restaurant.saveExtent();
        Cookbook.saveExtent();
        Recipe.saveExtent();
        Dish.saveExtent();
        RecipeStage.saveExtent();
        Serving.saveExtent();
        Employee.saveExtent();
    }

}
