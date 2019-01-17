package com.example.android.myapplication;

public class Farmer {
    private String name,description, Details;
    private String Stock;
    private String profilePic, Location;

    public Farmer(){}

    public Farmer(String location, String description, String details, String name, String profilePic,String stock)
    {
        this.name = name;
        Location = location;
        this.description = description;
        this.profilePic = profilePic;
        Details = details;
        Stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
