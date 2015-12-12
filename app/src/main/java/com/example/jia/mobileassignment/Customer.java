package com.example.jia.mobileassignment;

/**
 * Created by Ladious on 12/12/2015.
 */
public class Customer {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String representative;
    private String contact;
    private String position;
    private String email;

    public Customer(){};

    public Customer(String id, String name, String address, String phone,
                    String representative, String contact, String position, String email){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.representative = representative;
        this.contact = contact;
        this.position = position;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
