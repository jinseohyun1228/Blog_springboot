<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username" >
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <button height="37px" id="btn-login" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=f1bbd2060c770d4aeeb7b81068bebbd6&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="37px" src="/image/kakao_login_medium.png"></a>
    </form>

</div>
<%@ include file="../layout/footer.jsp"%>


