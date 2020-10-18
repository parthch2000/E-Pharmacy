package pharmacy;

import java.sql.*;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class buy extends Application{
    @Override
    public void start(Stage st) {
        
        BorderPane root=new BorderPane();
        Label l=new Label("Fill the Details");
        BorderPane.setAlignment(l, Pos.TOP_CENTER);
        root.setTop(l);
        VBox v=new VBox(10);
        Label l1=new Label("Medicine Name : ");
        Label l2=new Label("Amount : ");
        Label l3=new Label("Name : ");
        Label l4=new Label("UserID : ");
        Label l5=new Label("Address : ");
        Label l6=new Label("Phone No. : ");
        TextField t1=new TextField();
        TextField t2=new TextField();
        TextField t3=new TextField();
        TextField t4=new TextField();
        TextField t5=new TextField();
        TextField t6=new TextField();
        
        HBox h1=new HBox(10);   h1.getChildren().addAll(l1,t1);
        HBox h2=new HBox(10);   h2.getChildren().addAll(l2,t2);
        HBox h3=new HBox(10);   h3.getChildren().addAll(l3,t3);
        HBox h4=new HBox(10);   h4.getChildren().addAll(l4,t4);
        HBox h5=new HBox(10);   h5.getChildren().addAll(l5,t5);
        HBox h6=new HBox(10);   h6.getChildren().addAll(l6,t6);
        v.getChildren().addAll(h1,h2,h3,h4,h5,h6);
        v.setAlignment(Pos.CENTER);
        h1.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        h3.setAlignment(Pos.CENTER);
        h4.setAlignment(Pos.CENTER);
        h5.setAlignment(Pos.CENTER);
        h6.setAlignment(Pos.CENTER);
        
        Button up=new Button("Order");
        up.setOnAction(e->
        {
            String mn=t1.getText();
            int amt=Integer.valueOf(t2.getText());
            String nm=t3.getText();
            String uid=t4.getText();
            String home=t5.getText();
            long no=Long.valueOf(t6.getText());

//            else
//            {
             try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy",
                        "root", "");
                    Statement stm=conn.createStatement();
                    String sql="Insert into orders(UID,BuyerName,Medicine,Amount,Address,Contact) values('"+uid+"','"+nm+"','"+mn+"',"+amt+",'"+home+"',"+no+")";
                    stm.executeUpdate(sql);
                    
                    stm.close();
             }
             catch (SQLException ex) {
            ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
//            }
        });
        HBox h=new HBox(10);
        Button exit=new Button("Exit");
        exit.setOnAction(e->
        {
            User a=new User();
            a.start(st);
        }
        );
        h.getChildren().addAll(up,exit);
        h.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(v,Pos.CENTER);
        BorderPane.setAlignment(h,Pos.BOTTOM_CENTER);        
        root.setCenter(v);
        root.setBottom(h);
        root.setId("bp");
        Scene sc=new Scene(root,400,300);
        sc.getStylesheets().add("pharmacy//user.css");
        st.setTitle("Buy");
        st.setScene(sc);
        st.show();
    }
//    public static void main(String args[])
//    {
//        launch(args);
//    }
}
