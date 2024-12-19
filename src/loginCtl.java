import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginCtl {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button goBackButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = authenticateUser(username, password);

        if (user != null) {

            if (user instanceof Admin) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("stockCtl.fxml"));
                    Parent root = loader.load();

                    stockCtl stockController = loader.getController();
                    stockController.setPrimaryStage(primaryStage);

                    Scene stockScene = new Scene(root);
                    primaryStage.setScene(stockScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (user instanceof Salesperson) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("posCtl.fxml"));
                    Parent root = loader.load();

                    posCtl posController = loader.getController();
                    posController.setPrimaryStage(primaryStage);

                    Scene posScene = new Scene(root);
                    primaryStage.setScene(posScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {

            System.out.println("Invalid username or password.");
        }
    }

    @FXML
    public void handleGoBack(ActionEvent event) {
        try {
            shopViewCtl shopViewController = new shopViewCtl();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shopView.fxml"));
            loader.setController(shopViewController);
            Scene shopScene = new Scene(loader.load());
            shopViewController.setPrimaryStage(primaryStage);
            primaryStage.setScene(shopScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User authenticateUser(String username, String password) {
        for (int i = 0; i < DataStorage.usersSize; i++) {
            User user = DataStorage.users[i];
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}