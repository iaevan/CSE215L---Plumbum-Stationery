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

public class salesCtl {

    @FXML
    private Label loggedInAsLabel;
    @FXML
    private TableView<Sales> salesTable;
    @FXML
    private TableColumn<Sales, String> customerNameColumn;
    @FXML
    private TableColumn<Sales, Double> orderedAmountColumn;
    @FXML
    private TableColumn<Sales, String> dateColumn;
    @FXML
    private Button goBackButton;
    @FXML
    private Button checkStockButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {

        loggedInAsLabel.setText("Logged in as: Admin");

        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        orderedAmountColumn.setCellValueFactory(new PropertyValueFactory<>("orderedAmount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        salesTable.setItems(FXCollections.observableArrayList(DataStorage.sales));
    }

    @FXML
    public void handleGoBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shopViewCtl.fxml"));
            Parent root = loader.load();

            shopViewCtl shopViewController = loader.getController();
            shopViewController.setPrimaryStage(primaryStage);

            Scene shopScene = new Scene(root);
            primaryStage.setScene(shopScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCheckStock(ActionEvent event) {
        try {
            stockCtl stockController = new stockCtl();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stock.fxml"));
            loader.setController(stockController);
            Scene stockScene = new Scene(loader.load());
            stockController.setPrimaryStage(primaryStage);
            primaryStage.setScene(stockScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}