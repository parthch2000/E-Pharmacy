package pharmacy;

import java.sql.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Pharmacy extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        BorderPane rt2=new BorderPane();
        VBox root2 = new VBox(10);
        Scene sc2 = new Scene(rt2,500,250);
        
        Text head =new Text("Welcome to Parth's Pharmacy..!!");
        Button sign =new Button("Click to SignIn");
        Button exit=new Button("Exit");
        head.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        
        
        sign.setOnAction(e->primaryStage.setScene(sc2));
        exit.setOnAction(e->Platform.exit());
        
        
        BorderPane.setAlignment(head, Pos.TOP_CENTER);
        
        VBox btn=new VBox(10);
        btn.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(btn,Pos.CENTER);

        btn.getChildren().addAll(sign,exit);
        BorderPane root1 =new BorderPane();
        root1.setTop(head);
        root1.setCenter(btn);
        
        root1.setPadding(new Insets(15));
        
        Scene scene1 = new Scene(root1, 500, 300);
        root1.setId("root1");
        scene1.getStylesheets().add("pharmacy//index.css");
        
        HBox uname = new HBox();
        HBox psw = new HBox();
        Label u1 = new Label("Username : ");
        Label p1 = new Label("Password :  ");
        TextField u2=new TextField();
        PasswordField p2=new PasswordField();
        uname.getChildren().addAll(u1,u2);
        psw.getChildren().addAll(p1,p2);
        root2.setPadding(new Insets(50));
        
        final ToggleGroup group = new ToggleGroup();
        
        RadioButton r1=new RadioButton("Admin");
        RadioButton r2=new RadioButton("Customer");
        r1.setToggleGroup(group);
        r2.setToggleGroup(group);
        HBox us=new HBox(10);
        us.getChildren().addAll(r1,r2);
        Button log=new Button("Login");
        
        rt2.setId("rt2");
        sc2.getStylesheets().add("pharmacy//login.css");
         
        
        log.setOnAction(e->{
            String unm=u2.getText();
            String ps=p2.getText();
             try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy",
                        "root", "");

                    PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select * from admin where username=? and password=?");

                    st.setString(1,unm);
                    st.setString(2,ps);
                    ResultSet rs = st.executeQuery();
                    if (r1.isSelected()==true && rs.next()) {
                        admin a= new admin();
                        a.start(primaryStage);
                    }
                    else if(r2.isSelected()==true && rs.next())
                    {
                        User b= new User();
                        b.start(primaryStage);
                    }
                    else
                {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                String s ="Give correct data";
                alert.setContentText(s);
                alert.show();
                }
             }
             catch (SQLException ex) {
            ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            });
        
        root2.getChildren().addAll(uname,psw,us,log);
        root2.setAlignment(Pos.BASELINE_CENTER);
        rt2.setCenter(root2);
        rt2.setAlignment(root2,Pos.CENTER);
        uname.setAlignment(Pos.CENTER);
        psw.setAlignment(Pos.CENTER);
        us.setAlignment(Pos.CENTER);
        
        primaryStage.setTitle("Pharmacy");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
