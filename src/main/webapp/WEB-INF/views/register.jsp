<%--
  Created by IntelliJ IDEA.
  User: MAY CUA BIA
  Date: 25/02/2025
  Time: 5:14 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng ký</title>
</head>
<body>
    <div class="container">
        <div class="register-form-form">
            <div class="main-div">
                <c:if test="${param.registerFail !=null}">
                    <div class="alert alert-danger">
                        Register fail! Please try again!
                    </div>
                </c:if>

            </div>


        </div>

    </div>
</body>
</html>
