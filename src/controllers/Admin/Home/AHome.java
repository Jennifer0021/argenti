package controllers.Admin.Home;

import DAO.ProductObject;
import controllers.Admin.Edit.EditController;
import controllers.User.Cart.CartCardController;
import controllers.User.Cart.CartController;
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
import models.DB;
import java.io.IOException;
import java.util.List;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class AHome implements  Initializable{
    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProducts();
    }

    private void loadProducts() {
        DB conn = new DB();

        List<ProductObject> products = conn.GetProducts();

        int column = 0;
        int row = 1;
        for (ProductObject product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Admin/ProductCard.fxml"));
                VBox cardbox = loader.load();
                ProductCard productController = loader.getController();
                productController.setData(product);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                gridPane.add(cardbox, column++, row);
                GridPane.setMargin(cardbox, new Insets(15));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void GoCreate(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ACreate.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void GoEdit(MouseEvent event) throws IOException {
    }

    @FXML
    void GoLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ALogin.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void GoMenu(MouseEvent event) {

    }

    @FXML
    void GoProduct(MouseEvent event) {

    }


}
