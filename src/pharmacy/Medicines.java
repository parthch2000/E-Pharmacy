package pharmacy;

import java.sql.*;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Medicines extends Application{
    private final TableView<Medicine> tbl = new TableView<Medicine>();
    @Override
    public void start(Stage st) throws ClassNotFoundException
    {
        BorderPane root=new BorderPane();
        Scene s=new Scene(root,500,500);
        TableColumn<Medicine,String> id=new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn <Medicine,String>mn=new TableColumn("MName");
        mn.setCellValueFactory(new PropertyValueFactory("MName"));
        TableColumn <Medicine,String>pr=new TableColumn("Price");
        pr.setCellValueFactory(new PropertyValueFactory("Price"));
        TableColumn <Medicine,String>stc=new TableColumn("Stock");
        stc.setCellValueFactory(new PropertyValueFactory("Stock"));
        TableColumn <Medicine,String>ex=new TableColumn("Expiry");
        ex.setCellValueFactory(new PropertyValueFactory("Expiry"));
        TableColumn <Medicine,String>des=new TableColumn("Description");
        des.setCellValueFactory(new PropertyValueFactory("Description"));
        
        
        VBox v=new VBox(10);
        Label l=new Label("MEDICINES");
        l.setId("l");
        v.getChildren().addAll(l,tbl);
        v.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(tbl, Pos.CENTER);
        root.setCenter(v);
        ObservableList<Medicine> data=FXCollections.observableArrayList(new Medicine(1,"Para",12,32,"2112","good"));
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy",
                        "root", "");
                    Statement stm=conn.createStatement();
                    String sql="Select * from medicines";
                    ResultSet rs=stm.executeQuery(sql);
                    while(rs.next())
                    {
                        int Id=rs.getInt("ID");
                        String Name=rs.getString("name");
                        int price=rs.getInt("price");
                        int stock=rs.getInt("stock");
                        String exp=rs.getString("expiry");
                        String desc=rs.getString("description");

                        data.add(new Medicine(Id,Name,price,stock,exp,desc));
                    }
                    tbl.setItems(data);
                    tbl.getColumns().addAll(id,mn,pr,stc,ex,des);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        HBox h=new HBox(10);
        Button exit=new Button("Exit");
        exit.setOnAction(e->
        {
            User u=new User();
            u.start(st);
        }
        );
        h.getChildren().addAll(exit);
        h.setAlignment(Pos.CENTER);
        root.setBottom(h);
        root.setId("bp");
        s.getStylesheets().add("pharmacy//check.css");
        st.setTitle("Medicines");
        st.setScene(s);
        st.show();
    }
    public static class Medicine {
 
        SimpleIntegerProperty ID;
        SimpleStringProperty MName;
        SimpleIntegerProperty Price;
        SimpleIntegerProperty Stock;
        SimpleStringProperty Expiry;
        SimpleStringProperty Description;
        
 
        public Medicine(Integer ID,String MName,Integer Price,Integer Stock,String Expiry,String Description) {
            this.ID = new SimpleIntegerProperty(ID);
            this.MName = new SimpleStringProperty(MName);
            this.Price = new SimpleIntegerProperty(Price);
            this.Stock= new SimpleIntegerProperty(Stock);
            this.Expiry = new SimpleStringProperty(Expiry);
            this.Description = new SimpleStringProperty(Description);
        }
        public int getID() {
            return ID.get();
        }
 
        public void setID(int id1) {
            ID.set(id1);
        }
 
        public String getMName() {
            return MName.get();
        }
 
        public void setMName(String mn1) {
            MName.set(mn1);
        }
 
        public int getPrice() {
            return Price.get();
        }
 
        public void setPrice(int pr1) {
            Price.set(pr1);
        }
        public int getStock() {
            return Stock.get();
        }
 
        public void setStock(int stc1) {
            Stock.set(stc1);
        }
        public String getExpiry() {
            return Expiry.get();
        }
 
        public void setExpiry(String ex1) {
            Expiry.set(ex1);
        }
        public String getDescription() {
            return Description.get();
        }
 
        public void setDescription(String des1) {
            Description.set(des1);
        }
    }
}
