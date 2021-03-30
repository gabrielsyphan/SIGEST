package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;

import java.util.List;

public class InventoryController {

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, String> nameCol;

    @FXML
    private TableColumn<Item, String> descriptionCol;

    @FXML
    private TableColumn<Item, Float> priceCol;

    @FXML
    private TableColumn<Item, Integer> quantityCol;

    @FXML
    private TableColumn<Item, Button> btnCol;

    Item item = new Item();
    ItemController itemController = new ItemController();

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

    public void tableItems() {
        List<Item> itemList = itemController.listItem();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        btnCol.setCellValueFactory(new PropertyValueFactory<>("buttonInfo"));
        table.setItems(FXCollections.observableList(itemList));
    }

    @FXML
    protected void initialize() {
        this.tableItems();
    }
}
