package controllers.Admin.Create;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.io.*;
import java.util.Objects;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateController {
    @FXML
    MFXTextField jname, jprice, jstock;

    @FXML
    Label error_label;

    private File selectedFile = null;

//    public void AdminCreateProduct() {
//        if (jname.getText().isBlank() || jprice.getText().isBlank() || jstock.getText().isBlank()) {
//            error_label.setText("No puede haber campos vacíos.");
//        } else {
//
//            try {
//
//                if (selectedFile != null) {
//                    DB con = new DB();
//                    Boolean createProduct = con.CreateProduct(jname.getText(), Float.parseFloat(jprice.getText()), Integer.parseInt(jstock.getText()), fis);
//                    if (createProduct) {
//                        error_label.setText("Creado con éxito");
//                    } else {
//                        error_label.setText("Error al crear producto.");
//                    }
//                } else {
//                    error_label.setText("Error al cargar la imagen.");
//                }
//            } catch (Exception e) {
//                error_label.setText("Error al procesar la imagen.");
//                e.printStackTrace();
//            } finally {
//                if (selectedFile != null) {
//                    try {
//                        selectedFile.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }

//    public FileInputStream UploadImageAction() {
//        JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File f = chooser.getSelectedFile();
//        if (f != null) {
//            try {
//                return new FileInputStream(f);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//        }
//        return null;
//    }

//    @FXML
//    public void UploadImageAction() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Seleccionar Imagen");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif"),
//                new FileChooser.ExtensionFilter("Todos los Archivos", "*.*")
//        );
//        Stage stage = new Stage();
//        selectedFile = fileChooser.showOpenDialog(stage);
//
//        if (selectedFile != null) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Archivo");
//            alert.setHeaderText(null);
//            alert.setContentText("Exito: Archivo seleccionado.");
//            alert.showAndWait();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Archivo");
//            alert.setHeaderText(null);
//            alert.setContentText("Error: Archivo no seleccionado.");
//            alert.showAndWait();
//        }
//
//    }

//    @FXML
//    public void AdminCreateProduct() {
//        if (jname.getText().isBlank() || jprice.getText().isBlank() || jstock.getText().isBlank()) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Crear producto");
//            alert.setHeaderText(null);
//            alert.setContentText("Error: No puede haber campos vacios.");
//            alert.showAndWait();
//        } else {
//            try (FileInputStream fis = selectedFile != null ? new FileInputStream(selectedFile) : null) {
//                if (fis != null) {
//                    DB con = new DB();
//                    boolean createProduct = con.CreateProduct(jname.getText(),
//                            Float.parseFloat(jprice.getText()),
//                            Integer.parseInt(jstock.getText()),
//                            fis);
//                    if (createProduct) {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Crear producto");
//                        alert.setHeaderText(null);
//                        alert.setContentText("EXito: Producto Creado.");
//                        alert.showAndWait();
//                    } else {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Crear producto");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Error: Error al crear el producto.");
//                        alert.showAndWait();
//                    }
//                } else {
//                    error_label.setText("Error al cargar la imagen.");
//                }
//            } catch (FileNotFoundException e) {
//                error_label.setText("Archivo no encontrado.");
//                e.printStackTrace();
//            } catch (IOException e) {
//                error_label.setText("Error al procesar la imagen.");
//                e.printStackTrace();
//            } catch (Exception e) {
//                error_label.setText("Error al crear producto.");
//                e.printStackTrace();
//            }
//        }
//    }

//    public void GoProduct(MouseEvent mouseEvent) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Admin/AHome.fxml")));
//        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
}

