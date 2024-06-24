package controllers.User.WishList;

import DAO.CartObject;

import controllers.User.Cart.CartController;
import controllers.User.History.HistoryController;
import controllers.User.Home.HomeController;
import controllers.User.Profile.ProfileController;
import controllers.User.WishList.WishListCardController;
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
import models.DB;

import java.io.IOException;
import java.util.List;

public class WishListController {
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


    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
        loadProducts();
    }

    private void loadProducts() {
        DB conn = new DB();

        List<CartObject> products = conn.GetWishList(this.userId);

        int column = 0;
        int row = 1;

        for (CartObject product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/WishListProduct.fxml"));
                VBox cardbox = loader.load();
                WishListCardController cartCardController = loader.getController();
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

    public void GLogin(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoCart(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/CartView.fxml"));
        Parent root = loader.load();

        CartController homeController = loader.getController();
        homeController.setUserId(this.userId);

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
}
