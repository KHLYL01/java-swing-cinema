package main.ui.movies;

import main.io.IOFunction;
import main.model.Cinema;
import main.model.Movie;
import main.model.ShowTime;
import main.model.Ticket;
import main.ui.ticketing.BookedTicketPage;
import main.ui.ticketing.CancelBookedPage;
import main.ui.ticketing.ViewTicketPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MovieDetailsPage extends JFrame {

    private final Movie movie;
    private Font font = new Font("Arial", Font.BOLD, 16);
    private Font dateFont = new Font("Arial", Font.BOLD, 14);

    private String[] buttonTitle = {"View Tickets","Booked Tickets","Cancel Booked"};

    public MovieDetailsPage(Movie movie) {
        this.movie = movie;


        Image image = null;
        try {
            image = ImageIO.read(new File(movie.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        JLabel imageLabel = new JLabel(new ImageIcon(ig));
        Image scale = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(scale);

        JLabel imageLabel = new JLabel(imageIcon);

        JLabel title = new JLabel("Title:");
        JLabel movieTitle = new JLabel(movie.getTitle());
        JLabel category = new JLabel("Categories: ");
        JLabel movieCategory = new JLabel(movie.getType());
        JLabel cinemaLabel = new JLabel("Display on:");
        JLabel ticketLabel = new JLabel("Tickets :");


        DefaultListModel cinemaModel = new DefaultListModel();
        JList cinemaList = new JList<>(cinemaModel);
        JScrollPane cinemaScrollPane = new JScrollPane(cinemaList);

        DefaultListModel showTimeModel = new DefaultListModel();
        JList showTimeList = new JList<>(showTimeModel);
        JScrollPane showTimeScrollPane = new JScrollPane(showTimeList);


        JPanel chairPanel = new JPanel(new GridLayout(8, 15));
        JButton[] chairButtons = new JButton[104];

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton[] buttons = new JButton[3];

        List<ShowTime> showTimes = IOFunction.getData();

        chairPanel.setVisible(false);
        ticketLabel.setVisible(false);

        for (Cinema cinema : IOFunction.getAllCinemas()) {
            cinemaModel.addElement(cinema.getName());
        }

        cinemaList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                showTimeModel.clear();
            for (ShowTime showTime : showTimes) {
                    if (showTime.getMovie().getTitle().equals(movie.getTitle()) && showTime.getCinema().getName().equals(cinemaList.getSelectedValuesList().get(0).toString())) {
                        showTimeModel.addElement(showTime);
                    }
                }

                if (showTimeModel.isEmpty()) {
                    showTimeModel.addElement("Not Found!!!");
                    chairPanel.setVisible(false);
                    ticketLabel.setVisible(false);

                } else {
                    showTimeList.setSelectedIndex(0);
                }

            }
        });

        buttonPanel.setBackground(Color.black);

        cinemaList.setSelectedIndex(0);

        for (int i = 0; i < buttons.length; i++) {

            buttons[i] = new JButton(buttonTitle[i]);
            buttons[i].setBackground(Color.DARK_GRAY);
            buttons[i].setForeground(Color.CYAN);
            buttons[i].setFont(font);
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(showTimeList.getSelectedValuesList().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Select show time, try again!!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ShowTime selected = (ShowTime) showTimeList.getSelectedValuesList().get(0);
                    Ticket.setSum(0);
                    switch (finalI){
                           case 0 -> new ViewTicketPage(selected.getId(),showTimes);
                           case 1 -> new BookedTicketPage(selected.getId(),showTimes);
                           case 2 -> new CancelBookedPage(selected.getId(),showTimes);
                    }
                }
            });
            buttonPanel.add(buttons[i]);
        }


        cinemaScrollPane.setBorder(null);
        cinemaScrollPane.setForeground(Color.MAGENTA);
        cinemaScrollPane.setBackground(Color.MAGENTA);
        cinemaList.setFont(font);
        cinemaList.setForeground(Color.WHITE);
        cinemaList.setBackground(Color.BLACK);
        cinemaList.setBorder(new LineBorder(Color.CYAN, 2));

        showTimeScrollPane.setBorder(null);
        showTimeScrollPane.setForeground(Color.MAGENTA);
        showTimeScrollPane.setBackground(Color.MAGENTA);
        showTimeList.setFont(dateFont);
        showTimeList.setForeground(Color.WHITE);
        showTimeList.setBackground(Color.BLACK);
        showTimeList.setBorder(new LineBorder(Color.CYAN, 2));


        title.setForeground(Color.cyan);
        movieTitle.setForeground(Color.white);
        category.setForeground(Color.cyan);
        movieCategory.setForeground(Color.white);
        cinemaLabel.setForeground(Color.cyan);
        ticketLabel.setForeground(Color.cyan);

        imageLabel.setBounds(10, 10, 200, 300);
        title.setBounds(220, 20, 50, 20);
        movieTitle.setBounds(230, 40, 200, 20);
        category.setBounds(220, 60, 100, 20);
        movieCategory.setBounds(230, 80, 300, 20);
        cinemaLabel.setBounds(220, 120, 200, 20);
        cinemaScrollPane.setBounds(230, 150, 100, 160);
        showTimeScrollPane.setBounds(340, 150, 100, 160);
        ticketLabel.setBounds(20, 320, 50, 20);
        buttonPanel.setBounds(500, 160, 170, 150);


        add(imageLabel);
        add(title);
        add(movieTitle);
        add(category);
        add(movieCategory);
        add(cinemaLabel);
        add(cinemaScrollPane);
        add(showTimeScrollPane);
        add(ticketLabel);
        add(buttonPanel);


        setTitle(movie.getTitle());
        setSize(700, 360);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
