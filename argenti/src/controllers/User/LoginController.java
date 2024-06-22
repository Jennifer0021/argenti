package controllers.User;

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

import models.DB;

public class LoginController {
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
            error_label.setText("Error: No puede haber campos vacios.");
        } else {
            error_label.setText("Exito: No hay campos vacios.");
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
                error_label.setText("Error: Datos incorrectos.");
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
}
