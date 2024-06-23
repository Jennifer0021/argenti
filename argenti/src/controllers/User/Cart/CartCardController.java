package controllers.User.Cart;

import DAO.CartObject;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.ByteArrayInputStream;
import models.DB;

public class CartCardController {

    @FXML
    private ImageView jimg;

    @FXML
    private Label jname;

    @FXML
    private Label jprice;

    @FXML
    private Label jstock;

    @FXML
    private MFXButton buyButton;

    @FXML
    private MFXButton deleteButton;

    private int userId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("ProductController recieved id: " +  userId);
    }

    public void setData(CartObject product){
        Image image = new Image(new ByteArrayInputStream(product.getImage()));
        jimg.setImage(image);
        jimg.setFitWidth(200); // Ajusta el ancho de la imagen según sea necesario
        jimg.setFitHeight(150); // Ajusta la altura de la imagen según sea necesario

        jname.setText(product.getName());
        jprice.setText(String.valueOf(product.getPrice()));
        jstock.setText(String.valueOf(product.getStock()));
        deleteButton.setOnAction(event -> DeleteFromCart(product.getPid()));
        buyButton.setOnAction(event -> BuyProduct(product.getPid()));
    }

    public void BuyProduct(int productId) {
        DB conn = new DB();
        Boolean deleted = conn.BuyProduct(productId, getUserId());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Comprar producto");
        alert.setHeaderText(null);
        if (deleted){
            alert.setContentText("Comprado, revise su historial!");
        } else {
            alert.setContentText("Error al comprar.");
        }
        alert.showAndWait();
        DeleteFromCart(productId);
    }

    public void DeleteFromCart(int productId) {
        DB conn = new DB();
        Boolean deleted = conn.DeleteFromCart(productId, getUserId() );
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Eliminar del carrito");
        alert.setHeaderText(null);
        if (deleted){
            alert.setContentText("Eliminado con exito!");
        } else {
            alert.setContentText("Error al eliminar.");
        }
        alert.showAndWait();
    }
}

