package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Database;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    Item item = new Item();

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    @FXML
    private TextField description;

    Database db = new Database();
    Connection con = db.connection();

    public void exit(Event e) {
        System.exit(0);
    }

    public void homePage(){
        Main.changeScreen("home");
    }

    public void inventoryPage() {
        Main.changeScreen("inventory");
    }

    public void helpPage() {
        Main.changeScreen("help");
    }

    public void aboutPage() {
        Main.changeScreen("about");
    }

    public void logout() {
        Main.changeScreen("login");
    }

    public void newItem(Event e) {
        Main.changeScreen("newItem");
    }

    public void minimize() {
        Stage stage = Main.getStage();
        stage.setIconified(true);
    }

    public void createItem(Event e) {
        int itemId = this.lastId();
        item.setId(itemId);
        item.setName(name.getText());
        item.setDescription(description.getText());
        item.setQuantity(Integer.parseInt(quantity.getText()));
        item.setPrice(Float.parseFloat(price.getText()));

        if(this.insertItem(item)) {
            name.setText("");
            description.setText("");
            quantity.setText("");
            price.setText("");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Item cadastrado com sucesso.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi possível cadastrar o item.");
            alert.show();
        }

        this.closeConnection();
    }

    public boolean insertItem(Item item) {
        String sql = "INSERT INTO item(id, name, description, quantity, price)"
                + " VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDescription());
            ps.setInt(4, item.getQuantity());
            ps.setFloat(5, item.getPrice());
            ps.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAllItems() {
        String sql = "DELETE FROM item";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteItem(int id) {
        String sql = "DELETE FROM item WHERE id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateItem(int id, String name, String description, int quantity, float price) {
        String sql = "UPDATE item SET name=?, description=?, quantity=?, price=? WHERE id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, quantity);
            ps.setFloat(4, price);
            ps.setInt(5, id);
            ps.executeUpdate();

            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Item> listItem() {
        String sql = "SELECT * FROM item";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Item> list = new ArrayList<>();

            if(rs == null) {
                System.out.println("A tabela está vazia.");
            } else {
                while(rs.next()){
                    Item item = new Item();
                    item.setId(Integer.parseInt(rs.getString("id")));
                    item.setName(rs.getString("name"));
                    item.setDescription(rs.getString("description"));
                    item.setQuantity(Integer.parseInt(rs.getString("quantity")));
                    item.setPrice(Float.parseFloat(rs.getString("price")));

                    list.add(item);
                }

                return list;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Item itemById(int id) {
        String sql = "SELECT * FROM item Where id = "+ id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs != null) {
                Item item = new Item();
                item.setId(Integer.parseInt(rs.getString("id")));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setQuantity(Integer.parseInt(rs.getString("quantity")));
                item.setPrice(Float.parseFloat(rs.getString("price")));

                return item;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int lastId() {
        String sql = "SELECT id FROM item";
        int returnValue = 0;

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            List<Item> list = new ArrayList<>();

            if(rs != null) {
                while(rs.next()){
                    returnValue = (Integer.parseInt(rs.getString("id"))) + 1;
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
