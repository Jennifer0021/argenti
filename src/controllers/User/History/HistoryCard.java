package controllers.User.History;

import DAO.HistoryObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.ByteArrayInputStream;
public class HistoryCard {

    @FXML
    private ImageView jimg;

    @FXML
    private Label jname;

    @FXML
    private Label jprice;

    @FXML
    private Label jbuyDate;

    private int userId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setData(HistoryObject product){
        Image image = new Image(new ByteArrayInputStream(product.getImage()));
        jimg.setImage(image);
        jimg.setFitWidth(200);
        jimg.setFitHeight(150);

        jname.setText(product.getName());
        jprice.setText(String.valueOf(product.getPrice()));
        jbuyDate.setText(String.valueOf(product.getBuyDate()));
    }
}