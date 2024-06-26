package controllers.User;

import DAO.CartObject;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.DB;

import java.io.ByteArrayInputStream;

public class WishListCardController {

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

    @FXML
    private MFXButton jaddCart;

    @FXML
    private MFXButton jaddWish;

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
//        jaddCart.setOnAction(event -> addToCart(product.getUid()));
//        jaddWish.setOnAction(event -> addToWishlist(product.getUid()));
    }

    private void addToCart(int productId) {
        System.out.println("Agregar al carrito - ID del producto: " + productId);
        System.out.println("Agregar al carrito - ID del usuario: " + getUserId());
        DB conn = new DB();
        Boolean added = conn.AddToCart(getUserId(), productId);
        if (added){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agregar al carrito");
            alert.setHeaderText(null); // Sin encabezado
            alert.setContentText("Agregado con exito!");

            // Mostrar el diálogo y esperar a que el usuario presione OK
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agregar al carrito");
            alert.setHeaderText(null); // Sin encabezado
            alert.setContentText("Error al agregar.");

            // Mostrar el diálogo y esperar a que el usuario presione OK
            alert.showAndWait();
        }

    }

    private void addToWishlist(int productId) {
        System.out.println("Agregar a wishlist - ID del producto: " + productId);
        System.out.println("Agregar a wishlist - ID del usuario: " + getUserId());
        DB conn = new DB();
        Boolean added = conn.AddToWishList(getUserId(), productId);
        if (added){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agregar al carrito");
            alert.setHeaderText(null); // Sin encabezado
            alert.setContentText("Agregado con exito!");

            // Mostrar el diálogo y esperar a que el usuario presione OK
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agregar al carrito");
            alert.setHeaderText(null); // Sin encabezado
            alert.setContentText("Error al agregar.");

            // Mostrar el diálogo y esperar a que el usuario presione OK
            alert.showAndWait();
        }
    }


    public void BuyProduct() {
        System.out.println("nothin");

    }


    public void DeleteFromCart() {
        System.out.println("nothin");

    }




}

