import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class stockCtl {

    @FXML
    private Label loggedInAsLabel;
    @FXML
    private TableView<Products> stockTable;
    @FXML
    private TableColumn<Products, String> itemNameColumn;
    @FXML
    private TableColumn<Products, Integer> stockLeftColumn;
    @FXML
    private TableColumn<Products, Double> priceColumn;
    @FXML
    private Button goBackButton;
    @FXML
    private Button checkSalesButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
        // 1. Set the loggedInAsLabel text (assuming the user is already authenticated)
        loggedInAsLabel.setText("Logged in as: Admin"); // You might need to get the actual username

        // 2. Initialize the stockTable columns
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockLeftColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // 3. Populate the stockTable with data from DataStorage.products
        stockTable.setItems(FXCollections.observableArrayList(DataStorage.products));
    }

    @FXML
    public void handleGoBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shopViewCtl.fxml"));
            Parent root = loader.load();
    
            shopViewCtl shopViewController = loader.getController();
            shopViewController.setPrimaryStage(primaryStage); // Set the primaryStage 
    
            Scene shopScene = new Scene(root);
            primaryStage.setScene(shopScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    public void handleCheckSales(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("salesCtl.fxml"));
            Parent root = loader.load();

            salesCtl salesController = loader.getController();
            salesController.setPrimaryStage(primaryStage); // Set primaryStage before showing the scene

            Scene salesScene = new Scene(root);
            primaryStage.setScene(salesScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}