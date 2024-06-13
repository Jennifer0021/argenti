package models;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;

public class DB {
    public DB() {
    }

    public Connection Connect() {
        String db = "jmakeup";
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

    public void CreateUser(String name, String lastname, String email, String password) {
        String sql = "insert into juser (name, lastname, email, jpassword) values (?, ?, ?, ?)";
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

            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Insert succesfull.");
            } else {
                System.out.println("Row not inserted.");
            }

        } catch (SQLException e) {
            System.out.println("Error while insert operation: " + e);
        }
    }

    public void LoginUser(String email, String jpassword) {
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
                    email = result.getString("email");
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

}

