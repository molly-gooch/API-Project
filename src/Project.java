
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Project {

    private JFrame mainFrame;
    private int WIDTH = 800;
    private int HEIGHT = 700;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    private JButton b1;
    private JButton b2;

    private JTextArea ta1;
    private JTextArea ta2;
    private JTextArea ta3;


    public Project() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Project project = new Project();
        project.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Animal Creator");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(3, 1));
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel1.setLayout(new GridLayout(1,3));
        panel2.setLayout(new GridLayout(1,2));
        panel3.setLayout(new BorderLayout());
        panel4.setLayout(new BorderLayout());


        ta1 = new JTextArea();
        ta2 = new JTextArea();
        ta3 = new JTextArea();

        mainFrame.add(panel1);
        panel1.add(ta1);
        panel1.add(ta2);
        panel1.add(ta3);
        mainFrame.add(panel2);
        mainFrame.add(panel3);

    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";


        String apiKey = "6fG+e/V/5n45lzPE5UKMCQ==ecwp26yUZKx9j9Ur";

        try {

            URL url = new URL("https://api.api-ninjas.com/v1/animals?name=" + ta1.getText());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("X-Api-Key", apiKey);


            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            String name = (String) jsonObject.get("name");
            Long weight = (Long) jsonObject.get("weight");
            Long height = (Long) jsonObject.get("height");


            String eye = (String) jsonObject.get("eye_color");
            String birth = (String) jsonObject.get("birth_year");
            System.out.println();

            ta2.append("Pokemon name: " + name);
            ta2.append("\n");
            ta2.append("Pokemon weight: " + weight);
            ta2.append("\n");
            ta2.append("Pokemon height: " + height);
            ta2.append("\n");


            org.json.simple.JSONArray ab = (org.json.simple.JSONArray) jsonObject.get("abilities");
            int n = ab.size(); //(msg).length();a
            for (int i = 0; i < n; ++i) {
                JSONObject test = (JSONObject) ab.get(i);
                JSONObject ability = (JSONObject) test.get("ability");
                ta2.append("Ability " + (i + 1) + ": " + ability.get("name"));
                ta2.append("\n");
            }


            org.json.simple.JSONArray forms = (org.json.simple.JSONArray) jsonObject.get("forms");
            int f = forms.size();
            for (int i = 0; i < f; i = i + 1) {
                JSONObject test2 = (JSONObject) forms.get(i);
                String names = (String) test2.get("name");
                //String links = (String) test2.get("url");
                ta2.append("Form " + (i + 1) + ": " + names);
                ta2.append("\n");
                // ta2.append("Form link: " + links);
                // ta2.append("\n");
            }

            org.json.simple.JSONArray type = (org.json.simple.JSONArray) jsonObject.get("types");
            int t = type.size();
            for (int i = 0; i < t; i = i + 1) {
                JSONObject test3 = (JSONObject) type.get(i);
                JSONObject types = (JSONObject) test3.get("type");
                String names = (String) types.get("name");
                ta2.append("Type " + (i + 1) + ": " + names);
                ta2.append("\n");
            }

            org.json.simple.JSONArray move = (org.json.simple.JSONArray) jsonObject.get("moves");
            int m = type.size();
            for (int i = 0; i < m; i = i + 1) {
                JSONObject test4 = (JSONObject) move.get(i);
                JSONObject moves = (JSONObject) test4.get("move");
                String movesy = (String) moves.get("name");
                ta2.append("Move " + (i + 1) + ": " + movesy);
                ta2.append("\n");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEventDemo() {

        b1 = new JButton("Submit");
        b2 = new JButton("Reset");
        b1.setActionCommand("Submit");
        b2.setActionCommand("Reset");
        b1.addActionListener(new ButtonClickListener());
        b2.addActionListener(new ButtonClickListener());
        b1.setFont(new Font("Calibri", Font.BOLD, 30));
        b2.setFont(new Font("Calibri", Font.BOLD, 30));


        b1.setActionCommand("Submit");
        b2.setActionCommand("Reset");


        b1.addActionListener(new ButtonClickListener());
        b2.addActionListener(new ButtonClickListener());

        panel2.add(b1);
        panel2.add(b2);

        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent w) {
            String command = w.getActionCommand();


            if (command.equals("Submit")) {
                try {
                    pull();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }
    }



}