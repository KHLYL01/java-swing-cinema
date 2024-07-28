package main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static int count = 0;
    private int id;
    private String name;
    private String password;
    private String details;
    private int balance;
    private List<Ticket> tickets;

    public User(String name, String password ,String details) {
        this.name = name;
        this.password = password;
        this.details = details;
        this.balance = 0;
        tickets = new ArrayList<>();
        id = ++count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket){
        boolean isFound = false;
        Ticket newUserTicket = null;
        for (Ticket userTicket :tickets){
            if(userTicket.getChairNumber()==ticket.getChairNumber()){
                newUserTicket = userTicket;
                isFound = true;
                break;
            }
        }
        if(isFound){
            System.out.println(tickets.remove(newUserTicket));

        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", details='" + details + '\'' +
                ", balance=" + balance +
                '}';
    }
}
