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


public class BookedTicketPage extends JFrame {

    private ShowTime showTime;
    private List<Integer> chairs = new ArrayList<>();
    private List<Ticket> tickets;
    private Font panelFont = new Font("Arial", Font.BOLD, 10);
    private JButton ok = new JButton("OK");
    private JLabel clickLabel = new JLabel("click button (you can click more than 1 & click again to cancel):");
    private JLabel label = new JLabel("Tickets: ");
    private JLabel ticketLabel = new JLabel("[ ]");
    private JLabel balance = new JLabel();
    private JLabel costLabel = new JLabel("Cost: ");
    private JLabel cost = new JLabel("0");
    private JPanel chairPanel = new JPanel(new GridLayout(8, 15));
    private JButton[] chairButtons = new JButton[104];

    public BookedTicketPage(int showTimeId, List<ShowTime> showTimes) {

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
            chairButtons[i].setForeground(Color.cyan);
            chairButtons[i].setEnabled(tickets.get(i).isAvailable());
            chairButtons[i].setFont(panelFont);
            int finalI = i;


            chairButtons[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (chairs.contains(tickets.get(finalI).getChairNumber())) {
                        chairs.remove((Object)tickets.get(finalI).getChairNumber());
                        chairButtons[finalI].setForeground(Color.CYAN);
                        Ticket.setSum(Ticket.getSum() - tickets.get(0).getPrice());
                        cost.setText(Ticket.getSum() + "");
                    } else {
                        chairs.add(tickets.get(finalI).getChairNumber());
                        chairButtons[finalI].setForeground(Color.ORANGE);
                        Ticket.setSum(Ticket.getSum() + tickets.get(0).getPrice());
                        cost.setText(Ticket.getSum() + "");
                    }

                    if (user.getBalance() < Ticket.getSum()) {
                        cost.setForeground(Color.RED);
                    } else {
                        cost.setForeground(Color.GREEN);
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
                if (user.getBalance() < Ticket.getSum()) {
                    JOptionPane.showMessageDialog(null, "tickets cost is " + Ticket.getSum() + ", your balance " + user.getBalance() + " is not enough, try again!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirmDialog = JOptionPane.showConfirmDialog(null, "Do you really want to booked this chair :\n" + chairs, "Confirm Booked", JOptionPane.YES_NO_OPTION);
                if (confirmDialog != 0) {
                    return;
                }
                user.setBalance(user.getBalance() - Ticket.getSum());
                JOptionPane.showMessageDialog(null, "Payment completed successfully!!!\nYour Balance: " + user.getBalance(), "Payment", JOptionPane.INFORMATION_MESSAGE);
                for (Ticket ticket : showTime.getTickets()) {
                    if (chairs.contains(ticket.getChairNumber()) || chairs.contains(ticket.getChairNumber())) {
                        user.addTicket(ticket);
                        ticket.setAvailable(false);
                    }
                }
                IOFunction.saveDate(showTimes);
                JOptionPane.showMessageDialog(null, "Tickets booked successfully!!!");
                IOFunction.saveUser(user);
                dispose();

            }
        });

        // set color
        chairPanel.setBackground(Color.black);
        ok.setBackground(Color.DARK_GRAY);
        ok.setForeground(Color.cyan);
        clickLabel.setForeground(Color.WHITE);
        costLabel.setForeground(Color.WHITE);
        cost.setForeground(Color.GREEN);
        label.setForeground(Color.CYAN);
        balance.setForeground(Color.ORANGE);
        ticketLabel.setForeground(Color.WHITE);

        // set font
        ok.setFont(panelFont);
        label.setFont(panelFont);
        ticketLabel.setFont(panelFont);

        // set bounds
        clickLabel.setBounds(10, 10, 400, 20);
        balance.setBounds(550, 10, 200, 20);
        costLabel.setBounds(700, 10, 100, 20);
        cost.setBounds(735, 10, 100, 20);
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
        add(cost);
        add(costLabel);
        add(ticketLabel);


        // initial JFrame
        setTitle("Booked Ticket Page");
        setSize(800, 400);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
