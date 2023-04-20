/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Subject;

/**
 *
 * @author ThinkPad
 */
public class SubjectDAO extends DAO{
    
    private static final String GET_SUBJECT_BY_ID = "select * from subject where id = ?";

    public SubjectDAO() {
        super();
    }
    
    public Subject getSubjectByID(String id){
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SUBJECT_BY_ID);) {
            preparedStatement.setString(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String sid = rs.getString("id");
                String name = rs.getString("name");
                Double tc = rs.getDouble("tc");
                Double cc_percent = rs.getDouble("cc_percent");
                Double kt_percent = rs.getDouble("kt_percent");
                Double th_percent = rs.getDouble("th_percent");
                Double bt_percent = rs.getDouble("bt_percent");
                Double thi_percent = rs.getDouble("thi_percent");
                return new Subject(sid, name, tc, cc_percent, kt_percent, th_percent, bt_percent, thi_percent);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return new Subject();
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
