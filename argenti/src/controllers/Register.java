package controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.DB;
import java.io.IOException;
import java.util.Objects;

public class Register {
    @FXML
    MFXTextField uname, ulastname, uemail, upassword;

    @FXML
    MFXButton uregister, go_login_btn;

    @FXML
    Label error_label;

    public void RegisterAction(ActionEvent event) throws IOException {
        DB con = new DB();

        if (uname.getText().isBlank() || ulastname.getText().isBlank() || uemail.getText().isBlank() || upassword.getText().isBlank()) {
            error_label.setText("Error: No puede haber campos vacios.");
        } else {
            Boolean exists = con.ISRegistered(uemail.getText());
            if (exists){
                error_label.setText("Error: Email en uso.");
            } else {
                Boolean isCreated = con.CreateUser(uname.getText(), ulastname.getText(), uemail.getText(), upassword.getText());
                if (isCreated){
                    error_label.setText("Exito!: Usuario creado.");
                    uname.setText("");
                    ulastname.setText("");
                    uemail.setText("");
                    upassword.setText("");
                } else {
                    error_label.setText("Error: Usuario no creado.");
                }
            }
        }
    }

    public void GoLoginAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Login.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
