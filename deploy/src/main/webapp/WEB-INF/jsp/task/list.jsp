<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>任务管理</title>
</head>
<body>
<div id="msg"></div>
<div class="row">
    <div class="span4 offset7">
        <form class="form-search" action="#">
            <label>名称：</label>
            <input type="text" name="search_LIKE_title" class="input-medium" value="${param.search_LIKE_title}">
            <button type="submit" class="btn" id="search_btn">Search</button>
        </form>
    </div>
</div>
<br/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>任务</th>
        <th>管理</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tasks.content}" var="task">
        <tr>
            <td><a href="${ctx}/task/update/${task.id}">${task.title}</a></td>
            <td><a href="${ctx}/task/delete/${task.id}">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div id="pageing"></div>

<div><a class="btn btn-primary" href="${ctx}/web/task/save">创建任务</a></div>
<script lang="javascript">
    var page = page || 1;
    //TODO
    $.ajax({
        url: '/task/list',
        type: 'post',
        data: {
            page: page
        },
        success: function (result) {
            var pageDom = $("#pageing");
            if (pageDom.length > 0) {
                pageDom.html(createPagination(result.counts, result.current, 5, result.pageSize, "pageclick", false, "page"));
            }
        },
        error: function (XMLHttpRequest, textStatus, errorMsg) {
            $("#msg").attr("class", "alert alert-success").html('<button data-dismiss="alert" class="close">×</button>服务器错误')
        }
    });

</script>
</body>
</html>
