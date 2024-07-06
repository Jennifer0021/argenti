package controllers.User.Register;

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
import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RegisterController {
    public MFXButton exitButton;
    @FXML
    MFXTextField uname, ulastname, uemail, upassword;

    @FXML
    MFXButton uregister, go_login_btn;

    @FXML
    Label error_label;

    public void RegisterAction(ActionEvent event) throws IOException {
//        DB con = new DB();
//
//        if (uname.getText().isBlank() || ulastname.getText().isBlank() || uemail.getText().isBlank() || upassword.getText().isBlank()) {
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Registro");
//            alert.setHeaderText(null);
//            alert.setContentText("Error: No puede haber campos vacios.");
//            alert.showAndWait();
//        } else {
//            Boolean exists = con.ISRegistered(uemail.getText());
//            if (exists){
//                Alert alert = new Alert(AlertType.INFORMATION);
//                alert.setTitle("Registro");
//                alert.setHeaderText(null);
//                alert.setContentText("Error: Email en uso.");
//                alert.showAndWait();
//            } else {
//                Boolean isCreated = con.CreateUser(uname.getText(), ulastname.getText(), uemail.getText(), upassword.getText());
//                if (isCreated){
//                    Alert alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Registro");
//                    alert.setHeaderText(null);
//                    alert.setContentText("EXito: Usuario creado.");
//                    alert.showAndWait();
//
//                    uname.setText("");
//                    ulastname.setText("");
//                    uemail.setText("");
//                    upassword.setText("");
//                } else {
//                    Alert alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Registro");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Error: Usuario no creado.");
//                    alert.showAndWait();
//                }
//            }
//        }
    }

    public void GoLoginAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/User/Login.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ExitAction(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
