package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Database;
import model.Item;

import java.sql.Connection;

public class ItemInfoController {
    Item item = new Item();

    @FXML
    private Label nameData;

    @FXML
    private Label priceData;

    @FXML
    private Label quantityData;

    @FXML
    private Label descriptionData;

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

    public void minimize() {
        Stage stage = Main.getStage();
        stage.setIconified(true);
    }

    @FXML
    protected void initialize() {
        ItemController itemController = (new ItemController());
        Item item = itemController.itemById(Main.getSelectedItem());
        itemController.closeConnection();

        nameData.setText(item.getName());
        descriptionData.setText(item.getDescription());
        priceData.setText(""+ item.getPrice());
        quantityData.setText(""+ item.getQuantity());
    }

    public void deleteItem() {
        ItemController itemController = (new ItemController());
        Boolean item = itemController.deleteItem(Main.getSelectedItem());
        itemController.closeConnection();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("O item foi apagado.");
        alert.showAndWait();
        this.inventoryPage();
    }

    public void editItem() {
        Main.changeScreen("editItem");
    }
}
