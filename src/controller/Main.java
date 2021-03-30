package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage;
    private static double xOffset = 0;
    private static double yOffset = 0;
    private static int selectedItem;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(root);
        Image image = new Image("file:src/view/assets/images/icon.png");

        Main.stage = primaryStage;

        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Sistema gerenciador de estoque");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.show();

        dragWindow(root);
    }

    public static void changeScreen(String screen) {
        try {
            Parent parent = FXMLLoader.load(
                    Main.class.getResource("/view/"+ screen +".fxml")
            );

            Scene scene = new Scene(parent);

            if (screen == "login") {
                scene.setFill(Color.TRANSPARENT);
            }

            dragWindow(parent);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void dragWindow(Parent parent) {
        parent.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });

        parent.setOnMouseDragged(event -> {
            stage.setOpacity(0.8);
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

        parent.setOnMouseReleased(event -> {
            stage.setOpacity(1);
        });
    }

    public static void setSelectedItem(int id) {
        selectedItem = id;
    }

    public static int getSelectedItem() {
        return selectedItem;
    }
}
