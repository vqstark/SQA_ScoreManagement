/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ScoreDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Score;
import model.Student;
import model.Subject;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ScoreServlet", urlPatterns = {"/score"})
public class ScoreServlet extends HttpServlet {
    private ScoreDAO scoreDAO;
    private SubjectDAO subjectDAO;
    
    public static Map<String, List<Score>> SEMESTER_SCORES = null;
    
    @Override
    public void init() {
        scoreDAO = new ScoreDAO();
        subjectDAO = new SubjectDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/show_scores.jsp";
        Student loginedUser = (Student)request.getSession().getAttribute("userLogined");
        
        if(loginedUser!=null){
            System.out.println("=====> user ID: "+loginedUser.getId());

            LinkedHashMap<String, List<Score>> semesters_scores = scoreDAO.getScoresForAllSubjects(loginedUser.getId());
            Map<String, Double> semesters_avg_scores = scoreDAO.calcAvgScorePerSemester(semesters_scores);
            List<Double> gpa = calc_gpa(loginedUser.getId());
            
            SEMESTER_SCORES = semesters_scores;
            
            request.setAttribute("semesters_avg_scores", semesters_avg_scores);
            request.setAttribute("semesters_scores", semesters_scores);
            request.setAttribute("utils", gpa);
        }else{
            url = "/index.html";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/show_scores.jsp";
        request.setCharacterEncoding("UTF-8");
        Student loginedUser = (Student)request.getSession().getAttribute("userLogined");
        if(loginedUser!=null){
            String reqSemester = request.getParameter("semester");

            LinkedHashMap<String, List<Score>> semesters_scores = 
                    scoreDAO.getScoresForAllSubjectsByGivenSemesName(loginedUser.getId(), reqSemester);
            Map<String, Double> semesters_avg_scores = scoreDAO.calcAvgScorePerSemester(semesters_scores);
            List<Double> gpa = calc_gpa(loginedUser.getId());
            
            SEMESTER_SCORES = semesters_scores;

            request.setAttribute("semesters_avg_scores", semesters_avg_scores);
            request.setAttribute("semesters_scores", semesters_scores);
            request.setAttribute("searchedKey", reqSemester);
            request.setAttribute("utils", gpa);
        }else{
            url = "/index.html";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    private List<Double> calc_gpa(int student_id){
        List<Score> scores = scoreDAO.getAllScores(student_id);
        
        Map<String, Double> filter = new HashMap<String, Double>();
        for(Score s:scores){
            double cur_score = s.getTK();
            String cur_sub_id = s.getSubject().getId();
            try{
                double his_score = filter.get(cur_sub_id);
                if(cur_score > his_score){
                    filter.put(cur_sub_id, cur_score);
                }
            }catch(Exception e){
                filter.put(cur_sub_id, cur_score);
            }
        }
        
        double gpa = 0;
        double total_tc = 0;
        double completed_tc = 0;
        
        for(Map.Entry<String,Double> s:filter.entrySet()){
            Subject sb = subjectDAO.getSubjectByID(s.getKey());
            System.out.println("====>" + sb.getId());
            Double score = s.getValue();
            
            double cur_tc = sb.getTc();
            if(score<4.0){
            } else if(4.0<=score && score<=4.9){
                gpa += cur_tc;
            } else if (5.0<=score && score<=5.4){
                gpa += cur_tc * 1.5;
            } else if (5.5<=score && score<=6.4){
                gpa += cur_tc * 2;
            } else if (6.5<=score && score<=6.9){
                gpa += cur_tc * 2.5;
            } else if (7.0<=score && score<=7.9){
                gpa += cur_tc * 3;
            } else if (8.0<=score && score<=8.4){
                gpa += cur_tc * 3.5;
            } else if (8.5<=score && score<=8.9){
                gpa += cur_tc * 3.7;
            } else{
                gpa += cur_tc * 4;
            }
            total_tc += cur_tc;
            if(4.0<=score) completed_tc += cur_tc;
        }
        gpa /= total_tc;
        DecimalFormat df = new DecimalFormat("0.00");
        gpa = Double.parseDouble(df.format(gpa));
        
        List<Double> rets = new ArrayList<>();
        rets.add(gpa);
        rets.add(completed_tc);
        return rets;
    }
}
