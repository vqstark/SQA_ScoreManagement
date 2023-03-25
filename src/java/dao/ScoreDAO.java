/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Score;
import model.Semester;
import model.Subject;

/**
 *
 * @author Admin
 */
public class ScoreDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/sqa_score_management?useUnicode=true&characterEncoding=utf8";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1211";
    
    private static final String GET_ALL_SCORES_BY_STUDENT_ID =
            "select score.*, registed_subject.subject_id from score " +
            "join registed_subject on score.registedSubject_id = registed_subject.id "+
            "where registedSubject_id in (select id from registed_subject where student_id=? and semester_id=?);";
    
    private static final String GET_ALL_SCORES =
            "select subject_id, tc, cc_percent, kt_percent, th_percent, bt_percent, thi_percent, cc, kt, th, bt, thi from registed_subject " +
            "join score on score.registedSubject_id = registed_subject.id " +
            "join subject on subject.id = registed_subject.subject_id " +
            "where student_id = ?";
    
    private static final String GET_SEMESTER_BY_ID =
            "select name from semester where id = ?";
    
    private static final String GET_SUBJECT_BY_ID =
            "select * from subject where id = ?";
    
    private static final String GET_ALL_REGISTED_SEMESTER_BY_STUDENT_ID = 
            "select distinct semester_id from registed_subject where student_id = ?";
    
    private static final String GET_SEMESTER_ID_BY_GIVEN_NAME_VALUE = 
            "select distinct semester_id from registed_subject "+
            "join semester on semester.id = registed_subject.semester_id "+
            "where student_id = ? and semester.name like ?";
    
    
    public ScoreDAO() {
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
    
    public List<Integer> getRegistedSemesterByStudentID(int student_id){
        List<Integer> semester_id_list = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REGISTED_SEMESTER_BY_STUDENT_ID);) {
            preparedStatement.setInt(1, student_id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int semester_id = rs.getInt("semester_id");
                semester_id_list.add(semester_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return semester_id_list;
    }
    
    private Semester getSemesterByID(int id){
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEMESTER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                return new Semester(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return new Semester();
    }
    
    private List<Integer> getSemesterIdByGivenKeyName(int studentId, String key_name) throws UnsupportedEncodingException{
        String s = "'th'";
        List<Integer> semester_ids = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEMESTER_ID_BY_GIVEN_NAME_VALUE);) {
            String key = "%"+key_name+"%";
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, key);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("semester_id");
                semester_ids.add(id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return semester_ids;
    }
    
    private Subject getSubjectByID(String id){
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SUBJECT_BY_ID);) {
            preparedStatement.setString(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String s_id = rs.getString("id");
                String name = rs.getString("name");
                int tc = rs.getInt("tc");
                double cc_percent = rs.getDouble("cc_percent");
                double kt_percent = rs.getDouble("kt_percent");
                double th_percent = rs.getDouble("th_percent");
                double bt_percent = rs.getDouble("bt_percent");
                double thi_percent = rs.getDouble("thi_percent");
                return new Subject(s_id, name, tc, cc_percent, kt_percent, th_percent, bt_percent, thi_percent);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return new Subject();
    }
    

    public Map<String, List<Score>> getScoresForAllSubjects(int student_id){
        
        List<Integer> semester_id_list = getRegistedSemesterByStudentID(student_id);
        
        Map<String, List<Score>> semesters_scores = new HashMap<String, List<Score>>();
        
        for(int semester_id: semester_id_list){
            List<Score> scores = new ArrayList<>();
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SCORES_BY_STUDENT_ID);) {
                    preparedStatement.setInt(1, student_id);
                    preparedStatement.setInt(2, semester_id);
                    System.out.println(preparedStatement);
                    ResultSet rs = preparedStatement.executeQuery();
                    
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        double cc = rs.getDouble("cc");
                        double kt = rs.getDouble("kt");
                        double th = rs.getDouble("th");
                        double bt = rs.getDouble("bt");
                        double thi = rs.getDouble("thi");
                        int r_id = rs.getInt("registedSubject_id");
                        String subject_id = rs.getString("subject_id");
                        scores.add(new Score(id, getSubjectByID(subject_id), cc, kt, th, bt, thi));
                    }
            } catch (SQLException e) {
                printSQLException(e);
            }
            semesters_scores.put(getSemesterByID(semester_id).getName(), scores);
        }
        return semesters_scores;
    }
    
    public Map<String, List<Score>> getScoresForAllSubjects(int student_id, String key_name){
        
        List<Integer> semester_id_list = new ArrayList<>();
        Map<String, List<Score>> semesters_scores = new HashMap<String, List<Score>>();
        try{
            semester_id_list = getSemesterIdByGivenKeyName(student_id, key_name);
        }catch(UnsupportedEncodingException e){
            return semesters_scores;
        }
        
        for(int semester_id: semester_id_list){
            List<Score> scores = new ArrayList<>();
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SCORES_BY_STUDENT_ID);) {
                    preparedStatement.setInt(1, student_id);
                    preparedStatement.setInt(2, semester_id);
                    System.out.println(preparedStatement);
                    ResultSet rs = preparedStatement.executeQuery();
                    
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        double cc = rs.getDouble("cc");
                        double kt = rs.getDouble("kt");
                        double th = rs.getDouble("th");
                        double bt = rs.getDouble("bt");
                        double thi = rs.getDouble("thi");
                        int r_id = rs.getInt("registedSubject_id");
                        String subject_id = rs.getString("subject_id");
                        scores.add(new Score(id, getSubjectByID(subject_id), cc, kt, th, bt, thi));
                    }
            } catch (SQLException e) {
                printSQLException(e);
            }
            semesters_scores.put(getSemesterByID(semester_id).getName(), scores);
        }
        return semesters_scores;
    }
    
    public Map<String, Double> calcAvgScorePerSemester(Map<String, List<Score>> semesters_scores){
        Map<String, Double> semesters_avg_scores = new HashMap<String, Double>();
        
        for (Map.Entry<String, List<Score>> entry : semesters_scores.entrySet()) {
            String semester_name = entry.getKey();
            List<Score> scores = entry.getValue();
            double avg_score = 0;
            int total_tc = 0;
            if(scores.size()>0){
                for(Score c:scores){
                    double cur_tc = c.getSubject().getTc();
                    String tkc = c.getTKC();
                    if(tkc.equalsIgnoreCase("D")){
                        avg_score += cur_tc;
                    } else if (tkc.equalsIgnoreCase("D+")){
                        avg_score += cur_tc * 1.5;
                    } else if (tkc.equalsIgnoreCase("C")){
                        avg_score += cur_tc * 2;
                    } else if (tkc.equalsIgnoreCase("C+")){
                        avg_score += cur_tc * 2.5;
                    } else if (tkc.equalsIgnoreCase("B")){
                        avg_score += cur_tc * 3;
                    } else if (tkc.equalsIgnoreCase ("B+")){
                        avg_score += cur_tc * 3.5;
                    } else if (tkc.equalsIgnoreCase("A")){
                        avg_score += cur_tc * 3.7;
                    } else if (tkc.equalsIgnoreCase("A+")){
                        avg_score += cur_tc * 4;
                    }
                    total_tc += cur_tc;
                }
                DecimalFormat df = new DecimalFormat("0.00");
                semesters_avg_scores.put(semester_name, Double.parseDouble(df.format(avg_score/total_tc)));
            }
        }
        return semesters_avg_scores;
    }
    
    public List<Score> getAllScores(int student_id){
        List<Score> scores = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SCORES);) {
            preparedStatement.setInt(1, student_id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String subject_id = rs.getString("subject_id");
                double tc = rs.getDouble("tc");
                double cc_percent = rs.getDouble("cc_percent");
                double kt_percent = rs.getDouble("kt_percent");
                double th_percent = rs.getDouble("th_percent");
                double bt_percent = rs.getDouble("bt_percent");
                double thi_percent = rs.getDouble("thi_percent");
                
                Subject s = new Subject(subject_id, tc, cc_percent, kt_percent, th_percent, bt_percent, thi_percent);
                double cc = rs.getDouble("cc");
                double kt = rs.getDouble("kt");
                double th = rs.getDouble("th");
                double bt = rs.getDouble("bt");
                double thi = rs.getDouble("thi");
                Score c = new Score(s, cc, kt, th, bt, thi);
                scores.add(c);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return scores;
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
