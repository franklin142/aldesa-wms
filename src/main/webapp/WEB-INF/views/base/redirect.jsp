<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Autenticado</title>
</head>
<body>
<script type="text/javascript">
var createCookie = function(name, value, days) {
    var expires;
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    }
    else {
        expires = "";
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}
<c:if test="${!isadmin}">
createCookie("user", "${user}", 2);
createCookie("auth", "${auth}", 2);
</c:if>
<c:if test="${isadmin}">
createCookie("useradm", "${user}", 2);
createCookie("authadm", "${auth}", 2);
</c:if>
window.location = "${redirecturl}";
</script>
</body>
</html>