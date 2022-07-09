package com.example.advancedoopproject;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.sql.Statement;

import java.io.IOException;


public class PatientsLogin extends Application
{
    static Stage loginstage =new Stage();

    @Override
    public void start(Stage stage) throws IOException
    {
        //Node
        Text PhoneNo_label=new Text("PhoneNo");
        Text pass_label=new Text("Password");

        Button login_button=new Button("Log In");

        TextField phoneNo=new TextField();
        PasswordField pass=new PasswordField();

        //Container
        GridPane gridpane=new GridPane();
        gridpane.setMinSize(800,400);
        gridpane.setVgap(20);
        gridpane.setHgap(40);
        gridpane.setAlignment(Pos.CENTER);

        gridpane.add(PhoneNo_label, 1,1);
        gridpane.add(pass_label,1,2);
        gridpane.add(login_button,1,3);
        gridpane.add(phoneNo,2,1);
        gridpane.add(pass,2,2);

        login_button.setOnMouseClicked((new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                String PhoneNo = phoneNo.getText();
                String password = pass.getText();

                try
                {

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/makiniclinic?","root","");

                    Statement st= con.createStatement();

                    String query = "SELECT * FROM makiniclinic.patients Where PhoneNo = '"+PhoneNo+"' AND password = '"+password+"' ";
                    ResultSet rs = st.executeQuery(query);
                    System.out.println("Connected Successfully");

                    /*if(rs.next())
                    {
                        //Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                        //al.setContentText("Successful PatientsLogin");
                        //al.show();
                        PatientsHome h = new PatientsHome();
                        h.start(PatientsHome.homeStage);
                        stage.close();
                    }

                    else
                    {
                        Alert al = new Alert(Alert.AlertType.WARNING);
                        al.setContentText("Invalid Credentials");
                        al.show();

                    }*/

                    con.close();

                }
                catch(Exception ee){System.out.println(ee);System.out.println("Connection error");}
                {
                }
            }
        }));

        //Scene
        Scene scene=new Scene(gridpane);

        //Stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Makini Clinic: PatientsLogin Page");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
