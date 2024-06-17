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

}

