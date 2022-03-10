package gui.view;

import Model.Dish;
import Model.Restaurant;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@Data
public class RestaurantListView {
    private JPanel mainPanel;
    private JList restaurantList;
    private JLabel selectedRestaurantValue;
    private JList dishList;
    private JButton submitButton;

    private void createUIComponents() {
        restaurantList = new JList<Restaurant>();
        restaurantList.setCellRenderer(new RestaurantListCellRenderer());
        restaurantList.setModel(new DefaultListModel<Restaurant>());
        dishList = new JList<Dish>();
        dishList.setModel(new DefaultListModel<Dish>());
    }

    private class RestaurantListCellRenderer extends JLabel implements ListCellRenderer<Restaurant>{

        public RestaurantListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends Restaurant> list, Restaurant value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getName());
            if(isSelected){
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }else{
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }

    private class DishCellRenderer extends JLabel implements ListCellRenderer<Dish>{

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends Dish> list, Dish value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getName());
            if(isSelected){
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }else{
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }
}