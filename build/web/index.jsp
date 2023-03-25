<%-- 
    Document   : index
    Created on : Mar 15, 2023, 4:54:36 PM
    Author     : Admin
--%>
<%@page import="model.Student"%>
<%
Student user = (Student) session.getAttribute("userLogined");
if (user != null) {
    response.sendRedirect(request.getContextPath() + "/auth");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hệ thống quản lí điểm PTIT</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
/*            @use postcss-preset-env {
                stage: 0;
              }*/

              /* ---------- GENERAL ---------- */
              * {
                box-sizing: inherit;
              }

              html {
                box-sizing: border-box;
              }

              body {
                background-color: #c0c0c0;
                font-family: 'Varela Round', sans-serif;
                line-height: 1.5;
                margin: 0;
                min-block-size: 100vh;
                padding: 5vmin;
              }

              h2 {
                font-size: 1.75rem;
              }

              input {
                background-image: none;
                border: none;
                font: inherit;
                margin: 0;
                padding: 0;
                transition: all 0.3s;
              }

              svg {
                height: auto;
                max-width: 100%;
                vertical-align: middle;
              }

              /* ---------- ALIGN ---------- */
              .align {
                display: grid;
                place-items: center;
              }

              /* ---------- BUTTON ---------- */

              .button {
                background-color: #2D8ECE;
                color: #fff;
                padding: 0.25em 1.5em;
              }

              .button:focus,
              .button:hover {
                background-color: #2c74cc;
              }

              /* ---------- ICONS ---------- */
              .icons {
                display: none;
              }

              .icon {
                fill: currentcolor;
                display: inline-block;
                height: 1em;
                width: 1em;
              }

              /* ---------- LOGIN ---------- */
              .login {
                width: 500px;
              }

              .login__header {
                background-color: #2D8ECE;
                border-top-left-radius: 1.25em;
                border-top-right-radius: 1.25em;
                color: #fff;
                padding: 1.25em 1.625em;
              }

              .login__header :first-child {
                margin-top: 0;
              }

              .login__header :last-child {
                margin-bottom: 0;
              }

              .login h2 .icon {
                margin-right: 14px;
              }

              .login__form {
                background-color: #fff;
                border-bottom-left-radius: 1.25em;
                border-bottom-right-radius: 1.25em;
                color: #777;
                display: grid;
                gap: 0.875em;
                padding: 1.25em 1.625em;
              }

              .login input {
                border-radius: 0.1875em;
              }

              .login input[type="text"],
              .login input[type="password"] {
                background-color: #eee;
                color: #777;
                padding: 0.25em 0.625em;
                width: 100%;
              }

              .login input[type="submit"] {
                display: block;
                margin: 0 auto;
              }
        </style>
    </head>
<!--    <body>
        <h1>Hệ thống quản lí điểm PTIT</h1>
        <form action="<%=request.getContextPath()%>/auth/login" method="post">
            <label for="username">Tên đăng nhập</label>
            <input type="text" id="username" name="username" required/><br>
            <label for="password">Mật khẩu</label>
            <input type="password" id="password" name="password" required/><br>
            <button>Login</button>
        </form>
    </body>-->
    <body class="align">
        <div class="login">
          <header class="login__header">
            <h2><svg class="icon">
                <use xlink:href="#icon-lock" />
              </svg>Hệ thống quản lí điểm PTIT</h2>
          </header>

          <form action="<%=request.getContextPath()%>/auth/login" class="login__form" method="POST">
            <div>
              <label for="email">Tên đăng nhập</label>
              <input type="text" id="email" name="username" placeholder="" required>
            </div>

            <div>
              <label for="password">Mật khẩu</label>
              <input type="password" id="password" name="password" placeholder="" required>
            </div>

            <div>
              <input class="button" type="submit" value="Đăng nhập">
            </div>

          </form>

        </div>

        <svg xmlns="http://www.w3.org/2000/svg" class="icons">
          <symbol id="icon-lock" viewBox="0 0 448 512">
            <path d="M400 224h-24v-72C376 68.2 307.8 0 224 0S72 68.2 72 152v72H48c-26.5 0-48 21.5-48 48v192c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V272c0-26.5-21.5-48-48-48zm-104 0H152v-72c0-39.7 32.3-72 72-72s72 32.3 72 72v72z" />
          </symbol>
        </svg>
    </body>
</html>