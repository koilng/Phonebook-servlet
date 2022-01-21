package model;

import dao.ClientsDAO;

import java.util.HashMap;

public class Clients {
    private String name;
    private String phone;
    private String categories;

    public Clients(String name, String phone, String categories) {
        this.name = name;
        this.phone = phone;
        this.categories = categories;
    }

    public String getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
