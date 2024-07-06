package controllers.User.Home;

import controllers.User.Cart.CartController;
import controllers.User.History.HistoryController;
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

import java.io.IOException;
import java.util.List;

import javafx.stage.Stage;
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

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
        loadProducts();
    }

    private void loadProducts() {
//        DB conn = new DB();
//
//        List<ProductObject> products = conn.GetProducts();
//
//
//        int column = 0;
//        int row = 1;
//        for (ProductObject product : products) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/Product.fxml"));
//                VBox cardbox = loader.load();
//                HomeCardController productController = loader.getController();
//                productController.setData(product);
//                productController.setUserId(userId);
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

    public void GoToCart(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/CartView.fxml"));
        Parent root = loader.load();

        CartController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoToWishList(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/WishList.fxml"));
        Parent root = loader.load();

        WishListController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoLogin(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoToProfile(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Profile/Profile.fxml"));
        Parent root = loader.load();

        ProfileController cartController = loader.getController();
        cartController.setUserId(this.userId);

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoToHistory(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/History/History.fxml"));
        Parent root = loader.load();

        HistoryController controller = loader.getController();
        controller.setUserId(this.userId);

        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
