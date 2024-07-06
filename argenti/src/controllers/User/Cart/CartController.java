package controllers.User.Cart;


import controllers.User.History.HistoryController;
import controllers.User.Home.HomeController;
import controllers.User.Profile.ProfileController;
import controllers.User.WishList.WishListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CartController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Label productLabel;

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
        System.out.println("Cart Recieved userId: " + userId);

        // Llama a un método para cargar los productos una vez que tengas el userId
        loadProducts();
    }

    private void loadProducts() {
//        DB conn = new DB();
//
//        // Obtener la lista de productos desde la base de datos con el userId
//        List<CartObject> products = conn.GetCart(this.userId);
//
//        // Mostrar cada producto en la interfaz gráfica
//        int column = 0;
//        int row = 1;
//        for (CartObject product : products) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/CartProduct.fxml"));
//                VBox cardbox = loader.load();
//                CartCardController cartCardController = loader.getController();
//                cartCardController.setData(product);
//                cartCardController.setUserId(userId); // Configurar userId en ProductController
//
//                if (column == 3) {
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
    }

    public void GProduct(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/Home.fxml"));
        Parent root = loader.load();

        HomeController homeController = loader.getController();
        homeController.setUserId(this.userId);

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoWishList(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/WishList.fxml"));
        Parent root = loader.load();

        WishListController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void GLogin(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoProfile(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Profile/Profile.fxml"));
        Parent root = loader.load();

        ProfileController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoHistory(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/History/History.fxml"));
        Parent root = loader.load();

        HistoryController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
