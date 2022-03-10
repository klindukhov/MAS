package gui.view;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Data
@Component
public class MainWindowView extends JFrame {
    JMenuItem menuItemMenuList;

    public MainWindowView(){
        setTitle("MenuChoiceWindow");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initMenuBar();
    }

    private void initMenuBar(){
        JMenuBar jmb = new JMenuBar();
        JMenu jm = new JMenu("Menu");
        jmb.add(jm);
        menuItemMenuList = new JMenuItem("show menu window");
        jm.add(menuItemMenuList);
        this.setJMenuBar(jmb);
    }
}
