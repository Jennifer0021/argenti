package controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.*;


import models.DB;

public class ACreate {
    @FXML
    MFXTextField jname, jprice, jstock;

    @FXML
    Label error_label;

    public void AdminCreateProduct() {
        if (jname.getText().isBlank() || jprice.getText().isBlank() || jstock.getText().isBlank()) {
            error_label.setText("No puede haber campos vacíos.");
        } else {
            FileInputStream fis = null;
            try {
                fis = UploadImageAction();
                if (fis != null) {
                    DB con = new DB();
                    Boolean createProduct = con.CreateProduct(jname.getText(), Float.parseFloat(jprice.getText()), Integer.parseInt(jstock.getText()), fis);
                    if (createProduct) {
                        error_label.setText("Creado con éxito");
                    } else {
                        error_label.setText("Error al crear producto.");
                    }
                } else {
                    error_label.setText("Error al cargar la imagen.");
                }
            } catch (Exception e) {
                error_label.setText("Error al procesar la imagen.");
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public FileInputStream UploadImageAction() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        if (f != null) {
            try {
                return new FileInputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
