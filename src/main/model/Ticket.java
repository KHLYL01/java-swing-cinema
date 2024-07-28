package main.model;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static int sum = 0;
    private int chairNumber;
    private boolean available;
    private int price;
    private String date;

    public Ticket(int chairNumber, int price, String date) {
        this.chairNumber = chairNumber;
        this.price = price;
        this.date = date;
        this.available = true;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        this.chairNumber = chairNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static int getSum() {
        return sum;
    }

    public static void setSum(int sum) {
        Ticket.sum = sum;
    }

    @Override
    public String toString() {
        return chairNumber+"";
    }
}
