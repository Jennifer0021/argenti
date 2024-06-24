package controllers.User.Login;

import controllers.User.Home.HomeController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import models.DB;

public class LoginController {
    public MFXButton exitButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private MFXButton login_btn;

    @FXML
    private MFXButton register_btn;

    @FXML
    private MFXTextField email_field;

    @FXML
    private MFXTextField password_field;

    @FXML
    private Label error_label;

    public void LoginAction(ActionEvent event) throws IOException {
        DB con = new DB();

        if (email_field.getText().isBlank() || password_field.getText().isBlank()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Error: No puede haber campos vacios.");
            alert.showAndWait();
        } else {
            Boolean login = con.LoginUser(email_field.getText(), password_field.getText());
            if (login){
                int id = con.GetUserId(email_field.getText(), password_field.getText());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/Home.fxml"));
                Parent root = loader.load();

                HomeController homeController = loader.getController();
                System.out.println("Login controller id:" + id);
                homeController.setUserId(id);


                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setHeaderText(null);
                alert.setContentText("Error: Datos incorrectos.");
                alert.showAndWait();
            }
        }
    }

    public void RegisterAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/User/Register.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExitAction(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
