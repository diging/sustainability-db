<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Text Display Configuration</h2>

<c:url value="/admin/text/config" var="postUrl" />
<form:form method="POST" action="${postUrl}" modelAttribute="form" class="form-horizontal">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<c:forEach items="${form.annotationConfigs}" var="aconf" varStatus="status">
	<div class="panel panel-default">
	  <div class="panel-heading">${aconf.conceptName}</div>
	  <div class="panel-body">
	  	<form:input type="hidden" path="annotationConfigs[${status.index}].conceptId" value="${aconf.conceptId}" />
	  	<form:input type="hidden" path="annotationConfigs[${status.index}].id" value="${aconf.id}" />
	    <div class="form-group">
		    <label for="section" class="col-sm-2 control-label text-left">Section</label>
		    <div class="col-sm-10">
		      	<form:select class="form-control" path="annotationConfigs[${status.index}].section" items="${sections}" />
		    </div>
		</div>
		<div class="form-group">
		    <label for="section" class="col-sm-2 control-label text-left">Display Type</label>
		    <div class="col-sm-10">
		      	<form:select class="form-control" path="annotationConfigs[${status.index}].displayType" items="${displayTypes}" />
		    </div>
		</div>
		<div class="form-group">
		    <label for="section" class="col-sm-2 control-label text-left">Sort order</label>
		    <div class="col-sm-10">
		      	<form:input class="form-control" type="number" path="annotationConfigs[${status.index}].sortOrder" />
		    </div>
		</div>
	  </div>
	</div>
	</c:forEach>

	<button type="submit" class="btn btn-primary pull-right">Submit</button>
</form:form>
