package controller;
import javafx.event.Event;
import javafx.stage.Stage;

public class HomeController {

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
}
