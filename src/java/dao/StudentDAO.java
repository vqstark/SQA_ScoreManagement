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
public class StudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/sqa_score_management?useUnicode=true&characterEncoding=utf8";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1211";
    
//    private static final String SELECT_STUDENT_BY_USERNAME = "select * from student where username =?";
    private static final String QUERY_LOGIN = "select id,password,full_name,clas from student where username =?";
    
    public StudentDAO() {
    }
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    public Student login(String username, String userPassword) {
        Student stu = null;
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
                    return null;
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return stu;
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
