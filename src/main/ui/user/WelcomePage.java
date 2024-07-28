package main.ui.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomePage extends JFrame {

    private JLabel welcome = new JLabel("Welcome To Cinema");
    private JButton register = new JButton("Register");
    private JButton login = new JButton("login");
    private JPanel panel = new JPanel(new GridLayout(1,2,10,10));
    private Font newFont = new Font("Arial", Font.BOLD, 16);

    public WelcomePage(){

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginUserPage();
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterUserPage();
            }
        });

        login.setFont(newFont);
        login.setBackground(Color.darkGray);
        login.setForeground(Color.ORANGE);

        register.setFont(newFont);
        register.setBackground(Color.darkGray);
        register.setForeground(Color.cyan);

        welcome.setFont(newFont);
        welcome.setForeground(Color.WHITE);

        panel.setBackground(Color.BLACK);

        welcome.setBounds(80,20,300,20);
        panel.setBounds(10,60,285,40);


        panel.add(login);
        panel.add(register);

        add(welcome);
        add(panel);

        setTitle("Welcome Page");
        setSize(320,160);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
