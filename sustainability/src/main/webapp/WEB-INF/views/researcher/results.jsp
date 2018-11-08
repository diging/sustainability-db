<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Researcher Perspective</h1>
<h1>
	<small>Search Results</small>
</h1>

<p>
	<c:forEach items="${concepts}" var="concept">
		<span class="label label-warning">${concept.name}</span>
	</c:forEach>
</p>

<%-- <ul class="list-group">
	<c:forEach items="${resource}" var="entry">
		<c:if test="${empty entry.updatedOn}">
		<center>
			<i class="fa fa-spinner fa-pulse"></i> Please wait while we are loading the text for you.
		</center>
		</c:if>
		<c:if test="${not empty entry.updatedOn}">
			<li class="list-group-item"><a href="<c:url value="/text?uri=${entry.uri}"/>">${entry.title} - ${entry.year} </a></li>
		</c:if>
	</c:forEach>
</ul>
<script>
// reload page after three seconds
$(document).ready(function() {
	setTimeout(function() 
	  {
		location.reload(true);
	  }, 3000);
});
</script>
 --%>
 
<ul class="list-group">
	<c:forEach items="${results}" var="entry">
		<li class="list-group-item"><a href="<c:url value="/text?uri=${entry.key}" />">${entry.key}</a></li>
	</c:forEach>
</ul>