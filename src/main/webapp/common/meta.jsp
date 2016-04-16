<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%application.setAttribute("contextPath", request.getContextPath()); %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${contextPath}/common/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<script src="${contextPath}/common/jquery-1.12.0.min.js"></script>

<script src="${contextPath}/common/bootstrap/js/bootstrap.min.js"></script>
<script src="${contextPath}/common/json.js"></script>
<script src="${contextPath}/common/jquery.form.js"></script>
<script src="${contextPath}/common/jquery.cookie.js"></script>
<script>
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, '');
	}
	window.contextPath = '${contextPath}';
</script>
