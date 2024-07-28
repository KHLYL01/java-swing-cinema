package main.ui.user;

import main.io.IOFunction;
import main.ui.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginUserPage extends JFrame {

    private JLabel name = new JLabel("Username: ");
    private JTextField tName = new JTextField();
    private JButton login = new JButton("Login");
    private Font newFont = new Font("Arial", Font.BOLD, 16);

    public LoginUserPage(){

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                IOFunction.username = tName.getText();

                if(!IOFunction.userIsFound()) {
                    JOptionPane.showMessageDialog(null, "User not found please register first, try again!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                new HomePage();
                dispose();
//
            }
        });

        login.setFont(newFont);
        login.setBackground(Color.darkGray);
        login.setForeground(Color.cyan);

        name.setForeground(Color.CYAN);
        tName.setForeground(Color.white);

        tName.setBorder(null);

        tName.setCaretColor(Color.white);

        tName.setBackground(Color.darkGray);

        name.setBounds(10,20,100,20);
        tName.setBounds(85,22,200,20);
        login.setBounds(100,60,100,30);

        add(name);
        add(tName);
        add(login);

        setTitle("Login Page");
        setSize(320,150);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
