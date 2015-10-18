<%@ page import="com.appengine.user.web.WebUserController" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<title>用户注册</title>

<body>
<form id="inputForm" action="${ctx}/web/register" method="post" class="form-horizontal">
    <%
        String error = (String) request.getAttribute(WebUserController.REGISTER_ERROR_KEY);
        if (error != null) {
    %>
    <div class="alert alert-error input-medium controls">
        <button class="close" data-dismiss="alert">×</button>
        <% if (error.equals("exists")) { %>
        账号已存在，请更改后重试.
        <% } else { %>
        注册失败，请重试.
        <% } %>
    </div>
    <% } %>
    <fieldset>
        <legend>
            <small>用户注册</small>
        </legend>
        <div class="control-group">
            <label for="username" class="control-label">用户名:</label>

            <div class="controls">
                <input type="text" id="username" name="username" value="${username}" class="input-large required"/>
            </div>
        </div>
        <div class="control-group">
            <label for="password" class="control-label">密码:</label>

            <div class="controls">
                <input type="password" id="password" name="password" class="input-large required"/>
            </div>
        </div>
        <div class="control-group">
            <label for="confirmPassword" class="control-label">确认密码:</label>

            <div class="controls">
                <input type="password" id="confirmPassword" name="confirmPassword" class="input-large required"
                       equalTo="#password"/>
            </div>
        </div>
        <br/>

        <div class="form-actions">
            <input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
        </div>
    </fieldset>
</form>
<script>
    $(document).ready(function () {
        //聚焦第一个输入框
        $("#username").focus();
        //为inputForm注册validate函数
        $("#inputForm").validate();
    });
</script>
</body>
</html>
