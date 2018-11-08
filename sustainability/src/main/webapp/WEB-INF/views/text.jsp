<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty resource.updatedOn}">
<center>
	<i class="fa fa-spinner fa-pulse"></i> Please wait while we are loading the text for you.
</center>
<script>
// reload page after three seconds
$(document).ready(function() {
	setTimeout(function() 
	  {
		location.reload(true);
	  }, 3000);
});
</script>
</c:if>

<c:if test="${not empty resource.updatedOn}">
<h2>${resource.title} <c:if test="${not empty resource.year}">(${resource.year})</c:if></h2>

<c:forEach items="${resource.others}" var="metadatum">
${metadatum.label}: ${metadatum.value}
</c:forEach>


<c:forEach items="${annotations}" var="anno">
<c:if test="${not empty anno.key}">
<div><strong>${anno.key.name}:</strong></div>

<c:forEach items="${anno.value}" var="child">
	<div class="col-md-12">
	<span class="text-warning">${child.key.name}: </span> 
		<c:forEach items="${child.value}" var="anno">
		<p><span class="fa fa-tag"></span> <em>${anno.segment}</em></p>
		</c:forEach>
	</div>
</c:forEach>

</c:if>
<c:if test="${empty anno.key}">
<c:forEach items="${anno.value}" var="child">
	<strong class="text-warning">${child.key.name}: </strong> 
		<c:forEach items="${child.value}" var="anno">
		<p><span class="fa fa-tag"></span> <em>${anno.segment}</em></p>
		</c:forEach>
</c:forEach>
</c:if>
</c:forEach>

</c:if>

