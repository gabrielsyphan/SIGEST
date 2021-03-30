package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Item;


public class EditItemController {
    Item item = new Item();

    @FXML
    private TextField nameData;

    @FXML
    private TextField priceData;

    @FXML
    private TextField quantityData;

    @FXML
    private TextField descriptionData;

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

    public void updateItem() {
        ItemController itemController = (new ItemController());
        Boolean item = itemController.updateItem(
                Main.getSelectedItem(),
                nameData.getText(),
                descriptionData.getText(),
                Integer.parseInt(quantityData.getText()),
                Float.parseFloat(priceData.getText())
        );

        if (item) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Item atualizadp com sucesso.");
            alert.showAndWait();
            Main.changeScreen("itemInfo");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi possível atualizar o item.");
            alert.show();
        }

        itemController.closeConnection();
    }
}
