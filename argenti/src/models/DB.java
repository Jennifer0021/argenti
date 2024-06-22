package models;

import DAO.CartObject;
import DAO.ProductObject;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DB {
    public DB() {
    }

    public Connection Connect() {
        String db = "jargenti";
        String user = "mika";
        String password = "tokai";
        String url = "jdbc:mysql://localhost:3306/";
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection cn = null;

        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url + db, user, password);
            System.out.println("Success while connecting to db: " + db);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while connecting to database: " + e);
        }

        return cn;
    }

    public void GetAllUser() {
        String sql = "select * from juser";
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return;
            }

            ResultSet result = pstmt.executeQuery();

            if (result.isBeforeFirst()) {
                System.out.println("Iterating succesfull.");
                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String lastname = result.getString("lastname");
                    String email = result.getString("email");
                    String password = result.getString("jpassword");

                    System.out.println("User-Id: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Lastname: " + lastname);
                    System.out.println("E-mail: " + email);
                    System.out.println("Password: " + password);
                    System.out.println("-------------------");

                }
            } else {
                System.out.println("Error while loading user data.");
            }

        } catch (

                SQLException e) {
            System.out.println("Error while getAll operation: " + e);
        }
    }

    public int GetUserId(String email, String password) {
        String sql = "select * from juser where email = ? and jpassword = ?";
        int id = 0;
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return 0;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return 0;
            }

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                // Hay al menos una fila de resultados
                System.out.println("Iterating successful.");
                do {
                    id = result.getInt("id");
                    System.out.println("User id: " + id);
                    // Puedes almacenar los IDs en una lista o hacer otro procesamiento aquí
                } while (result.next());
            } else {
                // No hay filas de resultados
                System.out.println("No results found.");
                // Aquí puedes manejar el caso cuando no hay resultados, por ejemplo, lanzar una excepción o devolver un valor predeterminado
            }

        } catch (SQLException e) {
            System.out.println("Error while getAll operation: " + e);
        }
        return id;
    }

    public Boolean CreateUser(String name, String lastname, String email, String password) {
        String sql = "insert into juser (name, lastname, email, jpassword) values (?, ?, ?, ?)";
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return false;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return false;
            }

            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Insert successfully.");
                return true;
            } else {
                System.out.println("Row not inserted.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while insert operation: " + e);
        }
        return false;
    }

    public Boolean LoginUser(String email, String jpassword) {
        String sql = "select count(1) from juser where email = ? and jpassword = ?";
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return false;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return false;
            }


            pstmt.setString(1, email);
            pstmt.setString(2, jpassword);

            ResultSet result = pstmt.executeQuery();

            while(result.next()) {
                if (result.getInt(1) == 1){
                    System.out.println("User matches");
                    return true;
                } else {
                    System.out.println("User doesn't match");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while getAll operation: " + e);
        }
        return false;
    }

    public Boolean ISRegistered(String email) {
        String sql = "select count(1) from juser where email = ?";
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return false;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return false;
            }

            pstmt.setString(1, email);

            ResultSet result = pstmt.executeQuery();

            while(result.next()) {
                if (result.getInt(1) == 1){
                    System.out.println("User matches");
                    return true;
                } else {
                    System.out.println("User doesn't match");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while getAll operation: " + e);
        }
        return false;
    }

    // Admin
    public Boolean LoginAdmin(String email, String jpassword) {
        String sql = "select count(1) from jadmin where email = ? and jpassword = ?";
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return false;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return false;
            }


            pstmt.setString(1, email);
            pstmt.setString(2, jpassword);

            ResultSet result = pstmt.executeQuery();

            while(result.next()) {
                if (result.getInt(1) == 1){
                    System.out.println("Admin matches");
                    return true;
                } else {
                    System.out.println("Admin doesn't match");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while getAll operation: " + e);
        }
        return false;
    }

    // Admin
    public Boolean CreateProduct(String name, Float price, int stock, InputStream image) {
        String sql = "insert into jproduct (name, price, stock, image) values (?, ?, ?, ?)";
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return false;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return false;
            }


            pstmt.setString(1, name);
            pstmt.setFloat(2, price);
            pstmt.setInt(3, stock);
            pstmt.setBlob(4, image);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Insert successfully.");
                return true;
            } else {
                System.out.println("Row not inserted.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while getAll operation: " + e);
        }
        return false;
    }

    // Product

    public List<ProductObject> GetProducts() {
        List<ProductObject> products = new ArrayList<>();
        String sql = "SELECT id, name, stock, price, image FROM jproduct";

        try (Connection conn = Connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                byte[] image = rs.getBytes("image");

                ProductObject product = new ProductObject(id, name, stock, price, image);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción apropiadamente
        }

        return products;
    }

    // Shopping cart

    public List<CartObject> GetCart(int userId) {
        List<CartObject> products = new ArrayList<>();
        String sql = "select u.id as uid, p.id as pid, p.name, p.price, p.image, p.stock from jproduct as p inner join juser as u on u.id = ?";

        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
            }


            pstmt.setInt(1, userId);


            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int uid = rs.getInt("uid");
                int pid = rs.getInt("pid");
                String name = rs.getString("name");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                byte[] image = rs.getBytes("image");

                CartObject product = new CartObject(uid, pid, name, stock, price, image);
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error while getAll operation: " + e);
        }

        return products;
    }

    // Add product to cart

    public Boolean AddToCart(int userID, int productId) {
        String sql = "insert into jcart (jproduct_id, juser_id) values (?, ?)";
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return false;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return false;
            }

            pstmt.setInt(1, productId);
            pstmt.setInt(2, userID);


            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Insert successfully.");
                return true;
            } else {
                System.out.println("Row not inserted.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while insert operation: " + e);
        }
        return false;
    }

    // Add product to cart

    public Boolean AddToWishList(int userID, int productId) {
        String sql = "insert into jwishlist (jproduct_id, juser_id) values (?, ?)";
        try {
            Connection cn = Connect();

            if (cn == null) {
                System.out.println("Error while connecting to database.");
                return false;
            }

            PreparedStatement pstmt = cn.prepareStatement(sql);

            if (pstmt == null) {
                System.out.println("Prepared statement is empty.");
                return false;
            }

            pstmt.setInt(1, productId);
            pstmt.setInt(2, userID);


            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Insert successfully.");
                return true;
            } else {
                System.out.println("Row not inserted.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while insert operation: " + e);
        }
        return false;
    }



}

