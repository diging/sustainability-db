<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="panel panel-default">
<c:forEach items="${concepts}" var="concept">

<div class="panel-heading">
<p><strong>${concept.name}</strong></p>
</div>
<div>
<c:forEach items="${concept.children}" var="child">
<div class="panel-body"">
${child.name}
<c:forEach items="${roles}" var="role">
<input type="checkbox" name="${role}" value="${role}"> ${role}
</c:forEach>
</div>

</c:forEach>
</div>
</c:forEach>
</div>