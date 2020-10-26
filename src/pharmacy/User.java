package pharmacy;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class User extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Text hd =new Text("User");
        hd.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        
        Button b1=new Button("MyOrders");
        Button b2=new Button("Buy");
        Button b3=new Button("Medicines");
        Button b4=new Button("Logout");
        HBox opt=new HBox(10);
        opt.setAlignment(Pos.CENTER);
        
        b1.setOnAction(e->
        {
            uorder a=new uorder();
            try {
                a.start(primaryStage);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        b2.setOnAction(e->
        {
            buy b=new buy();
            b.start(primaryStage);
        });
        b3.setOnAction(e->
        {
            Medicines m=new Medicines();
            try {
                m.start(primaryStage);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        b4.setOnAction(e->{
        Pharmacy p=new Pharmacy();
        p.start(primaryStage);
        });
        
        opt.getChildren().addAll(hd,b1,b2,b3);
        BorderPane.setAlignment(hd,Pos.TOP_CENTER);
        BorderPane.setAlignment(opt,Pos.CENTER);
        BorderPane.setAlignment(b4,Pos.BOTTOM_CENTER);
        
        BorderPane bp=new BorderPane(hd);
        bp.setCenter(opt);
        bp.setBottom(b4);
        bp.setPadding(new Insets(5));
        Scene sc4=new Scene(bp,400,250);
        
        bp.setId("bp");
        sc4.getStylesheets().add("pharmacy//user.css");
        primaryStage.setTitle("User");
        primaryStage.setScene(sc4);
        }
}
