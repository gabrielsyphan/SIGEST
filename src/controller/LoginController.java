package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField email;

    @FXML
    private PasswordField psw;

    public void login(Event e) {
        if (email.getText().equals("admin") && psw.getText().equals("admin")) {
            Main.changeScreen("home");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esses dados não pertencem a um usuário.");
            alert.show();
        }
    }

    public void signUp(Event e) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Esse recurso ainda não está disponível.");
        alert.show();
    }

    public void exit(Event e) {
        System.exit(0);
    }
}
