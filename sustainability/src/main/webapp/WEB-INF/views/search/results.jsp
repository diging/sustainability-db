<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Researcher Perspective</h1>
<h1>
	<small>Search Results</small>
</h1>

<ul class="list-group">
	<c:forEach items="${resource}" var="entry">
		<c:if test="${empty entry.updatedOn}">
			<li id="part" class="list-group-item">
			<i class="fa fa-spinner fa-pulse"></i> Please wait while we are loading the text for you.
			
		</li>
		<script>
		$(document).ready(function() {
			$.ajax({
				url: '/perspective/researcher/resource',
				type : 'GET',
				data: { 'uri' : '${entry.uri}'},
				headers: {
					"Accept": "text/plain; charset=utf-8",         
				    "Content-Type": "text/plain; charset=utf-8"	
				},
				success: function (resource) {
					console.log(resource);
					/* setInterval(function() 
							  {
						$('part').html("<a href='<c:url value='/text?uri="+resource.uri+"'/> >"+resource.title - resource.year+"</a>");
							  }, 3000); */
				},
				error: function(e){
					console.log('Error');
				}
			}); 
		});
		</script>
		</c:if>
		<c:if test="${not empty entry.updatedOn}">
			<li class="list-group-item"><a href="<c:url value="/text?uri=${entry.uri}"/>" >${entry.title} - ${entry.year} </a></li>
		</c:if>
	</c:forEach>
</ul>