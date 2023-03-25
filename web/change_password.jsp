<%-- 
    Document   : change_password
    Created on : Mar 25, 2023, 11:32:16 AM
    Author     : ThinkPad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="home.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi mật khẩu</title>
        <style>
/*            @use postcss-preset-env {
                stage: 0;
              }*/

              /* ---------- GENERAL ---------- */

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
                margin: 0px auto;
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
    <body>
        <div class="login">

          <form action="<%=request.getContextPath()%>/auth/do_change" class="login__form" method="POST">
            <div class="notify" style="display:none"></div>
            <div>
              <label for="old_password">Mật khẩu cũ</label>
              <input type="password" id="old_password" name="old_password" placeholder="" onclick="deleteNotify()" required>
            </div>

            <div>
              <label for="new_password">Mật khẩu mới</label>
              <input type="password" id="new_password" name="new_password" placeholder="" onclick="deleteNotify()" required>
            </div>
            
            <div>
              <label for="rnew_password">Xác nhận mật khẩu mới</label>
              <input type="password" id="rnew_password" name="rnew_password" placeholder="" onclick="deleteNotify()" required>
            </div>

            <div>
              <input class="button" type="submit" value="Đổi mật khẩu">
            </div>

          </form>

        </div>

        <svg xmlns="http://www.w3.org/2000/svg" class="icons">
          <symbol id="icon-lock" viewBox="0 0 448 512">
            <path d="M400 224h-24v-72C376 68.2 307.8 0 224 0S72 68.2 72 152v72H48c-26.5 0-48 21.5-48 48v192c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V272c0-26.5-21.5-48-48-48zm-104 0H152v-72c0-39.7 32.3-72 72-72s72 32.3 72 72v72z" />
          </symbol>
        </svg>
    </body>
    <script>
        var message = '<%= (String)request.getAttribute("message") %>';
        
        var notifyElements = document.getElementsByClassName("notify");
        
        if (message === 'null') {
            for (var i = 0; i < notifyElements.length; i++) {
                notifyElements[i].style.display = 'none';
            }
        } else {
            for (var i = 0; i < notifyElements.length; i++) {
                notifyElements[i].style.display = 'block';
                notifyElements[i].style.color = 'red';
                notifyElements[i].innerHTML = message;
            }
        }
        
        function deleteNotify() {
            for (var i = 0; i < notifyElements.length; i++) {
                notifyElements[i].style.display = 'none';
            }
         }
    </script>
</html>
