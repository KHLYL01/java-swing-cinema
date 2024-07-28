package main.ui;

import main.ui.movies.MoviePage;
import main.ui.user.UserDetailsPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    JButton[] buttons = new JButton[3];
    Color[] colors = {Color.CYAN,Color.ORANGE,Color.RED};
    String[] titles = {"View Movies","User Details","Exit"};
    Font newFont = new Font("Arial", Font.BOLD, 24);

    public HomePage() {

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setText(titles[i]);
            buttons[i].setFont(newFont);
            buttons[i].setForeground(colors[i]);
            buttons[i].setBackground(Color.darkGray);
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (finalI){
                        case 0 ->new MoviePage();
                        case 1 ->new UserDetailsPage();
                        case 2 ->System.exit(0);
                    }
                }
            });
            add(buttons[i]);
        }


        setTitle("Home Page");
        setSize(400,180);
        setLayout(new GridLayout(3,1));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
