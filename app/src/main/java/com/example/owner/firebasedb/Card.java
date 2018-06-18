package com.example.owner.firebasedb;

public class Card {

    private String departamento, title, message;

    public Card(String departamento, String title, String message) {
        this.departamento = departamento;
        this.title = title;
        this.message = message;

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDepartamento() { return departamento; }

    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
