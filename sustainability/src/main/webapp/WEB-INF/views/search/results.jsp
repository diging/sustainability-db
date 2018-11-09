<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Search Results</h1>
<h1>
	<small>You searched for: 
	<c:forEach items="${concepts}" var="concept">
		<span class="label label-warning">${concept.name}</span>
	</c:forEach>
	</small>
</h1>


<ul class="list-group">
	<c:forEach items="${results}" var="entry">
		<li class="list-group-item"><a href="<c:url value="/text?uri=${entry.key}" />">${entry.key}</a></li>
	</c:forEach>
</ul>