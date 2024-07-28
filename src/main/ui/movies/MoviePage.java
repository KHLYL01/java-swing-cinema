package main.ui.movies;

import main.io.IOFunction;
import main.model.Cinema;
import main.model.Movie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MoviePage extends JFrame {

    List<Movie> movies = new ArrayList();
    List<Cinema> cinemas = new ArrayList<>();
    JButton[] buttons;
    Image image;

    public MoviePage() {
        List<Movie> movies = IOFunction.getAllMovies();
        buttons = new JButton[movies.size()];

        for (int i = 0; i < buttons.length; i++) {
            try {
                image = ImageIO.read(new File(movies.get(i).getImagePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
//        JLabel imageLabel = new JLabel(new ImageIcon(ig));
            Image scale = image.getScaledInstance(200,300,Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scale);

            buttons[i] = new JButton(imageIcon);


            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MovieDetailsPage(movies.get(finalI));
                    dispose();
                }
            });

            add(buttons[i]);
        }


        setTitle("Movies Page");
        setSize(600,620);
        setLayout(new GridLayout(2,3));
        getContentPane().setBackground(Color.black);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
}
