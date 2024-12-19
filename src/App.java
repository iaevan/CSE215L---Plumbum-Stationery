import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/plmbm.png")));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("shopViewCtl.fxml"));
        Parent root = loader.load();

        shopViewCtl shopViewController = loader.getController();

        shopViewController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Plumbum Stationery");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}