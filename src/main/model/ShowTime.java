package main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowTime implements Serializable {
    private static int count = 0;
    private int id;
    private String date;
    private Movie movie;
    private Cinema cinema;
    private List<Ticket> tickets = new ArrayList();

    public ShowTime(String date, Movie movie, Cinema cinema) {
        this.date = date;
        this.movie = movie;
        this.cinema = cinema;
        for (int i = 1; i <= 104; i++) {
            tickets.add(new Ticket(i,1500,this.date));
        }
        id = ++count;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return date;
    }
}
