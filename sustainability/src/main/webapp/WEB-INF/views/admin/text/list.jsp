<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h2>Annotated Texts</h2>

<c:url value="/admin/text/update/all" var="updateTextsUrl" />
<div class="col-sm-12">
<form action="${updateTextsUrl}" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<button style="margin-bottom: 20px;" class="btn btn-primary btn-md pull-right">Update Text Metadata</button>
</form>
</div>

<div class="col-sm-12">
<c:forEach items="${texts}" var="text">
<div class="panel panel-default">
  <div class="panel-body">
  <c:if test="${not empty text.updatedOn}">
  ${text.title} (${text.year})<br>
  <small>${text.uri}, updated on ${text.updatedOn}</small>
  </c:if>
  <c:if test="${empty text.updatedOn}">
    ${text.uri}
  </c:if>
  
  <div class="pull-right">
  <c:url value="/admin/text/update?uri=${text.uri}" var="updateResourceUrl" />
  <form action="${updateResourceUrl}" method="POST">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<button class="btn btn-primary btn-sm pull-right" title="Update this text's metadata"><i class="fa fa-refresh" aria-hidden="true"></i>
</button>
	</form>
  </div>
  </div>
</div>
</c:forEach>
</div>