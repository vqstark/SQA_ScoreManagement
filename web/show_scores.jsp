<%-- 
    Document   : show_scores
    Created on : Mar 12, 2023, 6:52:59 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xem điểm</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            body {
                background-color: #e5e5e5;
            }
            thead {
                position: sticky;
                top: 0;
                z-index: 1;
                background-color: #2D8ECE;
                color: white;
            }
            #header {
                padding: 20px 0;
                background-color: #2D8ECE;
                text-align: right;
            }
            #wrapp-header {
                padding: 0px 100px;
                display: flex;
                justify-content: space-between;
            }
            #wrapp-left-header {
                padding-left: 10px;
            }
            #wrapp-right-header {
                padding-right: 10px;
            }
            #wrapp-left-header > a , #wrapp-right-header > a {
                padding: 20px 10px;
            }
            #wrapp-left-header > a:hover , #wrapp-right-header > a:hover{
                background-color: #2c74cc;
            }
            #wrapp-content {
                margin-left: 100px;
                margin-right: 100px;
                background-color: #ffffff;
/*                border-left: 1px solid #878787;
                border-right: 1px solid #878787;*/
            }
            #student-scores{
                margin: 0px 20px;
            }
            #student-info {
                width: 50%;
                margin: 0px auto;
            }
            #student-info table {
                width: 100%;
            }
            #search-box {
                display: flex;
                justify-content: space-around;
                margin-top: 100px;
            }
            .row_score td {
                text-align: center;
                font-size: 13px;
            }
            .row-semester td{
                background-color: #CCCCCC;
                font-weight: bold;
                font-size: 13px;
            }
            .title-row-score {
                text-align: center;
                font-size: 14px;
            }
        </style>
    </head>
    <body>
        <div id="header">
            <div id="wrapp-header">
                <div id="wrapp-left-header">
                    <a href="<%=request.getContextPath()%>/auth" style="color:white; text-decoration: none; font-weight: bold; margin-right: 20px">Trang chủ</a>
                    <a href="<%=request.getContextPath()%>/score" style="color:white; text-decoration: none; font-weight: bold">Xem điểm</a>
                </div>
                <div id="wrapp-right-header">
                    <span style="color:white; font-weight: bold; margin-right: 20px; font-style: italic">Chào bạn ${userLogined.fullName} (${userLogined.username})</span>
                    <a href="<%=request.getContextPath()%>/auth/change_password" style="color:white; text-decoration: none; font-weight: bold; margin-right: 20px">Thay đổi mật khẩu</a>
                    <a href="<%=request.getContextPath()%>/auth/logout" style="color:white; text-decoration: none; font-weight: bold">Đăng xuất</a>
                </div>
            </div>
        </div>
        <div id="wrapp-content">
            <div id="menu">
                
            </div>
            <div id="student-info">
                <div style="padding: 10px 0px; font-weight: bold; font-size: 20px">Thông tin sinh viên</div>
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td style="text-align: left;">Mã sinh viên</td>
                            <td style="text-align: left;">${userLogined.username}</td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">Tên sinh viên</td>
                            <td style="text-align: left;">${userLogined.fullName}</td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">Lớp</td>
                            <td style="text-align: left;">${userLogined.clas}</td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">GPA</td>
                            <td style="text-align: left;font-weight: bold">${utils[0]}</td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">Tổng số tín chỉ tích lũy</td>
                            <td style="text-align: left;font-weight: bold">${utils[1]}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="search-box">
                <form action="score" method="post" accept-charset="UTF-8">
                    <label for="search" style="padding-right: 10px">Xem điểm theo học kì</label>
                    <input type="text" id="search" name="semester" placeholder="Nhập học kì/năm học..."/>
                    <button>Xem điểm</button>
                </form>
            </div>
            <div style="color: #2D8ECE; display: flex; justify-content: space-around; margin-top: 10px; margin-bottom: 100px;">
                *Vui lòng tìm kiếm theo đúng định dạng "Học kì 1"; "Năm học 2019-2020"; "Học kì 1 - Năm học 2019-2020"
            </div>
            <div id="student-scores">
                <table class="table table-bordered">
                    <thead>
                        <tr class="title-row-score">
                            <th>Mã Môn</th>
                            <th>Tên Môn</th>
                            <th>TC</th>
                            <th></th>
                            <th>%CC</th>
                            <th>%KT</th>
                            <th>%TH</th>
                            <th>%BT</th>
                            <th>%Thi</th>
                            <th></th>
                            <th>Điểm CC</th>
                            <th>Điểm KT</th>
                            <th>Điểm TH</th>
                            <th>Điểm BT</th>
                            <th>Điểm Thi</th>
                            <th>Tổng Kết (hệ 10)</th>
                            <th>Tổng Kết (hệ chữ)</th>
                            <th>Kết Quả</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--   for (Todo todo: todos) {  -->
                        <c:forEach var="outerEntry" items="${semesters_scores}">
                            <tr class="row-semester">
                                <td colspan="18" style="font-size: 14px">${outerEntry.key}</td>
                            </tr>
                            <c:forEach var="innerItem" items="${outerEntry.value}">
                               <tr class="row_score">
                                    <td>${innerItem.subject.id}</td>
                                    <td style="text-align:left">${innerItem.subject.name}</td>
                                    <td>${innerItem.subject.tc}</td>
                                    <td></td>
                                    <td>${innerItem.subject.cc_percent}</td>
                                    <td>${innerItem.subject.kt_percent}</td>
                                    <td>${innerItem.subject.th_percent}</td>
                                    <td>${innerItem.subject.bt_percent}</td>
                                    <td>${innerItem.subject.thi_percent}</td>
                                    <td></td>
                                    <td>${innerItem.cc}</td>
                                    <td>${innerItem.kt}</td>
                                    <td>${innerItem.th}</td>
                                    <td>${innerItem.bt}</td>
                                    <td>${innerItem.thi}</td>
                                    <td>${innerItem.getTK()}</td>
                                    <td>${innerItem.getTKC()}</td>
                                    <td>${innerItem.getD()}</td>
                               </tr>
                            </c:forEach>
                            <tr>
                                <td style="font-weight: bold;font-size: 13px;font-style: italic" colspan="18">Điểm trung bình học kì: ${semesters_avg_scores[outerEntry.key]}</td>
                            </tr>
                         </c:forEach>
                        <!-- } -->
                    </tbody>
                </table>
            </div>
                        
            <div style="margin: 50px auto; display: flex; justify-content: space-around">
                <form action="out" method="get">
                    <button>IN ĐIỂM THI</button>
                </form>
            </div>
            <div style="color:blue; background-color: #f0f0f0; padding: 10px; text-align: center">
                Thiết kế bởi N15
            </div>
        </div>
        <script>
            var c = '${searchedKey}';
            var divSearch = document.getElementById("search");
            if(c !== null){
                divSearch.value = c;
            }else{
                divSearch.value = "";
            }
        </script>
    </body>
</html>
