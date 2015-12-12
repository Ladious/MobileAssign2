package com.example.jia.mobileassignment;

/**
 * Created by JiaYu on 12/12/2015.
 */
public class Client {
    private String name;
    private String address;

    public Client(){};

    public Client(String name, String address) {
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return this.name + ". " + this.address ;
    }




}
