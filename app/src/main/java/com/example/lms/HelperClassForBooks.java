package com.example.lms;

public class HelperClassForBooks {

    String bookno, title, author, category;

    public String getBookno() {
        return bookno;
    }

    public void setBookno(String bookno) {
        this.bookno = bookno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public HelperClassForBooks(String bookno, String title, String author, String category) {
        this.bookno = bookno;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public HelperClassForBooks() {
    }
}
