package controllers.User.Home;

import DAO.ProductObject;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.ByteArrayInputStream;

import javafx.scene.layout.VBox;
import models.DB;

public class HomeCardController {

    @FXML
    private ImageView jimg;

    @FXML
    private Label jname;

    @FXML
    private Label jprice;

    @FXML
    private Label jstock;

    @FXML
    private MFXButton jaddCart;

    @FXML
    private MFXButton jaddWish;

    @FXML
    private VBox pBox;

    private int userId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setData(ProductObject product){
        Image image = new Image(new ByteArrayInputStream(product.getImage()));
        jimg.setImage(image);
        jimg.setFitWidth(200);
        jimg.setFitHeight(150);
        jname.setText("Nombre: " + product.getName());
        jprice.setText("Precio: " + String.valueOf(product.getPrice()));
        jstock.setText("Stock: " + String.valueOf(product.getStock()));
        jaddCart.setOnAction(event -> addToCart(product.getId()));
        jaddWish.setOnAction(event -> addToWishlist(product.getId()));
        pBox.setStyle("-fx-background-color: #90a4ae; -fx-background-radius: 20px;");
    }

    private void addToCart(int productId) {
        DB conn = new DB();
        Boolean added = conn.AddToCart(getUserId(), productId);
        if (added){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agregar al carrito");
            alert.setHeaderText(null);
            alert.setContentText("Agregado con exito!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agregar al carrito");
            alert.setHeaderText(null);
            alert.setContentText("Error al agregar.");
            alert.showAndWait();
        }
    }

    private void addToWishlist(int productId) {
        DB conn = new DB();
        Boolean added = conn.AddToWishList(getUserId(), productId);
        if (added){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agregar a lista de deseos");
            alert.setHeaderText(null);
            alert.setContentText("Agregado con exito!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agregar a lista de deseos");
            alert.setHeaderText(null);
            alert.setContentText("Error al agregar.");
            alert.showAndWait();
        }
    }
}
