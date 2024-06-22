package controllers.Admin;

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
import java.util.Objects;

import models.DB;
public class ALogin {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private MFXButton alogin_btn;
    @FXML
    private MFXTextField aemail;

    @FXML
    private MFXTextField apassword;

    @FXML
    private Label error_label;

    public void AdminLoginAction(ActionEvent event) throws IOException {
        DB con = new DB();

        if (aemail.getText().isBlank() || apassword.getText().isBlank()) {
            error_label.setText("Error: No puede haber campos vacios.");
        } else {
            error_label.setText("Exito: No hay campos vacios.");
            Boolean login = con.LoginAdmin(aemail.getText(), apassword.getText());
            if (login){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Admin/AHome.fxml")));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }else {
                error_label.setText("Error: Datos incorrectos.");
            }
        }
    }

}
