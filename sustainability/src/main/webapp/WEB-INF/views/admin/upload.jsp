<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/admin/annotation/add" var="postUrl" />
<form:form method="POST" action="${postUrl}?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">

	<div class="form-group row">
		<label for="description" class="col-md-2 col-form-label">Annotations file:</label>
		<input type="file" name="file" class="form-control col-md-10" id="file" />
	</div>

	<button class="btn btn-primary btn-sm" type="submit" value="submit">Add Annotations</button>
</form:form>
