package com.exercise;

public class Book {
    private String title;
    private String publisher;
    private Author author;

    public Book(String title, String publisher, Author author){
        this.title = title;
        this.publisher = publisher;
        this.author = author;
    }

    public String getTitle(){
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author=" + author +
                '}';
    }
}
