<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${not empty resource.updatedOn}">
<h2>${resource.title} <c:if test="${not empty resource.year}">(${resource.year})</c:if></h2>
</c:if>

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

