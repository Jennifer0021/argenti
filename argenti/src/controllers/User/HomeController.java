package controllers.User;

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

import java.io.IOException;
import java.util.List;

import javafx.stage.Stage;
import models.DB;
public class HomeController {
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
        List<ProductObject> products = conn.GetProducts();

        // Mostrar cada producto en la interfaz gráfica
        int column = 0;
        int row = 1;
        for (ProductObject product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Product.fxml"));
                VBox cardbox = loader.load();
                ProductController productController = loader.getController();
                productController.setData(product);
                productController.setUserId(userId); // Configurar userId en ProductController

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

//    @FXML
//    private GridPane gridPane;
//
//    private int id;
//    public int getId() {
//        System.out.println("Home Recieved GETTER id:" +  this.id);
//        return this.id;
//
//    }
//
//    public void setId(int id) {
//        this.id = id;
//        System.out.println("Home Recieved id:" +  id);
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        DB conn = new DB();
//
//        // Obtener la lista de productos desde la base de datos
//        List<ProductObject> products = conn.GetProducts();
//
//        // Mostrar cada producto en la interfaz gráfica
//        int column = 0;
//        int row = 1;
//        for (ProductObject product : products) {
//
//            try {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/views/Product.fxml"));
//                VBox cardbox = loader.load();
//                Product productController = loader.getController();
//                productController.setData(product);
//
//                if (column == 3){
//                    column = 0;
//                    ++row;
//                }
//
//                gridPane.add(cardbox, column++, row);
//                GridPane.setMargin(cardbox, new Insets(20));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }



}
