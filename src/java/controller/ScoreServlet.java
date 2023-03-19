/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ScoreDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Score;
import model.Student;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ScoreServlet", urlPatterns = {"/score"})
public class ScoreServlet extends HttpServlet {
    private ScoreDAO scoreDAO;
    
    public void init() {
        scoreDAO = new ScoreDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/show_scores.jsp";
        Student loginedUser = (Student)request.getSession().getAttribute("userLogined");
        
        if(loginedUser!=null){
            System.out.println("=====> user ID: "+loginedUser.getId());

            Map<String, List<Score>> semesters_scores = scoreDAO.getScoresForAllSubjects(loginedUser.getId());
            Map<String, Double> semesters_avg_scores = scoreDAO.calcAvgScorePerSemester(semesters_scores);

            request.setAttribute("semesters_avg_scores", semesters_avg_scores);
            request.setAttribute("semesters_scores", semesters_scores);
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

            Map<String, List<Score>> semesters_scores = scoreDAO.getScoresForAllSubjects(loginedUser.getId(), reqSemester);
            Map<String, Double> semesters_avg_scores = scoreDAO.calcAvgScorePerSemester(semesters_scores);

            request.setAttribute("semesters_avg_scores", semesters_avg_scores);
            request.setAttribute("semesters_scores", semesters_scores);
            request.setAttribute("searchedKey", reqSemester);
        }else{
            url = "/index.html";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
