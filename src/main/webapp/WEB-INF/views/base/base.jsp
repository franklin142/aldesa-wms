<%--
  Author: Lennin Hernandez
  Date: 7/18/2016
  Time: 5:25 PM
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <tiles:insertAttribute name="title" /> - ALDESA WMS
    </title>

    <!-- styles -->
    <tiles:insertAttribute name="styles" />
    <!-- /styles -->

    <!-- scripts -->
    <tiles:insertAttribute name="scripts" />
    <!-- /scripts -->
</head>

<body>
<!-- header -->
<tiles:insertAttribute name="header" />
<!-- /header -->

<!-- body -->
<tiles:insertAttribute name="body" />
<!-- /body -->

<!-- footer -->
<tiles:insertAttribute name="footer" />
<!-- /footer -->

<!-- afterbdy -->
<tiles:insertAttribute name="afterbdy" />
<!-- /afterbdy -->

</body>
</html>