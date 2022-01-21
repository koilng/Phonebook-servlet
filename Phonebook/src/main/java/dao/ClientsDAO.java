package dao;

import model.Clients;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientsDAO {
    private List<Clients> clientsList;

    public ClientsDAO() {
        this.clientsList = new ArrayList<>();
        loadFromFile();
    }

    public List<Clients> index() {
        return clientsList;
    }

    public void addClient(String name, String phone, String categories) {
        clientsList.add(new Clients(name, phone, categories));
    }

    public void clearList() {
        clientsList.clear();
    }

    private synchronized void loadFromFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getFileInputStream("/clients.txt")));
            String clientInfo;
            while ((clientInfo = bufferedReader.readLine()) != null) {
                System.out.println(clientInfo);
                String[] tokens = clientInfo.split(":");
                clientsList.add(new Clients(tokens[0], tokens[1], tokens[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getFileInputStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }


/*
    public synchronized void saveToFile() {
        try {
            Path path = Paths.get(getFileFromResources("clients.txt"));
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (Clients client: clientsList) {
                writer.write(client.getName() + ":" + client.getPhone() + ":" + client.getCategories() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    private String getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return resource.getPath();
        }
    }

    private File getFileFromResources1(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    public synchronized void saveToFile() {
        try {
            /*            String path = this.getClass().getClassLoader().getResource("clients.txt").toExternalForm();*/

            URL res = getClass().getClassLoader().getResource("clients.txt");
            System.out.println(res);
            File file = Paths.get(res.toURI()).toFile();
            String absolutePath = file.getAbsolutePath();
            System.out.println(absolutePath);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath));
            for (Clients client : clientsList) {
                bufferedWriter.write(client.getName() + ":" + client.getPhone() + ":" + client.getCategories() + "\n");
            }
            bufferedWriter.close();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

 /*   public synchronized void saveToFile() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(getFileFromResources("/clients.txt")));
            for (Clients client: clientsList) {
                out.print(client.getName() + ":" + client.getPhone() + ":" + client.getCategories() + "\n");
            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
}
