package controllers.User.Profile;

import DAO.UserObject;
import controllers.User.Cart.CartController;
import controllers.User.Home.HomeController;
import controllers.User.WishList.WishListController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.DB;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;

public class ProfileController {
    @FXML
    private MFXTextField jname;

    @FXML
    private MFXTextField jlastname;

    @FXML
    private MFXTextField jemail;

    @FXML
    private MFXTextField jpassword;

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;

        loadProducts();
    }

    private void loadProducts() {
        DB conn = new DB();

        UserObject user = conn.GetUserProfile(this.userId);
        jname.setText(user.getName());
        jlastname.setText(user.getLastname());
        jemail.setText(user.getEmail());
    }

    @FXML
    void UpdateAction(ActionEvent event) {
        DB con = new DB();

        if (jname.getText().isBlank() || jlastname.getText().isBlank() ||
                jemail.getText().isBlank() || jpassword.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Perfil");
            alert.setHeaderText(null);
            alert.setContentText("Error: No puede haber campos vacios.");
            alert.showAndWait();
        } else {
            Boolean isCreated = con.UpdateProfile(this.userId, jname.getText(), jlastname.getText(), jemail.getText(), jpassword.getText());
            if (isCreated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Perfil");
                alert.setHeaderText(null);
                alert.setContentText("EXito: Usuario actualizado.");
                alert.showAndWait();

                jname.setText("");
                jlastname.setText("");
                jemail.setText("");
                jpassword.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Perfil");
                alert.setHeaderText(null);
                alert.setContentText("Error: Usuario no actualizado.");
                alert.showAndWait();
            }
        }
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

        controllers.User.History.HistoryController cartController = loader.getController();
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