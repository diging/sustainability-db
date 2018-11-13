<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Uploaded Annotations</h2>
<table class="table">
<tr>
<th>Segment</th>
<th>Position</th>
<th>Occurs In</th>
<th>Concept</th>
<th>Uploaded File</th>
</tr>
<c:forEach items="${annotations}" var="annotation">
<tr>
	<td>${annotation.segment}</td>
	<td>${annotation.start} - ${annotation.end}</td>
	<td>${annotation.occursIn}</td>
	<td>${annotation.concept.name}</td>
	<td>${annotation.fromFile}</td>
</tr>
</c:forEach>
</table>
