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

public class add extends Application{
    @Override
    public void start(Stage st) {
        
        BorderPane root=new BorderPane();
        VBox v=new VBox(10);
        Label l1=new Label("Medicine Name : ");
        Label l2=new Label("Price : ");
        Label l3=new Label("Stock : ");
        Label l4=new Label("Expiry : ");
        Label l5=new Label("Description : ");
        TextField t1=new TextField();
        TextField t2=new TextField();
        TextField t3=new TextField();
        TextField t4=new TextField();
        TextField t5=new TextField();
        HBox h1=new HBox(10);   h1.getChildren().addAll(l1,t1);
        HBox h2=new HBox(10);   h2.getChildren().addAll(l2,t2);
        HBox h3=new HBox(10);   h3.getChildren().addAll(l3,t3);
        HBox h4=new HBox(10);   h4.getChildren().addAll(l4,t4);
        HBox h5=new HBox(10);   h5.getChildren().addAll(l5,t5);
        v.getChildren().addAll(h1,h2,h3,h4,h5);
        v.setAlignment(Pos.CENTER);
        h1.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        h3.setAlignment(Pos.CENTER);
        h4.setAlignment(Pos.CENTER);
        h5.setAlignment(Pos.CENTER);
        
        Button up=new Button("Update");
        up.setOnAction(e->
        {
            if(t1.getText().isEmpty()||t2.getText().isEmpty()||t3.getText().isEmpty()||t4.getText().isEmpty()||t5.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                String s ="Give correct data";
                alert.setContentText(s);
                alert.show();
            }
            String mn=t1.getText();
            int pr=Integer.valueOf(t2.getText());
            int stc=Integer.valueOf(t3.getText());
            String exp=t4.getText();
            String des=t5.getText();
            
             try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy",
                        "root", "");
                    Statement stm=conn.createStatement();
                    String sql="Insert into medicines(name,price,stock,expiry,description) values('"+mn+"',"+pr+","+stc+",'"+exp+"','"+des+"')";
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
            admin a=new admin();
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
        sc.getStylesheets().add("pharmacy//check.css");
        st.setTitle("Add");
        st.setScene(sc);
        st.show();
    }
}
