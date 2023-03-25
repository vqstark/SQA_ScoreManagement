/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/","/auth", "/auth/*"})
public class AuthServlet extends HttpServlet {
    
    private StudentDAO studentDAO;
    
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if(action == null){
            showHomePage(request, response);
            return;
        }

        switch (action) {
            case "/login":
                login(request, response);
                break;
            case "/change_password":
                changePasswordUI(request, response);
                break;
            case "/do_change":
                changePassword(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            default:
                showHomePage(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    private void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String url = "/home.jsp";
        Student userLogined = (Student)request.getSession().getAttribute("userLogined");
        if(userLogined==null){
            url = "/index.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Student userLogined = studentDAO.login(username, password);
        if(userLogined!=null){
            request.getSession().setAttribute("userLogined", userLogined);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().setAttribute("userLogined", null);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    private void changePasswordUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Student userLogined = (Student)request.getSession().getAttribute("userLogined");
        if(userLogined!=null){
            request.getRequestDispatcher("/change_password.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    
    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String url="/index.jsp";
        Student userLogined = (Student)request.getSession().getAttribute("userLogined");
        if(userLogined!=null){
            String message =null;
            String alert_message=null;
            String old_password = request.getParameter("old_password");
            String new_password = request.getParameter("new_password");
            String rnew_password = request.getParameter("rnew_password");
            if(studentDAO.login(userLogined.getUsername(), old_password)!=null){
                if(new_password.equals(rnew_password)){
                    int row = studentDAO.changePassword(userLogined.getUsername(), new_password);
                    if(row>0){
                        alert_message = "Thay đổi mật khẩu thành công";
                    }else{
                        alert_message = "Thay đổi mật khẩu thất bại";
                    }
                    request.setAttribute("alert_message", alert_message);
                    url="/home.jsp";
                }else{
                    message = "Mật khẩu mới không khớp nhau";
                    request.setAttribute("message", message);
                    url="/change_password.jsp";
                }
            }else{
                message = "Mật khẩu cũ không chính xác";
                request.setAttribute("message", message);
                url="/change_password.jsp";
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
