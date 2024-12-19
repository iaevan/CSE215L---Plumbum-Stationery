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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class checkOutCtl {

    @FXML
    private TableView<OrderItem> cartTable;
    @FXML
    private TableColumn<OrderItem, String> itemNameColumn;
    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;
    @FXML
    private TableColumn<OrderItem, Double> priceColumn;
    @FXML
    private Label totalLabel;
    @FXML
    private TextField customerNameField;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button loginButton;

    private Stage primaryStage;
    private Cart cart;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @FXML
    public void initialize() {

        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        if (cart != null) {
            cartTable.setItems(FXCollections.observableArrayList(cart.getItems()));
        }

        double total = calculateTotalAmount();
        totalLabel.setText("Total: BDT " + total);
    }

    @FXML
    public void handlePlaceOrder(ActionEvent event) {

        String customerName = customerNameField.getText();

        double totalAmount = calculateTotalAmount();
        Customer customer = new Customer(customerName, "default_password");
        Order order = new Order(customer, cart.getItems(), totalAmount);

        for (OrderItem item : cart.getItems()) {
            DataStorage.updateStock(item.getProduct(), -item.getQuantity());
        }

        DataStorage.addOrder(order);

    }

    @FXML
    public void handleLoginButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginCtl.fxml"));
            Parent root = loader.load();

            loginCtl loginController = loader.getController();
            loginController.setPrimaryStage(primaryStage);

            Scene loginScene = new Scene(root);
            primaryStage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double calculateTotalAmount() {
        double total = 0;
        if (cart != null) {
            for (OrderItem item : cart.getItems()) {
                if (item != null && item.getProduct() != null) {
                    total += item.getProduct().getPrice() * item.getQuantity();
                }
            }
        }
        return total;
    }

    public void refreshCartTable() {
        if (cart != null) {
            cartTable.setItems(FXCollections.observableArrayList(cart.getItems()));
        }
    }
}