package model;

import controller.Main;
import javafx.scene.control.Button;

public class Item {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private Button buttonInfo;

    public Item () {
        this.buttonInfo = new Button("Visualizar");
        this.buttonInfo.setOnAction(event -> {
            Main.setSelectedItem(id);
            Main.changeScreen("itemInfo");
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Button getButtonInfo() {
        return this.buttonInfo;
    }
}
