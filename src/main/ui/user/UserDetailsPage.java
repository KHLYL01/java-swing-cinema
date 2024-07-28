package main.ui.user;

import main.io.IOFunction;
import main.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDetailsPage extends JFrame {

    private JLabel name = new JLabel("Username: ");
    private JLabel description = new JLabel("Description: ");
    private JLabel balance = new JLabel("Balance: ");
    private JLabel rbalance = new JLabel();
    private JButton rechargeBalance = new JButton("Recharge balance");

    private Font newFont = new Font("Arial", Font.BOLD, 16);

    private User user;

    public UserDetailsPage() {

        this.user = IOFunction.getUser();

        name.setText(name.getText()+user.getName());
        description.setText(description.getText()+user.getDetails());
        rbalance.setText(user.getBalance()+"");

        rechargeBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("recharge the balance:");
                try {
                    int recharged = Integer.parseInt(s);
                    user.setBalance(user.getBalance()+ recharged);
                    IOFunction.saveUser(user);
                    rbalance.setText(user.getBalance()+"");
                    JOptionPane.showMessageDialog(null,recharged +" added successfully","information",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"invalid input, try again!!!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        rechargeBalance.setFont(newFont);
        name.setFont(newFont);
        description.setFont(newFont);
        balance.setFont(newFont);
        rbalance.setFont(newFont);

        rechargeBalance.setBackground(Color.DARK_GRAY);
        rechargeBalance.setForeground(Color.CYAN);

        name.setForeground(Color.WHITE);
        description.setForeground(Color.WHITE);
        balance.setForeground(Color.WHITE);
        rbalance.setForeground(Color.CYAN);


        name.setBounds(10, 10, 200, 20);
        description.setBounds(10, 40, 300, 20);
        balance.setBounds(10, 70, 100, 20);
        rbalance.setBounds(80, 70, 300, 20);
        rechargeBalance.setBounds(100, 120, 200, 30);

        add(name);
        add(description);
        add(balance);
        add(rbalance);
        add(rechargeBalance);

        setTitle("User Page");
        setSize(320, 200);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}

