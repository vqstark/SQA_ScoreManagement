/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentDAO extends DAO{
    
    private static final String QUERY_LOGIN = "select id,password,full_name,clas from student where username =?";
    
    private static final String CHANGE_PASSWORD = "UPDATE student SET password = ? WHERE username = ?";
    
    public StudentDAO() {
        super();
    }
    
    public Student login(String username, String userPassword) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LOGIN);) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("id");
                String password = rs.getString("password");
                String fullName = rs.getString("full_name");
                String clas = rs.getString("clas");
                if(password.equals(userPassword)){
                    return new Student(user_id, username, fullName, clas);
                }else{
                    return new Student();
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
    
    public int changePassword(String username, String newPassword){
        int rowsUpdated = -1;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD);) {
            preparedStatement.setString(2, username);
            preparedStatement.setString(1, newPassword);
            System.out.println(preparedStatement);
            rowsUpdated = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowsUpdated;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
