package main.ui.ticketing;

import main.io.IOFunction;
import main.model.ShowTime;
import main.model.Ticket;
import main.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CancelBookedPage extends JFrame {

    private ShowTime showTime;
    private java.util.List<Integer> chairs = new ArrayList<>();
    private java.util.List<Ticket> tickets;
    private Font panelFont = new Font("Arial", Font.BOLD, 10);
    private JButton ok = new JButton("OK");
    private JLabel clickLabel = new JLabel("click button (you can click more than 1 & click again to cancel):");
    private JLabel label = new JLabel("Tickets: ");
    private JLabel ticketLabel = new JLabel("[ ]");
    private JLabel balance = new JLabel();
    private JLabel returnedLabel = new JLabel("Returned: ");
    private JLabel returned = new JLabel("0");
    private JPanel chairPanel = new JPanel(new GridLayout(8, 15));
    private JButton[] chairButtons = new JButton[104];

    public CancelBookedPage(int showTimeId, List<ShowTime> showTimes) {

        for (ShowTime showTime : showTimes) {
            if (showTime.getId() == showTimeId) {
                this.showTime = showTime;
                break;
            }
        }

        User user = IOFunction.getUser();
        tickets = showTime.getTickets();

        balance.setText("your Balance: " + user.getBalance());


        for (int i = 0; i < chairButtons.length; i++) {
            if (i < 9) {
                chairButtons[i] = new JButton("0" + tickets.get(i).getChairNumber());
            } else {
                chairButtons[i] = new JButton("" + tickets.get(i).getChairNumber());
            }

            chairButtons[i].setBackground(Color.darkGray);
            chairButtons[i].setForeground(Color.RED);
            chairButtons[i].setVisible(false);
            chairButtons[i].setFont(panelFont);
            int finalI = i;

            for (Ticket userTicket : user.getTickets()) {
                if (userTicket.getChairNumber() == tickets.get(i).getChairNumber() && userTicket.getDate().equals(showTime.getDate())){
                    chairButtons[i].setVisible(true);
                    break;
                }
            }


            chairButtons[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (chairs.contains(tickets.get(finalI).getChairNumber())) {
                        chairs.remove((Object) tickets.get(finalI).getChairNumber());
                        chairButtons[finalI].setForeground(Color.RED);
                        Ticket.setSum(Ticket.getSum() - tickets.get(0).getPrice());
                        returned.setText(Ticket.getSum() + "");
                    } else {
                        chairs.add(tickets.get(finalI).getChairNumber());
                        chairButtons[finalI].setForeground(Color.ORANGE);
                        Ticket.setSum(Ticket.getSum() + tickets.get(0).getPrice());
                        returned.setText(Ticket.getSum() + "");
                    }

                    ticketLabel.setText(chairs.toString());
                }
            });

            chairPanel.add(chairButtons[i]);
        }

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (chairs.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "click on any button, try again!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int confirmDialog = JOptionPane.showConfirmDialog(null, "Do you really want to cancel booked this chair :\n" + chairs, "Confirm cancel Booked", JOptionPane.YES_NO_OPTION);
                if (confirmDialog != 0) {
                    return;
                }
                user.setBalance(user.getBalance() + Ticket.getSum());
                JOptionPane.showMessageDialog(null, "Returned completed successfully!!!\nYour Balance: " + user.getBalance(), "Returned", JOptionPane.INFORMATION_MESSAGE);
                for (Ticket ticket : showTime.getTickets()) {
                    if (chairs.contains(ticket.getChairNumber()) || chairs.contains(ticket.getChairNumber())) {
                        user.removeTicket(ticket);
                        ticket.setAvailable(true);
                    }
                }
                IOFunction.saveDate(showTimes);
                JOptionPane.showMessageDialog(null, "cancel booked successfully!!!");
                IOFunction.saveUser(user);
                dispose();
            }
        });

        // set color
        chairPanel.setBackground(Color.black);
        ok.setBackground(Color.DARK_GRAY);
        ok.setForeground(Color.cyan);
        clickLabel.setForeground(Color.WHITE);
        returnedLabel.setForeground(Color.WHITE);
        returned.setForeground(Color.GREEN);
        label.setForeground(Color.CYAN);
        balance.setForeground(Color.ORANGE);
        ticketLabel.setForeground(Color.WHITE);

        // set font
        ok.setFont(panelFont);
        label.setFont(panelFont);
        ticketLabel.setFont(panelFont);

        // set bounds
        clickLabel.setBounds(10, 10, 400, 20);
        balance.setBounds(520, 10, 200, 20);
        returnedLabel.setBounds(670, 10, 100, 20);
        returned.setBounds(735, 10, 100, 20);
        chairPanel.setBounds(10, 40, 770, 260);
        ok.setBounds(695, 320, 80, 30);
        label.setBounds(10, 320, 80, 30);
        ticketLabel.setBounds(60, 320, 700, 30);

        // add component to JFrame
        add(chairPanel);
        add(ok);
        add(clickLabel);
        add(label);
        add(balance);
        add(returned);
        add(returnedLabel);
        add(ticketLabel);


        // initial JFrame
        setTitle("Cancel Booked Page");
        setSize(800, 400);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
