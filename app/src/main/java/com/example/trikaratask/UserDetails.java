package com.example.trikaratask;

public class UserDetails {
    private int id;
    private String name;
    private String email;
    private String pageNumber;


    public UserDetails(String pageNumber){
        this.pageNumber = pageNumber;
    }
    public UserDetails(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }
    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
