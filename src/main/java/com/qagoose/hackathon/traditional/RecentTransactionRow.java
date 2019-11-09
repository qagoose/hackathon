package com.qagoose.hackathon.traditional;

import java.util.Objects;

public class RecentTransactionRow {
    private String status;
    private String date;
    private String description;
    private String category;
    private String amount;
    private int position;

    public RecentTransactionRow(String status, String date, String description, String category, String amount, int position) {
        this.status = status;
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getAmount() {
        return amount;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecentTransactionRow that = (RecentTransactionRow) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(date, that.date) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public String toString() {
        return "RecentTransactionRow{" +
                "status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, date, description, category, amount);
    }
}
