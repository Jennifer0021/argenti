package controllers;

import Lists.Admin.Admin;
import Lists.Admin.AdminList;
import Lists.Product.Product;
import Lists.Product.ProductList;
import Lists.User.User;
import Lists.User.UserList;
import controllers.Admin.Edit.EditController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class GeneralController implements Initializable {
    private static GeneralController instance;
    public GridPane gridPane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Admin fields

    @FXML
    private MFXTextField aemail;

    @FXML
    private MFXTextField apassword;

    // User fields
    public MFXTextField email_field;
    public MFXPasswordField password_field;

    // Register fields
    public MFXTextField ulastname;
    public MFXTextField uemail;
    public MFXTextField uname;
    public MFXPasswordField upassword;

    // Admin create product fields
    @FXML
    MFXTextField jname, jprice, jstock;

    // Admin product card

    @FXML
    private ImageView pimage;

    @FXML
    Label pname, pprice, pstock;

    @FXML
    private VBox pBox;

    // List
    private AdminList adminList = new AdminList();
    private UserList userList = new UserList();
    private ProductList productList = new ProductList();

    // Singleton pattern implementation
    public GeneralController() {
        adminList.AddAdmin(new Admin("admin@gmail.com", "admin"));
        userList.AddUser(new User("user@gmail.com", "user", "username", "lastname"));
    }

    public static GeneralController getInstance() {
        if (instance == null) {
            instance = new GeneralController();
        }
        return instance;
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(GeneralController.getInstance());
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

//            // Actualizar la vista después de cargar la escena
//            if (fxmlFile.contains("AdminHome.fxml")) {
//                GeneralController.getInstance().updateProductListView();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mouseSwitchScene(MouseEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(GeneralController.getInstance());
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

//            // Actualizar la vista después de cargar la escena
//            if (fxmlFile.contains("AdminHome.fxml")) {
//                GeneralController.getInstance().updateProductListView();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("General controller logs:");

        System.out.println("Admin credentials \n");
        adminList.ListAdmin();

        System.out.println("User credentials \n");
        userList.ListUser();
    }

    // Alert function simplified

    private void AlertFunction(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    // User functions

    public void LoginAction(ActionEvent event) throws IOException {
        if (email_field.getText().isBlank() || password_field.getText().isBlank()) {
            AlertFunction("Login", "Los campos no pueden estar vacios");
        } else {
            User user = userList.FindUser(email_field.getText());

            if (user != null) {
                System.out.println("Usuario encontrado: " + user.getEmail());
            } else {
                AlertFunction("Login", "Usuario no encontrado");
            }

            boolean login = userList.LoginUser(email_field.getText(), password_field.getText());

            if (login){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/Home.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }else {
                AlertFunction("Login", "Datos incorrectos");
            }
        }
    }

    public void RegisterAction(ActionEvent event) {
        switchScene(event, "/views/User/Register.fxml");
    }

    public void GoAdminAction(ActionEvent event) {
        switchScene(event, "/views/Admin/ALogin.fxml");
    }

    // Admin Functions
    public void AdminLoginAction(ActionEvent event) throws IOException {
        if (aemail.getText().isBlank() || apassword.getText().isBlank()) {
            AlertFunction("Login", "Los campos no pueden estar vacios");
        } else {
            Admin user = adminList.FindAdmin(aemail.getText());

            if (user != null) {
                System.out.println("Usuario encontrado: " + user.getEmail());
            } else {
                AlertFunction("Login", "Usuario no encontrado");
            }

            boolean login = adminList.LoginAdmin(aemail.getText(), apassword.getText());

            if (login){
                switchScene(event, "/views/Admin/AHome.fxml");
                loadProducts();
            }else {
                AlertFunction("Login", "Datos incorrectos");
            }
        }
    }

    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    // REGISTER ACTIONS

    public void GoLoginAction(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/views/User/Login.fxml");
    }

    public void ExitAction(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

    public void RegisterUserAction(ActionEvent actionEvent) {
        if (uname.getText().isBlank() || ulastname.getText().isBlank() || uemail.getText().isBlank() || upassword.getText().isBlank()) {
            AlertFunction("Registro", "No puede haber campos vacios");
        } else {
            User exists = userList.FindUser(uemail.getText());
            if (exists != null){
                AlertFunction("Login", "Email en uso");
            } else {
                userList.AddUser(new User(uemail.getText(), upassword.getText(), uname.getText(), ulastname.getText()));
                AlertFunction("Registro", "Usuario creado");
            }
        }
    }

    // Admin actions

    public void GoMenu(MouseEvent mouseEvent) {
    }

    public void GoProduct(MouseEvent mouseEvent) {
        mouseSwitchScene(mouseEvent, "/views/Admin/AHome.fxml");
        loadProducts();
    }

    public void GoCreate(MouseEvent mouseEvent) {
        mouseSwitchScene(mouseEvent, "/views/Admin/ACreate.fxml");
    }

    public void GoLogin(MouseEvent mouseEvent) {
        mouseSwitchScene(mouseEvent, "/views/User/Login.fxml");
    }

    // Admin create product
    public void AdminCreateProduct(ActionEvent actionEvent) throws IOException {
        if (jname.getText().isBlank() || jprice.getText().isBlank() || jstock.getText().isBlank()) {
            AlertFunction("Error", "Campos vacios");
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile != null) {
                try {
                    Image image = new Image(new FileInputStream(selectedFile));
                    Product product = new Product(jname.getText(), Double.parseDouble(jprice.getText()),
                            Integer.parseInt(jstock.getText()), image);
                    productList.AddProduct(product);

                    jname.clear();  jprice.clear(); jstock.clear();

                    productList.listarProductos();

                    AlertFunction("Exito!", "Producto creado");
//                    updateProductListView();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public void AdminGoProduct(MouseEvent mouseEvent) {
        mouseSwitchScene(mouseEvent, "/views/Admin/AHome.fxml");
        loadProducts();
    }

    // Admin Home Card

    public MFXButton AdminDeleteProductButton, AdminEditProduct;

    private void loadProducts() {
        gridPane.getChildren().clear();

        List<Product> products = productList.getAllProducts();

        int column = 0;
        int row = 1;
        for (Product product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Admin/ProductCard.fxml"));
                loader.setController(GeneralController.getInstance());
                VBox cardbox = loader.load();
                setData(product);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                gridPane.add(cardbox, column++, row);
                GridPane.setMargin(cardbox, new Insets(10));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Admin product list or home page
        public void setData(Product product){
        pimage.setImage(product.getImagen());
        pimage.setFitWidth(200);
        pimage.setFitHeight(150);
        pname.setText("Nombre: " + product.getNombre());
        pprice.setText("Precio: " + product.getPrecio());
        pstock.setText("Stock: " + product.getStock());
        AdminDeleteProductButton.setOnAction(event -> AdminDeleteProduct(product.getNombre()));
        AdminEditProduct.setOnAction(event -> {
            try {
                AdminGoToEdit(event, product);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pBox.setStyle("-fx-background-color: #90a4ae; -fx-background-radius: 20px;");
    }

    // ADMIN EDIT LABEL ACTION


    private void AdminDeleteProduct(String productName) {
        productList.eliminar(productName);
        AlertFunction("Exito", "Producto eliminado");
        loadProducts();
    }
    public void AdminGoToEdit(ActionEvent actionEvent, Product product) throws IOException {
        switchScene(actionEvent, "/views/Admin/AEdit.fxml");
        LoadEditForm(product);
    }

    // ADMIN EDIT SCENE

    @FXML
    private MFXTextField editname, editprice, editstock;
    public MFXButton AdminEditImage, AdminEditProductButton;

    public void LoadEditForm(Product product){
        editname.setText(product.getNombre());
        editprice.setText(String.valueOf(product.getPrecio()));
        editstock.setText(String.valueOf(product.getStock()));
        AdminEditImage.setOnAction(event -> {
            Image newImage = null;
            try {
                newImage = AdminEditImageAction();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            // Aquí podrías manejar cómo asignar la nueva imagen al producto
            product.setImagen(newImage);
            // También podrías actualizar la vista con la nueva imagen
            // Por ejemplo, pimage.setImage(newImage);
        });
        AdminEditProductButton.setOnAction(event -> AdminEditProductAction(event, product));

    }

    public Image AdminEditImageAction() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        Image image = new Image(new FileInputStream(selectedFile));
        return image;
    }

    public void AdminEditProductAction(ActionEvent actionEvent, Product product) {
        if (editname.getText().isBlank() || editprice.getText().isBlank() || editstock.getText().isBlank()) {
            AlertFunction("Error", "Campos vacios");
        } else {
            productList.actualizar(editname.getText(), Double.parseDouble(editprice.getText()),
                    Integer.parseInt(editstock.getText()), product.getImagen());
            AlertFunction("Exito", "Producto editado");
        }
    }
}
