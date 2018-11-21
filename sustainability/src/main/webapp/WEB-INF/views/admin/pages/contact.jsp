<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Edit Contact Info</h2>

<c:url value="/admin/pages/contact/edit" var="postUrl" />
<form:form action="${postUrl}" method="POST" modelAttribute="form">
	
	<div class="form-group">
	<label for="title">Title:</label>
	<form:input id="title" class="form-control" path="title"/>
	</div>
	
	<div class="form-group">
	<label for="content">Text:</label>
	<span class="pull-right text-small">You can use <a href="https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet">Markdown</a> to format the text.</span>
	<form:textarea id="content" path="content" rows="40" class="form-control"/>
	</div>
	<div style="margin-top: 20px" class="pull-right">
	<button type="submit" class="btn btn-primary"><i class="fa fa-floppy-o" aria-hidden="true"></i> Save</button>
	</div>
</form:form>