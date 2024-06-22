package controllers.Admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AHome {
    public void GoToCreate(MouseEvent event) throws IOException {
        System.out.println("nothin");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Admin/ACreate.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoToEdit(){
        System.out.println("nothin");
    }
    public void GoToProduct(){
        System.out.println("nothin");
    }

    public void Exit(){
        System.out.println("nothin");
    }
}
