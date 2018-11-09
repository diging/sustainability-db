<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Sustainable Development Goal</h1>

<c:url value="/perspective/search" var="searchUrl" />
<form:form action="${searchUrl}" method="POST">

<c:forEach items="${concepts}" var="concept">
<div class="row">
<div class="col-md-2">
<p><strong>${concept.name}</strong></p>
</div>
<div class="col-md-10">
<c:forEach items="${concept.children}" var="child">
<div class="col-md-3">
<div class="alert alert-warning" role="alert"><input type="checkbox" name="selectedConcepts" value="${child.id}"> ${child.name}</div>
</div>

</c:forEach>
</div>
</div>
</c:forEach>

<div class="text-right">
<button type="submit" class="btn btn-default">Search</button>
</div>
</form:form>