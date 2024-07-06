package controllers.User.History;

import controllers.User.Cart.CartController;
import controllers.User.Home.HomeController;
import controllers.User.Profile.ProfileController;
import controllers.User.WishList.WishListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HistoryController {
    @FXML
    private GridPane gridPane;
    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;

        loadProducts();
    }

    private void loadProducts() {
//        DB conn = new DB();
//
//        List<HistoryObject> products = conn.GetHistory(this.userId);
//
//        int column = 0;
//        int row = 1;
//        for (HistoryObject product : products) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/History/HistoryCard.fxml"));
//                VBox cardbox = loader.load();
//                HistoryCard cartCardController = loader.getController();
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

    @FXML
    void GoCart(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/CartView.fxml"));
        Parent root = loader.load();

        CartController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void GoHistory(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/History/History.fxml"));
        Parent root = loader.load();

        HistoryController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void GoLogin(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void GoProducts(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/Home.fxml"));
        Parent root = loader.load();

        HomeController homeController = loader.getController();
        homeController.setUserId(this.userId);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void GoProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Profile/Profile.fxml"));
        Parent root = loader.load();

        ProfileController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void GoWishList(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/WishList.fxml"));
        Parent root = loader.load();

        WishListController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
