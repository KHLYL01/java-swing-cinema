package main.ui.user;

import main.model.User;
import main.io.IOFunction;
import main.ui.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterUserPage extends JFrame {

    private JLabel name = new JLabel("Username: ");
    private JLabel password = new JLabel("password: ");
    private JLabel description = new JLabel("Description: ");
    private JTextField tName = new JTextField();
    private JPasswordField tPassword = new JPasswordField();
    private JTextArea tDescription = new JTextArea(3, 100);
    private JButton register = new JButton("Register");
    private Font newFont = new Font("Arial", Font.BOLD, 16);

    public RegisterUserPage() {

        register.setFont(newFont);
        register.setBackground(Color.darkGray);
        register.setForeground(Color.cyan);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                IOFunction.username = tName.getText();

                if (IOFunction.userIsFound()) {
                    JOptionPane.showMessageDialog(null, "User already found enter other username!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User user = new User(tName.getText(), tPassword.getPassword().toString(), tDescription.getText());

                System.out.println(user);

                IOFunction.saveUser(user);

                new HomePage();
                dispose();
//
            }
        });

        name.setForeground(Color.CYAN);
        password.setForeground(Color.CYAN);
        description.setForeground(Color.CYAN);
        tName.setForeground(Color.white);
        tPassword.setForeground(Color.white);
        tDescription.setForeground(Color.white);

        tName.setBorder(null);
        tPassword.setBorder(null);

        tName.setCaretColor(Color.white);
        tPassword.setCaretColor(Color.white);
        tDescription.setCaretColor(Color.white);

        tName.setBackground(Color.darkGray);
        tPassword.setBackground(Color.darkGray);
        tDescription.setBackground(Color.darkGray);

        name.setBounds(10, 20, 100, 20);
        tName.setBounds(85, 22, 200, 20);
        password.setBounds(10, 50, 100, 20);
        tPassword.setBounds(85, 52, 200, 20);
        description.setBounds(10, 80, 100, 20);
        tDescription.setBounds(85, 82, 200, 50);
        register.setBounds(100, 160, 100, 30);

        add(name);
        add(tName);
        add(password);
        add(tPassword);
        add(description);
        add(tDescription);
        add(register);

        setTitle("Register Page");
        setSize(320, 240);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
