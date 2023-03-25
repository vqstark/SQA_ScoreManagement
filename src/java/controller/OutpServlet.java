/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ThinkPad
 */
@WebServlet(name = "OutpServlet", urlPatterns = {"/out"})
public class OutpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student loginedUser = (Student)request.getSession().getAttribute("userLogined");
        
        if(loginedUser!=null){
            // Create .xlsx file
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã Môn");
            headerRow.createCell(1).setCellValue("Tên Môn");
            headerRow.createCell(2).setCellValue("TC");
            headerRow.createCell(3).setCellValue("%CC");
            headerRow.createCell(4).setCellValue("%KT");
            headerRow.createCell(5).setCellValue("%TH");
            headerRow.createCell(6).setCellValue("%BT");
            headerRow.createCell(7).setCellValue("%Thi");
            headerRow.createCell(8).setCellValue("Điểm CC");
            headerRow.createCell(9).setCellValue("Điểm KT");
            headerRow.createCell(10).setCellValue("Điểm TH");
            headerRow.createCell(11).setCellValue("Điểm BT");
            headerRow.createCell(12).setCellValue("Điểm Thi");
            headerRow.createCell(13).setCellValue("Tổng Kết (hệ 10)");
            headerRow.createCell(14).setCellValue("Tổng Kết (hệ chữ)");
            headerRow.createCell(15).setCellValue("Kết Quả");
            
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell == null) {
                    cell = headerRow.createCell(i);
                }
                cell.setCellStyle(style);
            }


            Map<String, List<Score>> semesters_scores = ScoreServlet.SEMESTER_SCORES;
            int rowNum = 1;
            for(Map.Entry<String, List<Score>> semes: semesters_scores.entrySet()){
                String semester = semes.getKey();
                List<Score> scores = semes.getValue();
                
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(semester);
                
                for(Score s:scores){
                    Row score_row = sheet.createRow(rowNum++);
                    score_row.createCell(0).setCellValue(s.getSubject().getId());
                    score_row.createCell(1).setCellValue(s.getSubject().getName());
                    score_row.createCell(2).setCellValue(s.getSubject().getTc()+"");
                    score_row.createCell(3).setCellValue(s.getSubject().getCc_percent());
                    score_row.createCell(4).setCellValue(s.getSubject().getKt_percent());
                    score_row.createCell(5).setCellValue(s.getSubject().getTh_percent());
                    score_row.createCell(6).setCellValue(s.getSubject().getBt_percent());
                    score_row.createCell(7).setCellValue(s.getSubject().getThi_percent());
                    score_row.createCell(8).setCellValue(s.getCc());
                    score_row.createCell(9).setCellValue(s.getKt());
                    score_row.createCell(10).setCellValue(s.getTh());
                    score_row.createCell(11).setCellValue(s.getBt());
                    score_row.createCell(12).setCellValue(s.getThi());
                    score_row.createCell(13).setCellValue(s.getTK());
                    score_row.createCell(14).setCellValue(s.getTKC());
                    score_row.createCell(15).setCellValue(s.getD());
                }
            }
            
            String fileName = "diem.xlsx";
            File file = new File(fileName);
            FileOutputStream foutputStream = new FileOutputStream(file);
            workbook.write(foutputStream);
           
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            OutputStream outputStream = response.getOutputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();
        }else{
            String url = "/index.html";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
