<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- Custom styling for configuration page. -->
<link href="<c:url value="/resources/bootstrap/css/admin/admin.css" />"
	rel="stylesheet">

<c:url value="/admin/concept/configuration" var="updateConfigUrl" />
<form:form modelAttribute="form" action="${updateConfigUrl}"
	method="POST">
	<div class="panel panel-default">
		<div class="panel panel-heading update-config-panel">
			<strong id="alias-header">Concept Name &nbsp; Alias</strong> <strong
				id="search-header">Search Categories</strong>
		</div>
		<div class="panel-body">
			<c:forEach items="${concepts}" var="con" varStatus="vs">
				<li class="list-group-item"><form:hidden
						path="concepts[${vs.index}].id" value="${con.id}" /> ${con.name}
					&nbsp;<form:input type="text"
						path="concepts[${vs.index}].conceptAlias" value="${con.alias}"
						cssErrorClass="invalid" />
					<div class="checkbox-switch">
						<c:forEach items="${categories}" var="category">
							<spring:eval var="containsValue"
								expression="con.searchCategories.contains(category)" />
							<c:if test="${containsValue eq true}">
								<form:checkbox path="concepts[${vs.index}].searchCategories"
									cssErrorClass="invalid" value="${category}" checked="checked" />
							</c:if>
							<c:if test="${containsValue eq false}">
								<form:checkbox path="concepts[${vs.index}].searchCategories"
									cssErrorClass="invalid" value="${category}" />
							</c:if>
							<spring:eval
								expression="@propertyConfigurer.getProperty('concept_role_${fn:toLowerCase(category)}')" />
						</c:forEach>
					</div></li>

			</c:forEach>
		</div>
	</div>

	<div class="text-right">
		<button type="submit" class="btn btn-default">Update
			Configuration</button>
	</div>
</form:form>