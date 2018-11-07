<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<!-- Custom styling for configuration page. -->
<link href="<c:url value="/resources/bootstrap/css/admin/admin.css" />" rel="stylesheet">

<c:url value="/admin/concept/configuration" var="updateConfigUrl" />
<form:form modelAttribute="form" action="${updateConfigUrl}" method="POST">
<div class="panel panel-default">
  	<div>
      <c:forEach items="${concepts}" var="con" varStatus="vs">
        <div class="panel-body"">
	      <li class="list-group-item">
	        <form:hidden path="concepts[${vs.index}].id" value="${con.id}"/>
	        ${con.name}
	        <div class="checkbox-switch">
	          <c:forEach items="${roles}" var="role" >
	            <spring:eval var="containsValue" expression="con.roles.contains(role)" />
	            <c:if test="${containsValue eq true}">
		          <form:checkbox path="concepts[${vs.index}].roles"  cssErrorClass="invalid" value="${role}" checked="checked" />
		        </c:if>
		        <c:if test="${containsValue eq false}">
		          <form:checkbox path="concepts[${vs.index}].roles" cssErrorClass="invalid" value="${role}"/>
		        </c:if>
		        <spring:eval expression="@propertyConfigurer.getProperty('concept_role_${fn:toLowerCase(role)}')" />
	          </c:forEach>
	        </div>
	      </li>
        </div>
      </c:forEach>
    </div>
</div>

<div class="text-right">
<button type="submit" class="btn btn-default">Update Configuration</button>
</div>
</form:form>