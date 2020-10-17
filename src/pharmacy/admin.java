package pharmacy;

import java.util.logging.Level;
import java.util.logging.Logger;
import pharmacy.Pharmacy;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class admin extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Text hd =new Text("Admin");
        hd.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        
        Button b1=new Button("Check");
        Button b2=new Button("Add");
        Button b3=new Button("Orders");
        Button logout=new Button("Logout");
        HBox opt=new HBox(10);
        opt.setAlignment(Pos.CENTER);
        HBox out= new HBox(logout);
        out.setAlignment(Pos.BASELINE_RIGHT);
        
        b1.setOnAction(e->
        {
            check c=new check();
            try {
                c.start(primaryStage);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        b2.setOnAction(e->
        {
            add u=new add();
            u.start(primaryStage);
        }
        );
        b3.setOnAction(e->
        {
            orders o=new orders();
            try {
                o.start(primaryStage);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        logout.setOnAction(e->{
        Pharmacy p=new Pharmacy();
        p.start(primaryStage);
        });
        opt.getChildren().addAll(hd,b1,b2,b3);
        BorderPane.setAlignment(hd,Pos.TOP_CENTER);
        BorderPane.setAlignment(opt,Pos.CENTER);
        BorderPane.setAlignment(logout,Pos.BOTTOM_CENTER);

        
        BorderPane bp=new BorderPane(hd);
        bp.setCenter(opt);
        bp.setBottom(logout);
        bp.setPadding(new Insets(5));
        Scene sc3=new Scene(bp,300,200);

        bp.setId("bp");
        sc3.getStylesheets().add("pharmacy//admin.css");
        primaryStage.setTitle("Admin");
        primaryStage.setScene(sc3);
        
        }
}
