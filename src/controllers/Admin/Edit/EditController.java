package controllers.Admin.Edit;

import DAO.ProductObject;
import DAO.UserObject;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.DB;

import java.io.*;
import java.util.Objects;

public class EditController {

    @FXML
    private MFXTextField pprice;

    @FXML
    private MFXTextField pname;

    @FXML
    private MFXTextField pstock;

    @FXML
    private MFXButton pimage;

    private File selectedFile = null;

    private byte[] existingImage;

    private int productId;

    public void setProductId(int productId) {
        System.out.println("Edit controller product id: " + productId);
        this.productId = productId;
        loadProducts();
    }

    private void loadProducts() {
        DB conn = new DB();

        ProductObject product = conn.GetProductId(this.productId);

        pname.setText(product.getName());
        pprice.setText(String.valueOf(product.getPrice()));
        pstock.setText(String.valueOf(product.getStock()));
        this.existingImage = product.getImage();
    }

    @FXML
    void EditProductAction(ActionEvent event) {
        if (pname.getText().isBlank() || pprice.getText().isBlank() || pstock.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editar producto");
            alert.setHeaderText(null);
            alert.setContentText("Error: No puede haber campos vacios.");
            alert.showAndWait();
        } else {
            try (InputStream is = selectedFile != null ? new FileInputStream(selectedFile) :
                    (existingImage != null ? new ByteArrayInputStream(existingImage) : null)) {
                if (is != null) {
                    DB con = new DB();
                    boolean createProduct = con.EditProduct(pname.getText(),
                            Float.parseFloat(pprice.getText()),
                            Integer.parseInt(pstock.getText()),
                            is, this.productId);
                    if (createProduct) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Editar producto");
                        alert.setHeaderText(null);
                        alert.setContentText("Éxito: Producto editado.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Editar producto");
                        alert.setHeaderText(null);
                        alert.setContentText("Error: Error al editar el producto.");
                        alert.showAndWait();
                    }
                } else {
                    System.out.println("Error al cargar la imagen");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void UploadImageAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Todos los Archivos", "*.*")
        );
        Stage stage = new Stage();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Archivo");
            alert.setHeaderText(null);
            alert.setContentText("Exito: Archivo seleccionado.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Archivo");
            alert.setHeaderText(null);
            alert.setContentText("Error: Archivo no seleccionado.");
            alert.showAndWait();
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
    void GoEdit(MouseEvent event) {

    }

    @FXML
    void GoLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/ALogin.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void GoProduct(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Admin/AHome.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

