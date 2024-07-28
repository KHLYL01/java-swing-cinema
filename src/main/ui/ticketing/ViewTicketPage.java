package main.ui.ticketing;

import main.io.IOFunction;
import main.model.ShowTime;
import main.model.Ticket;
import main.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewTicketPage extends JFrame {

    private ShowTime showTime;
    private List<Ticket> tickets;
    private Font panelFont = new Font("Arial", Font.BOLD, 10);
    private JButton[] chairButtons = new JButton[104];
    private JPanel chairPanel = new JPanel(new GridLayout(8, 15));


    private JPanel colorPanel = new JPanel(new GridLayout(1, 3));
    private JLabel orange = new JLabel("Your Tickets (Orange)");
    private JLabel red = new JLabel("booked Tickets (Red)");
    private JLabel cyan = new JLabel("Available Tickets (Cyan)");

    public ViewTicketPage(int showTimeId,List<ShowTime> showTimes) {

        for (ShowTime showTime : showTimes) {
            if (showTime.getId() == showTimeId) {
                this.showTime = showTime;
                break;
            }
        }

        User user = IOFunction.getUser();
        tickets = showTime.getTickets();

        for (int i = 0; i < chairButtons.length; i++) {
            if (i < 9) {
                chairButtons[i] = new JButton("0" + tickets.get(i).getChairNumber());
            } else {
                chairButtons[i] = new JButton("" + tickets.get(i).getChairNumber());
            }

            chairButtons[i].setBackground(Color.darkGray);

            if(!tickets.get(i).isAvailable()){
                chairButtons[i].setForeground(Color.RED);
            }else {
                chairButtons[i].setForeground(Color.CYAN);
            }

            for(Ticket userTicket :user.getTickets()){
                if(userTicket.getChairNumber() == tickets.get(i).getChairNumber() && userTicket.getDate().equals(showTime.getDate())){
                    System.out.println(showTime.getDate());
                    System.out.println(tickets.get(i).getDate());
                    chairButtons[i].setForeground(Color.ORANGE);
                    break;
                }
            }

            chairButtons[i].setFont(panelFont);
            chairPanel.add(chairButtons[i]);
        }

        chairPanel.setBounds(10, 60, 770, 260);
        colorPanel.setBounds(60, 20, 780, 20);

        chairPanel.setBackground(Color.black);
        colorPanel.setBackground(Color.BLACK);
        orange.setForeground(Color.ORANGE);
        red.setForeground(Color.RED);
        cyan.setForeground(Color.CYAN);

        colorPanel.add(orange);
        colorPanel.add(red);
        colorPanel.add(cyan);

        add(chairPanel);
        add(colorPanel);



        setTitle("View Ticket Page");
        setSize(800, 400);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

}
