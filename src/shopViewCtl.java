import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class shopViewCtl {

    @FXML
    private TilePane productGrid;
    @FXML
    private Button cartButton;
    @FXML
    private Button loginButton;

    private Stage primaryStage;
    private Cart cart;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {

        cart = new Cart(100);

        Products[] products = DataStorage.getProducts();
        for (int i = 0; i < DataStorage.productsSize; i++) {
            VBox productBox = createProductBox(products[i], i + 1);
            productGrid.getChildren().add(productBox);
        }
    }

    private VBox createProductBox(Products product, int index) {
        System.out.println("Creating product box for " + product.getName());
        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label("BDT " + product.getPrice());
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        Button addToCartButton = new Button("Add to cart");

        addToCartButton.setOnAction(e -> handleAddToCart(product, quantityField, index));

        VBox productBox = new VBox(10, nameLabel, priceLabel, quantityField, addToCartButton);

        return productBox;
    }

    @FXML
    private void handleAddToCart(Products product, TextField quantityField, int index) {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            OrderItem orderItem = new OrderItem(product, quantity);
            cart.addItem(orderItem);

        } catch (NumberFormatException e) {

            System.err.println("Invalid quantity entered for product " + index);
        }
    }

    @FXML
    private void handleCartButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("checkOutCtl.fxml"));
            Parent root = loader.load();

            checkOutCtl checkoutController = loader.getController();
            checkoutController.setPrimaryStage(primaryStage);
            checkoutController.setCart(cart);
            checkoutController.refreshCartTable();

            Scene checkoutScene = new Scene(root);
            primaryStage.setScene(checkoutScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
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
}