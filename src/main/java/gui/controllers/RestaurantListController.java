package gui.controllers;

import Model.Dish;
import Model.Restaurant;
import Model.Serving;
import gui.view.RestaurantListView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class RestaurantListController {
    private final RestaurantListView view;

    public void showGUI(MainWindowController mainWindowController){

        updateRestaurantList();
        mainWindowController.showView(view.getMainPanel());
    }

    @PostConstruct
    private void initListeners(){
        view.getRestaurantList().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                Restaurant selectedValue = (Restaurant) view.getRestaurantList().getSelectedValue();
                view.getSelectedRestaurantValue().setText(selectedValue.getName());
            }
        });
    }

    @PostConstruct
    private void submitListener(){
        view.getSubmitButton().addActionListener(e->{
            getDishList();
        });
    }

    private void updateRestaurantList(){
        List<Restaurant> restaurants = Restaurant.getExtent();
        DefaultListModel<Restaurant> model = (DefaultListModel<Restaurant>)view.getRestaurantList().getModel();
        model.removeAllElements();
        restaurants.forEach(model :: addElement);
    }

    private void getDishList(){
        Restaurant selectedValue = (Restaurant) view.getRestaurantList().getSelectedValue();
        Set<Serving> servings = selectedValue.getDishes();
        List<Dish> dishes = new ArrayList<>();
        for (Serving s:servings) {
            dishes.add(s.getDish());
        }
        DefaultListModel<Dish> modelD = (DefaultListModel<Dish>) view.getDishList().getModel();
        modelD.removeAllElements();
        dishes.forEach(modelD :: addElement);
    }
}
