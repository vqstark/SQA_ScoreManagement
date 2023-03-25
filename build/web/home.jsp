<%-- 
    Document   : home
    Created on : Mar 12, 2023, 1:45:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Trang chủ</title>
        <style>
            body {
                background-color: #e5e5e5;
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
            #wrapp-left-header > a , #wrapp-right-header > a {
                padding: 20px 10px;
            }
            #wrapp-left-header > a:hover , #wrapp-right-header > a:hover{
                background-color: #2c74cc;
            }
            #wrapp-right-header {
                padding-right: 10px;
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
<!--        <h2>Welcome, ${userLogined.fullName}</h2>
        <form action="score" method="get">
            <button>Xem điểm</button>
        </form>-->
    </body>
    <script>
        var message = '<%= (String)request.getAttribute("alert_message") %>';
        
        if(message !== 'null'){
            alert(message);
        }
    </script>
</html>
