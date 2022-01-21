package view;

import model.Clients;

import java.util.List;

public class PhonebookView {
    public static String getAddForm() {
        StringBuilder sb = new StringBuilder();

        sb.append("<form method=\"POST\" action=\"/phonebook\">\n");
        sb.append("Name: <input type=\"text\" name=\"name\">\n");
        sb.append("Phone: <input type=\"text\" name=\"phone\">\n");
        sb.append("<input type=\"submit\" value=\"add\">\n");
        sb.append("<br>\n");
        sb.append("<input type=\"checkbox\" name=\"friendcategory\" value=\"friend\"> Friend");
        sb.append("<br>\n");
        sb.append("<input type=\"checkbox\" name=\"colleaguecategory\" value=\"colleague\"> Colleague");
        sb.append("<br>\n");
        sb.append("<input type=\"checkbox\" name=\"familycategory\" value=\"family\"> Family");
        sb.append("</form>");
        return sb.toString();
    }

    public static String getListForm(List<Clients> clientsList) {
        StringBuilder sb = new StringBuilder();
        clientsList.forEach(clients -> {
            sb.append("<p>" + clients.getName() + " : " + clients.getPhone() + "<p>\n");
        });
        return sb.toString();
    }

    public static String getMainForm() {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"/phonebook/add\" style=\"color: green; text-decoration: none; border: 2px solid green\">ADD</a>");
        sb.append("<a href=\"/phonebook/clear\" style=\"color: red; text-decoration: none; border: 2px solid red\">CLEAR</a>");
        sb.append("<a href=\"/phonebook/list\" style=\"color: orange; text-decoration: none; border: 2px solid orange\">LIST</a>");
        sb.append("<br>\n");
        sb.append("<a href=\"/phonebook/friends\" style=\"color: yellow; text-decoration: none; border: 2px solid black\">FRIENDS</a>");
        sb.append("<a href=\"/phonebook/colleagues\" style=\"color: blue; text-decoration: none; border: 2px solid blue\">COLLEAGUES</a>");
        sb.append("<a href=\"/phonebook/family\" style=\"color: purple; text-decoration: none; border: 2px solid purple\">FAMILY</a>\n");
        return sb.toString();
    }

    public static String getFriendsForm(List<Clients> clientsList) {
        StringBuilder sb = new StringBuilder();
        clientsList.forEach(clients -> {
            if (clients.getCategories().contains("friend")) {
                sb.append("<p>" + clients.getName() + " : " + clients.getPhone() + "<p>\n");
            }
        });
        return sb.toString();
    }

    public static String getColleaguesForm(List<Clients> clientsList) {
        StringBuilder sb = new StringBuilder();
        clientsList.forEach(clients -> {
            if (clients.getCategories().contains("colleague")) {
                sb.append("<p>" + clients.getName() + " : " + clients.getPhone() + "<p>\n");
            }
        });
        return sb.toString();
    }

    public static String getFamilyForm(List<Clients> clientsList) {
        StringBuilder sb = new StringBuilder();
        clientsList.forEach(clients -> {
            if (clients.getCategories().contains("family")) {
                sb.append("<p>" + clients.getName() + " : " + clients.getPhone() + "<p>\n");
            }
        });
        return sb.toString();
    }
}
