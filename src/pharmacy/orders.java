package pharmacy;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class orders extends Application {
    
    private final TableView<Orders> tbl = new TableView<Orders>();
    @Override
    public void start(Stage st) throws ClassNotFoundException
    {
        BorderPane root=new BorderPane();
        Scene sc=new Scene(root,500,500);
        TableColumn<Orders,String> id=new TableColumn("OrderID");
        id.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        TableColumn <Orders,String>mn=new TableColumn("Medicine");
        mn.setCellValueFactory(new PropertyValueFactory("Medicine"));
        TableColumn <Orders,String>pr=new TableColumn("Amount");
        pr.setCellValueFactory(new PropertyValueFactory("Amount"));
        TableColumn <Orders,String>name=new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory("Name"));
        
        VBox v=new VBox(10);
        Label l=new Label("ORDERS");
        l.setId("l");
        v.getChildren().addAll(l,tbl);
        v.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(v, Pos.CENTER);
        root.setCenter(v);
        ObservableList<Orders> data=FXCollections.observableArrayList();
        tbl.getColumns().addAll(id,name,mn,pr);
        
            try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy",
                        "root", "");
                    Statement stm=conn.createStatement();
                    String sql="Select * from orders";
                    ResultSet rs=stm.executeQuery(sql);
                    
                    while(rs.next())
                    {
                        int Id=rs.getInt("OrderID");
                        String Name=rs.getString("BuyerName");
                        String Medicine=rs.getString("Medicine");
                        int Amount=rs.getInt("Amount");
                        data.add(new Orders(Id,Name,Medicine,Amount));
                    
                    }
                    tbl.setItems(data);
                    
        }
        catch(SQLException e1)
        {
            e1.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(uorder.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        HBox h1=new HBox(10);
        Button exit=new Button("Exit");
        exit.setOnAction(e->
        {
            admin a=new admin();
            a.start(st);
        }
        );
        h1.getChildren().add(exit);
        h1.setAlignment(Pos.CENTER);
        root.setBottom(h1);
        root.setId("bp");
        sc.getStylesheets().add("pharmacy//check.css");
        st.setTitle("Orders");
        st.setScene(sc);
        st.show();
    }

    
    public static class Orders {
 
        SimpleIntegerProperty OrderID;
        SimpleStringProperty Medicine;
        SimpleIntegerProperty Amount;
        SimpleStringProperty Name;
        
 
        public Orders(Integer ID,String Name,String Medicine,int Amount) {
            this.OrderID = new SimpleIntegerProperty(ID);
            this.Medicine = new SimpleStringProperty(Medicine);
            this.Amount = new SimpleIntegerProperty(Amount);
            this.Name= new SimpleStringProperty(Name);

        }
        public int getOrderID() {
            return OrderID.get();
        }
 
        public void setOrderID(int id1) {
            OrderID.set(id1);
        }
 
        public String getMedicine() {
            return Medicine.get();
        }
 
        public void setMedicine(String mn1) {
            Medicine.set(mn1);
        }
 
        public int getAmount() {
            return Amount.get();
        }
 
        public void setAmount(int pr1) {
            Amount.set(pr1);
        }
        public String getName() {
            return Name.get();
        }
 
        public void setName(String name1) {
            Name.set(name1);
        }
       
    }
}
