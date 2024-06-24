package controllers.Admin.Home;

import DAO.ProductObject;
import controllers.Admin.Edit.EditController;
import controllers.User.Cart.CartController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DB;

public class ProductCard {

    @FXML
    private ImageView jimg;

    @FXML
    private Label jname;

    @FXML
    private Label jprice;

    @FXML
    private Label jstock;

    @FXML
    private MFXButton editButton;

    @FXML
    private MFXButton deleteButton;

    @FXML
    private VBox pBox;

    private int userId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int productId;

    public void setData(ProductObject product){
        Image image = new Image(new ByteArrayInputStream(product.getImage()));
        jimg.setImage(image);
        jimg.setFitWidth(200);
        jimg.setFitHeight(150);
        jname.setText("Nombre: " + product.getName());
        jprice.setText("Precio: " + String.valueOf(product.getPrice()));
        jstock.setText("Stock: " + String.valueOf(product.getStock()));
        deleteButton.setOnAction(event -> DeleteProduct(product.getId()));
        pBox.setStyle("-fx-background-color: #90a4ae; -fx-background-radius: 20px;");
        this.productId = product.getId();
    }

    private void DeleteProduct(int productId) {
        DB conn = new DB();
        Boolean added = conn.DeleteProduct(productId);
        if (added){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminar producto");
            alert.setHeaderText(null);
            alert.setContentText("Eliminado con exito!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminar producto");
            alert.setHeaderText(null);
            alert.setContentText("Error al eliminar.");
            alert.showAndWait();
        }
    }
    public void EditProductAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AEdit.fxml"));
        Parent root = loader.load();

        EditController controller = loader.getController();
        controller.setProductId(productId);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
