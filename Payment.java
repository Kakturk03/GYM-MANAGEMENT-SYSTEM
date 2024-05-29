package CMPE232;

import java.sql.Timestamp;

public class Payment {
    private int paymentID;
    private int memberID;
    private Timestamp date;
    private double amount;
    private String description;

    // Constructor
    public Payment(int paymentID, int memberID, Timestamp date, double amount, String description) {
        this.paymentID = paymentID;
        this.memberID = memberID;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    // Getters and Setters
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

