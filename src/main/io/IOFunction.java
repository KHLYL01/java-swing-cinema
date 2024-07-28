package main.io;

import main.model.Cinema;
import main.model.Movie;
import main.model.ShowTime;
import main.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFunction {

    public static String username;

    public static void saveUser(User user) {
        try {
            File file = new File(username+".txt");
            file.createNewFile();
            final FileOutputStream fout = new FileOutputStream(file);
            final ObjectOutputStream out = new ObjectOutputStream(fout);

            out.writeObject(user);
            out.flush();
            System.out.println("user save successfully");

            out.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser() {
//        if (new File("user.txt").exists()) {
        User user = null;
        try {
            FileInputStream fin = new FileInputStream(username+".txt");
            ObjectInputStream in = new ObjectInputStream(fin);

            user = (User) in.readObject();
            System.out.println("read data successfully");

            in.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static boolean userIsFound() {
        if (new File(username+".txt").exists())
            return true;
        return false;
    }

    public static List<ShowTime> initData() {

        List<Movie> movies = getAllMovies();

        List<Cinema> cinemas =  getAllCinemas();

        List<ShowTime> showTimes = List.of(
                new ShowTime("12-03-2024",movies.get(0),cinemas.get(0)),
                new ShowTime("15-03-2024",movies.get(0),cinemas.get(1)),
                new ShowTime("20-03-2024",movies.get(0),cinemas.get(0)),
                new ShowTime("28-04-2024",movies.get(1),cinemas.get(1)),
                new ShowTime("02-05-2024",movies.get(1),cinemas.get(1)),
                new ShowTime("16-05-2024",movies.get(2),cinemas.get(1)),
                new ShowTime("30-05-2024",movies.get(2),cinemas.get(0)),
                new ShowTime("01-07-2024",movies.get(2),cinemas.get(0)),
                new ShowTime("04-07-2024",movies.get(2),cinemas.get(0)),
                new ShowTime("18-07-2024",movies.get(3),cinemas.get(1)),
                new ShowTime("22-08-2024",movies.get(3),cinemas.get(0)),
                new ShowTime("27-09-2024",movies.get(4),cinemas.get(0)),
                new ShowTime("15-10-2024",movies.get(4),cinemas.get(1)),
                new ShowTime("20-10-2024",movies.get(4),cinemas.get(1)),
                new ShowTime("06-11-2024",movies.get(4),cinemas.get(1)),
                new ShowTime("07-11-2024",movies.get(4),cinemas.get(0)),
                new ShowTime("12-12-2024",movies.get(5),cinemas.get(1)),
                new ShowTime("16-12-2024",movies.get(5),cinemas.get(0))
        );

        try {
            File file = new File( "cinema_data.txt");
            file.createNewFile();
            final FileOutputStream fout = new FileOutputStream(file);
            final ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(showTimes);
            out.flush();
            System.out.println("initial data successfully");
            out.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return showTimes;
    }



    public static void saveDate(List<ShowTime> showTimes) {

        try {
            final FileOutputStream fout = new FileOutputStream( "cinema_data.txt");
            final ObjectOutputStream out = new ObjectOutputStream(fout);

            out.writeObject(showTimes);
            out.flush();
            System.out.println("save data successfully");

            out.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ShowTime> getData() {
        if (new File("cinema_data.txt").exists()) {
            List<ShowTime> showTimes = new ArrayList<>();
                try {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream("cinema_data.txt"));
                    showTimes = (List<ShowTime>) in.readObject();

                    System.out.println("read data successfully");


                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            return showTimes;
        } else {
            return initData();
        }

    }

    public static List<Movie> getAllMovies() {
        return List.of(
                new Movie(1, "jujutsu Kaisen", "image/jujutsu.jpg", "action, shonen, school, super power"),
                new Movie(2, "Demon Slayer", "image/demon.jpg", "action, history, shonen, super power, demons"),
                new Movie(3, "Attack On Titan", "image/attack.jpg", "action, drama, shonen, military"),
                new Movie(4, "JoJo Diamond is Unbreakable", "image/jojo.jpg", "action, super power, shonen, adventure"),
                new Movie(5, "One Piece", "image/one piece.jpg", "action, super power, shonen, adventure"),
                new Movie(6, "Naruto Shippuuden", "image/naruto.jpg", "action, drama, fighting arts, shonen, adventure")
        );
    }
    public static List<Cinema> getAllCinemas() {
        return List.of(
                new Cinema("cinema1"),
                new Cinema("cinema2")
        );
    }
}
