package controllers.User;

import DAO.CartObject;
import DAO.ProductObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DB;

import java.io.IOException;
import java.util.List;

public class CartController {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label cartLabel;

    @FXML
    private Label wishLabel;

    @FXML
    private Label historyLabel;

    @FXML
    private Label profileLabel;

    @FXML
    private Label exitLabel;


    private int userId; // Cambiado de 'id' a 'userId' para mayor claridad

    public void setUserId(int userId) {
        this.userId = userId;
        // Aquí puedes realizar cualquier lógica adicional que necesites con userId
        System.out.println("Home Recieved userId: " + userId);

        // Llama a un método para cargar los productos una vez que tengas el userId
        loadProducts();
    }

    private void loadProducts() {
        DB conn = new DB();

        // Obtener la lista de productos desde la base de datos con el userId
        List<CartObject> products = conn.GetCart(this.userId);

        // Mostrar cada producto en la interfaz gráfica
        int column = 0;
        int row = 1;
        for (CartObject product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Product.fxml"));
                VBox cardbox = loader.load();
                CartCardController cartCardController = loader.getController();
                cartCardController.setData(product);
                cartCardController.setUserId(userId); // Configurar userId en ProductController

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                gridPane.add(cardbox, column++, row);
                GridPane.setMargin(cardbox, new Insets(20));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void GoToCart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/CartView.fxml"));
        Parent root = loader.load();

        CartController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
