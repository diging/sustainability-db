<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Custom styling for the search page. -->
<link href="<c:url value="/resources/bootstrap/css/search/search.css" />" rel="stylesheet">

<h1>Sustainable Development Goal</h1>

<c:url value="/perspective/search" var="searchUrl" />
<form:form action="${searchUrl}" method="POST">
	<div id="deleteAlert" class="alert alert-danger" style="display: none;">
		<strong>Please select at least one SDG to search.</strong>
	</div>
	<c:forEach items="${concepts}" var="concept">
		<div class="row">
			<div class="col-md-2">
				<p>
					<strong>${concept.name}</strong>
				</p>
			</div>
			<div class="col-md-10">
				<c:forEach items="${concept.children}" var="child">
					<div class="col-md-3">
						<div class="btn-group btn-group-toggle group-buttons" data-toggle="buttons" role="button">
							<label id="check" class="btn btn-default button-label">
							  <input type="checkbox" class="selectedConcepts"
								name="selectedConcepts" value="${child.id}">
							${child.name}
							</label>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>

	<div class="text-right">
		<button id="search-btn" type="submit" class="btn btn-default">Search</button>
	</div>
</form:form>
<script>
	$(document).ready(function() {
		$('#search-btn').on("click", function(e) {
			if ($('input[type="checkbox"]:checked').length == 0) {
				$('#deleteAlert').toggle("slide");
				e.preventDefault();
			}
		});
	});
</script>
