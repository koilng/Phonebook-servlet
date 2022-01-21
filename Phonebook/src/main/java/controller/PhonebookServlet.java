package controller;

import dao.ClientsDAO;
import model.Clients;
import view.PhonebookView;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class PhonebookServlet extends HttpServlet {
    ClientsDAO clientsDAO;

    @Override
    public void init(ServletConfig config) {
        clientsDAO = new ClientsDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        PrintWriter out = resp.getWriter();

        out.println("<html>\n<body>\n");

        switch (uri) {
            case "/phonebook/add":
                out.println(PhonebookView.getAddForm());
                break;
            case "/phonebook/clear":
                clientsDAO.clearList();
                clientsDAO.saveToFile();
                break;
            case "/phonebook/list":
                out.println(PhonebookView.getListForm(clientsDAO.index()));
                break;
            case "/phonebook/colleagues":
                out.println(PhonebookView.getColleaguesForm(clientsDAO.index()));
                break;
            case "/phonebook/friends":
                out.println(PhonebookView.getFriendsForm(clientsDAO.index()));
                break;
            case "/phonebook/family":
                out.println(PhonebookView.getFamilyForm(clientsDAO.index()));
                break;
            default:
                out.println(PhonebookView.getMainForm());
                break;
        }

        out.println("</body>\n</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String categories = req.getParameter("familycategory") + " " + req.getParameter("colleaguecategory") + " " + req.getParameter("friendcategory");
        clientsDAO.addClient(req.getParameter("name"), req.getParameter("phone"), categories);
        clientsDAO.saveToFile();

        PrintWriter out = resp.getWriter();
        out.println("<html>\n<body>\n");
        out.println(PhonebookView.getAddForm());
        out.println("</body>\n</html>");
    }
}
