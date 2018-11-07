<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h2>${resource.title} <c:if test="${not empty resource.year}">(${resource.year})</c:if></h2>

