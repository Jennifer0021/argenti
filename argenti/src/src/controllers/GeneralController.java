package controllers;

import Lists.Admin.Admin;
import Lists.Admin.AdminList;
import Lists.History.HistoryProduct;
import Lists.History.HistoryProductList;
import Lists.Product.Product;
import Lists.Product.ProductList;
import Lists.Product.ShoppingCart;
import Lists.Product.WishList;
import Lists.User.User;
import Lists.User.UserList;
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
import java.time.LocalDate;
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
    private ShoppingCart cart = new ShoppingCart();
    private WishList wishList = new WishList();
    private HistoryProductList history = new HistoryProductList();
    //UID
    private String uid = "";
    // Singleton pattern implementation
    public GeneralController() {
        adminList.AddAdmin(new Admin("admin@gmail.com", "admin"));
        userList.addUser(new User("user@gmail.com", "user", "username", "lastname"));
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

    // USER LOGIN

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
                uid = userList.GetUid(email_field.getText());
                System.out.println("UID: " + uid);
                switchScene(event, "/views/User/Home.fxml");
                homeLoadProducts();

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

    // USER HOME
    @FXML
    private ImageView himage;
    @FXML
    private MFXButton jaddCart, jaddWish;
    @FXML
    private Label hname, hprice, hstock, profileLabel, exitLabel, historyLabel, wishLabel, cartLabel;
    private void homeLoadProducts() {
        gridPane.getChildren().clear();

        List<Product> products = productList.getAllProducts();

        int column = 0;
        int row = 1;
        for (Product product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/Product.fxml"));
                loader.setController(GeneralController.getInstance());
                VBox cardbox = loader.load();
                homeSetData(product);

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
    public void homeSetData(Product product){
        himage.setImage(product.getImagen());
        himage.setFitWidth(200);
        himage.setFitHeight(150);
        hname.setText("Nombre: " + product.getNombre());
        hprice.setText("Precio: " + product.getPrecio());
        hstock.setText("Stock: " + product.getStock());
        jaddCart.setOnAction(event -> addToCart(product));
        jaddWish.setOnAction(event -> addToWishlist(product));
    }
    private void addToCart(Product product) {
        cart.AddProduct(product);
        AlertFunction("Exito", "Agregado al carrito con exito.");
    }
    private void addToWishlist(Product product) {
        wishList.AddProduct(product);
        AlertFunction("Exito", "Agregado a la lista de deseos.");
    }
    public void HomeGoProduct(MouseEvent event){
        mouseSwitchScene(event, "/views/User/Home.fxml");
        homeLoadProducts();
    }
    public void GoToCart(MouseEvent event) throws IOException {
        mouseSwitchScene(event, "/views/User/CartView.fxml");
        CartLoadProducts();
    }
    public void GoToWishList(MouseEvent event) throws IOException {
        mouseSwitchScene(event, "/views/User/WishList.fxml");
        WishLoadProducts();
    }
    public void GoToProfile(MouseEvent event) throws IOException {
        mouseSwitchScene(event, "/views/User/Profile/Profile.fxml");
        User user = userList.GetByUid(uid);
        loadProfile(user);
    }
    public void GoToHistory(MouseEvent event) throws IOException {
        mouseSwitchScene(event, "/views/User/History/History.fxml");
        HistoryLoadProducts();
    }

    // USER CART
    @FXML
    private ImageView cartimage;
    @FXML
    private MFXButton CartBuyButton, CartDeleteButton;
    @FXML
    private Label cartname, cartprice, cartstock;
    private void CartLoadProducts() {
        gridPane.getChildren().clear();

        List<Product> products = cart.getAllProducts();

        int column = 0;
        int row = 1;
        for (Product product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/CartProduct.fxml"));
                loader.setController(GeneralController.getInstance());
                VBox cardbox = loader.load();
                CartSetData(product);

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
    public void CartSetData(Product product){
        cartimage.setImage(product.getImagen());
        cartimage.setFitWidth(200);
        cartimage.setFitHeight(150);
        cartname.setText("Nombre: " + product.getNombre());
        cartprice.setText("Precio: " + product.getPrecio());
        cartstock.setText("Stock: " + product.getStock());
        CartBuyButton.setOnAction(event -> BuyProduct(product));
        CartDeleteButton.setOnAction(event -> CartDeleteProduct(product.getNombre()));
    }
    private void BuyProduct(Product product){
        history.addProductHistory(new HistoryProduct(product.getNombre(),
                product.getPrecio(), product.getStock(), product.getImagen(), LocalDate.now()));
        AlertFunction("Exito", "Producto comprado.");
    }
    private void CartDeleteProduct(String productName){
        cart.removeProduct(productName);
        AlertFunction("Exito", "Eliminado del carrito de compras");
        CartLoadProducts();
    }

    // USER WISHLIST
    @FXML
    private ImageView wimage;
    @FXML
    private MFXButton WishAddCart, WishDeleteButton;
    @FXML
    private Label wname, wprice, wstock;

    private void WishLoadProducts() {
        gridPane.getChildren().clear();

        List<Product> products = wishList.getAllProducts();

        int column = 0;
        int row = 1;
        for (Product product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/WishListProduct.fxml"));
                loader.setController(GeneralController.getInstance());
                VBox cardbox = loader.load();
                WishSetData(product);

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
    public void WishSetData(Product product){
        wimage.setImage(product.getImagen());
        wimage.setFitWidth(200);
        wimage.setFitHeight(150);
        wname.setText("Nombre: " + product.getNombre());
        wprice.setText("Precio: " + product.getPrecio());
        wstock.setText("Stock: " + product.getStock());
        WishAddCart.setOnAction(event -> WishAddToCart(product));
        WishDeleteButton.setOnAction(event -> CartDeleteProduct(product.getNombre()));
    }
    private void WishAddToCart(Product product){
        cart.AddProduct(product);
        AlertFunction("Exit", "Agregado al carrito.");
    }
    private void WishDelete(String productName){
        wishList.removeProduct(productName);
        AlertFunction("Exito", "Eliminado de lista de deseos.");
        WishLoadProducts();
    }

    // USER HISTORY
    @FXML
    private ImageView historyimage;
    @FXML
    private Label historyname, historyprice, historystock, historyBuyDate;
    private void HistoryLoadProducts() {
        gridPane.getChildren().clear();

        List<HistoryProduct> products = history.getAllProductHistories() ;

        int column = 0;
        int row = 1;
        for (HistoryProduct product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/User/History/HistoryCard.fxml"));
                loader.setController(GeneralController.getInstance());
                VBox cardbox = loader.load();
                HistorySetData(product);

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
    public void HistorySetData(HistoryProduct product){
        historyimage.setImage(product.getImagen());
        historyimage.setFitWidth(200);
        historyimage.setFitHeight(150);
        historyname.setText("Nombre: " + product.getNombre());
        historyprice.setText("Precio: " + product.getPrecio());
        historyBuyDate.setText("Fecha: " + product.getFechaCompra());
    }

    // USER PROFILE
    @FXML
    private MFXTextField profilename, profilelastname, profileemail;
    @FXML
    private MFXPasswordField profilepassword;

    private void loadProfile(User user){
        profileemail.setText(user.getEmail());
        profilename.setText(user.getNombre());
        profilelastname.setText(user.getApellido());
    }
    public void UpdateProfileAction(ActionEvent event) throws IOException {
        if (profilename.getText().isBlank() || profilelastname.getText().isBlank()
                || profileemail.getText().isBlank() || profilepassword.getText().isBlank()) {
            AlertFunction("Actualizacion", "Los campos no pueden estar vacios");
        } else {
            userList.updateUser(uid, profileemail.getText(),
                    profilepassword.getText(), profilename.getText(), profilelastname.getText());
            profilepassword.clear();
            profileemail.clear();
            profilename.clear();
            profilelastname.clear();
            User user = userList.GetByUid(uid);
            loadProfile(user);
            AlertFunction("EXito", "Usuario actualizado con exito!");
        }
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
                userList.addUser(new User(uemail.getText(), upassword.getText(), uname.getText(), ulastname.getText()));
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
