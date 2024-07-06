package controllers.User.WishList;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private MFXButton addCartButton;

    @FXML
    private MFXButton deleteButton;

    private int userId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public void setData(CartObject product){
//        Image image = new Image(new ByteArrayInputStream(product.getImage()));
//        jimg.setImage(image);
//        jimg.setFitWidth(200); // Ajusta el ancho de la imagen según sea necesario
//        jimg.setFitHeight(150); // Ajusta la altura de la imagen según sea necesario
//
//        jname.setText(product.getName());
//        jprice.setText(String.valueOf(product.getPrice()));
//        jstock.setText(String.valueOf(product.getStock()));
//        addCartButton.setOnAction(event -> AddToCart(product.getPid()));
//        deleteButton.setOnAction(event -> DeleteFromWishList(product.getPid()));
//    }

//    public void AddToCart(int productId) {
//        DB conn = new DB();
//        Boolean added = conn.AddToCart(getUserId(), productId);
//        if (added){
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Agregar al carrito");
//            alert.setHeaderText(null);
//            alert.setContentText("Agregado con exito!");
//            alert.showAndWait();
//        } else {
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Agregar al carrito");
//            alert.setHeaderText(null);
//            alert.setContentText("Error al agregar.");
//            alert.showAndWait();
//        }

//    }
//
//
//    public void DeleteFromWishList(int productId) {
//        DB conn = new DB();
//        Boolean deleted = conn.DeleteFromWishList(productId, getUserId() );
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Eliminar del carrito");
//        alert.setHeaderText(null);
//        if (deleted){
//            alert.setContentText("Eliminado con exito!");
//        } else {
//            alert.setContentText("Error al eliminar.");
//        }
//        alert.showAndWait();
//
//    }

}

