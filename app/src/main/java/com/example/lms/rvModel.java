package com.example.lms;

public class rvModel {
    String BookID, Title, StudentID, Name;

    rvModel(){

    }
    public rvModel(String bookID, String title, String studentID, String name) {
        BookID = bookID;
        Title = title;
        StudentID = studentID;
        Name = name;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
