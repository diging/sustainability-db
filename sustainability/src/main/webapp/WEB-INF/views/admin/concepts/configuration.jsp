<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Custom styling for configuration page. -->
<link href="<c:url value="/resources/bootstrap/css/admin/admin.css" />" rel="stylesheet">

<c:url value="/admin/concept/updateconfiguration" var="updateConfigUrl" />
<form:form action="${updateConfigUrl}" method="POST">
<div class="panel panel-default">
  <c:forEach items="${concepts}" var="concept">
    <div class="panel-heading">
      <p><strong>${concept.name}</strong></p>
    </div>
    
    <div>
      <c:forEach items="${concept.children}" var="child">
        <div class="panel-body"">
	      <li class="list-group-item">
	        ${child.name}
	        <c:forEach items="${roles}" var="role">
	          <label class="switch checkbox-switch">
	            <input type="checkbox" class="default" name="${child.id}" value="${role}"> ${role}
	          </label>
	        </c:forEach>
	      </li>
        </div>
      </c:forEach>
    </div>
  </c:forEach>
</div>

<div class="text-right">
<button type="submit" class="btn btn-default">Update Configuration</button>
</div>
</form:form>


