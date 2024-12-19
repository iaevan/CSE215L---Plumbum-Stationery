import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class posCtl {

    @FXML
    private Label loggedInAsLabel;
    @FXML
    private ComboBox<Products> productDropdown;
    @FXML
    private TextField quantityInput;
    @FXML
    private Button addOrderButton;
    @FXML
    private TableView<OrderItem> orderTable;
    @FXML
    private TableColumn<OrderItem, String> productNameColumn;
    @FXML
    private TableColumn<OrderItem, Double> priceColumn;
    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;
    @FXML
    private Label totalAmountLabel;
    @FXML
    private Button goBackButton;
    @FXML
    private Button completeOrderButton;

    private Stage primaryStage;
    private Cart cart;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {

        loggedInAsLabel.setText("Logged in as: Salesperson");

        productDropdown.setItems(FXCollections.observableArrayList(DataStorage.products));

        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        cart = new Cart(100);
    }

    @FXML
    public void handleAddOrder(ActionEvent event) {

        Products selectedProduct = productDropdown.getValue();
        int quantity = Integer.parseInt(quantityInput.getText());

        OrderItem orderItem = new OrderItem(selectedProduct, quantity);

        cart.addItem(orderItem);

        orderTable.setItems(FXCollections.observableArrayList(cart.getItems()));

        double total = calculateTotalAmount();
        totalAmountLabel.setText("Total Amount: BDT " + total);
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
    public void handleCompleteOrder(ActionEvent event) {

        double totalAmount = calculateTotalAmount();
        Customer customer = new Customer("default_customer", "password");
        Order order = new Order(customer, cart.getItems(), totalAmount);

        for (OrderItem item : cart.getItems()) {
            if (item != null) {
                DataStorage.updateStock(item.getProduct(), -item.getQuantity());
            }
        }

        DataStorage.addOrder(order);

    }

    private double calculateTotalAmount() {
        double total = 0;
        for (OrderItem item : cart.getItems()) {
            if (item != null && item.getProduct() != null) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        return total;
    }
}