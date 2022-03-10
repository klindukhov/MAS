package gui.controllers;

import gui.view.MainWindowView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Controller
@RequiredArgsConstructor
public class MainWindowController {
    private final MainWindowView view;
    private final RestaurantListController restaurantListController;

    public void showGUI(){
        view.setVisible(true);
    }

    @PostConstruct
    private void initMenuListener(){
        view.getMenuItemMenuList().addActionListener(e -> {
            restaurantListController.showGUI(this);
        });
    }

    public void showView(JPanel viewToShow){
        view.getContentPane().removeAll();
        view.getContentPane().add(viewToShow);
        view.revalidate();
    }
}
