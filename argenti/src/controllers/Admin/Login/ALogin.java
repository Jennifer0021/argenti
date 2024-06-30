package controllers.Admin.Login;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class ALogin {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private MFXTextField aemail;

    @FXML
    private MFXTextField apassword;

    @FXML
    private Label error_label;

    public void AdminLoginAction(ActionEvent event) throws IOException {

//        if (aemail.getText().isBlank() || apassword.getText().isBlank()) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Login");
//            alert.setHeaderText(null);
//            alert.setContentText("Error: No puede haber campos vacios.");
//            alert.showAndWait();
//        } else {
//
//            if (login){
//                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Admin/AHome.fxml")));
//                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//
//            }else {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Login");
//                alert.setHeaderText(null);
//                alert.setContentText("Error: Datos incorrectos.");
//                alert.showAndWait();
//            }
//        }
    }

    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
