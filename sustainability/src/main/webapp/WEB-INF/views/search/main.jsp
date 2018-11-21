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

	<c:forEach items="${concepts}" var="concept" varStatus="conceptIter">
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
								<input type="checkbox" name="selectedConcepts" value="${child.id}"
								/>${child.name}
							</label>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
	<div class="text-right">
		<button type="submit" class="btn btn-default">Search</button>
	</div>
</form:form>