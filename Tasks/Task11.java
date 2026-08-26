package ProgJava;

public class Task11 {
    public static void main(String[] args) {
        Book[] books = new Book[] {
                new Book("BUK NI RYZA", "RYZA", 2021, 539.99),
                new Book("BUK NI ASH", "ASHMOE", 2020, 529.99),
                new Book("BUK NI BHEA", "BHEA", 2019, 549.99)
        };

        for (int i = 0; i < books.length; i++) {
            System.out.println("Book " + (i + 1) + ":");
            System.out.println(books[i]);
            System.out.println();
        }
    }
}

// different class file
package ProgJava;

public class Book {
    private String title;
    private String author;
    private int yearPublished;
    private double price;

    public Book(String title, String author, int yearPublished, double price) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Title: \"" + title + "\"\n" +
                "Author: \"" + author + "\"\n" +
                "Year Published: " + yearPublished + "\n" +
                "Price: ₱" + String.format("%.2f", price);
    }
}
