
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
    private JPanel panel101;
    private JPanel panel102;
    private JPanel panel103;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

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
        panel101 = new JPanel(new BorderLayout());
        panel102 = new JPanel(new BorderLayout());
        panel103 = new JPanel(new BorderLayout());
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
        panel101.add(ta1);
        panel1.add(panel101, BorderLayout.CENTER);
        panel1.add(panel102, BorderLayout.CENTER);
        panel102.add(ta2);
        panel103.add(ta3);
        panel1.add(panel103, BorderLayout.CENTER);
        mainFrame.add(panel2);
        mainFrame.add(panel3);

    }

    public void pull() throws ParseException {
        String output1 = "abc";
        String output2 = "abc";
        String output3 = "abc";
        String totlaJson1 = "";
        String totlaJson2 = "";
        String totlaJson3 = "";


        String apiKey = "6fG+e/V/5n45lzPE5UKMCQ==ecwp26yUZKx9j9Ur";

        try {

            URL url1 = new URL("https://api.api-ninjas.com/v1/animals?name=" + ta1.getText());
            URL url2 = new URL("https://api.api-ninjas.com/v1/animals?name=" + ta2.getText());
            URL url3 = new URL("https://api.api-ninjas.com/v1/animals?name=" + ta3.getText());
            HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
            HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();

            conn1.setRequestMethod("GET");
            conn1.setRequestProperty("Accept", "application/json");
            conn1.setRequestProperty("X-Api-Key", apiKey);

            conn2.setRequestMethod("GET");
            conn2.setRequestProperty("Accept", "application/json");
            conn2.setRequestProperty("X-Api-Key", apiKey);

            conn3.setRequestMethod("GET");
            conn3.setRequestProperty("Accept", "application/json");
            conn3.setRequestProperty("X-Api-Key", apiKey);


            if (conn1.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn1.getResponseCode());
            }

            if (conn2.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn2.getResponseCode());
            }

            if (conn3.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn3.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn1.getInputStream())));

            BufferedReader br2 = new BufferedReader(new InputStreamReader(
                    (conn2.getInputStream())));

            BufferedReader br3 = new BufferedReader(new InputStreamReader(
                    (conn3.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output1 = br.readLine()) != null) {
                System.out.println(output1);
                totlaJson1 += output1;
            }

            System.out.println("Output from Server .... \n");
            while ((output2 = br2.readLine()) != null) {
                System.out.println(output2);
                totlaJson2 += output2;
            }

            System.out.println("Output from Server .... \n");
            while ((output3 = br3.readLine()) != null) {
                System.out.println(output3);
                totlaJson3 += output3;
            }

            conn1.disconnect();
            conn2.disconnect();
            conn3.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser1 = new JSONParser();
        org.json.simple.JSONObject jsonObject1 = (org.json.simple.JSONObject) parser1.parse(totlaJson1);
        System.out.println(jsonObject1);

        JSONParser parser2 = new JSONParser();
        org.json.simple.JSONObject jsonObject2 = (org.json.simple.JSONObject) parser2.parse(totlaJson2);
        System.out.println(jsonObject2);

        JSONParser parser3 = new JSONParser();
        org.json.simple.JSONObject jsonObject3 = (org.json.simple.JSONObject) parser3.parse(totlaJson3);
        System.out.println(jsonObject3);

        try {

            String name1 = (String) jsonObject1.get("name");
            System.out.println(name1);



            String name2 = (String) jsonObject2.get("name");
            System.out.println(name2);

            String name3 = (String) jsonObject3.get("name");
            System.out.println(name3);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEventDemo() {

        b1 = new JButton("Submit");
        b2 = new JButton("Reset");
        b3 = new JButton("Reset");
        b4 = new JButton("Reset");
        b1.setActionCommand("Submit");
        b2.setActionCommand("Reset1");
        b3.setActionCommand("Reset2");
        b4.setActionCommand("Reset3");
        b1.addActionListener(new ButtonClickListener());
        b2.addActionListener(new ButtonClickListener());
        b3.addActionListener(new ButtonClickListener());
        b4.addActionListener(new ButtonClickListener());
        b1.setFont(new Font("Calibri", Font.BOLD, 30));
        b2.setFont(new Font("Calibri", Font.BOLD, 30));
        b3.setFont(new Font("Calibri", Font.BOLD, 30));
        b4.setFont(new Font("Calibri", Font.BOLD, 30));



        panel2.add(b1);
        panel101.add(b2, BorderLayout.SOUTH);
        panel102.add(b3, BorderLayout.SOUTH);
        panel103.add(b4, BorderLayout.SOUTH);


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

            if(command.equals("Reset1")){
                ta1.setText("");
            }

            if(command.equals("Reset2")){
                ta2.setText("");
            }

            if(command.equals("Reset3")){
                ta3.setText("");
            }

        }
    }



}