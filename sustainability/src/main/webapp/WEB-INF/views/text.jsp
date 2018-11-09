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
<strong>${metadatum.label}:</strong> ${metadatum.value}
</c:forEach>

<!-- header -->
<c:forEach items="${annotations}" var="anno">
	<c:if test="${empty anno.key}">
	<c:forEach items="${anno.value}" var="child">
	<div>
		<c:if test="${configs[child.key.id].section eq 'HEADING' and configs[child.key.id].displayType != 'HIDDEN'}">
		<c:if test="${configs[child.key.id].displayType == 'SHOW_CONCEPT_SEGMENT'}">
		<strong class="text-warning">${child.key.name}: </strong> 
			<c:forEach items="${child.value}" var="anno" varStatus="vstatus">
			<em>${anno.segment}</em><c:if test="${!vstatus.last}">, </c:if>
			</c:forEach>
		</c:if>
		</c:if>
	</div>
	</c:forEach>
	</c:if>
	
	<!-- child concepts -->
	<c:if test="${not empty anno.key}">
	<c:if test="${configs[anno.key.id].section eq 'HEADING' and configs[anno.key.id].displayType != 'HIDDEN'}">
	<div>
	<strong class="text-warning">${anno.key.name}:</strong>

	<c:forEach items="${anno.value}" var="child" varStatus="vstatus">
		<c:if test="${configs[anno.key.id].displayType == 'SHOW_CONCEPT_SEGMENT'}">
		<div class="col-md-12">
			<span class="text-warning">${child.key.name}: </span> 
			<c:forEach items="${child.value}" var="anno">
			<p><span class="fa fa-tag"></span> <em>${anno.segment}</em></p>
			</c:forEach>
		</div>
		</c:if>
		
		<c:if test="${configs[anno.key.id].displayType == 'SHOW_CONCEPT_ONLY'}">
		<span>${child.key.name}<c:if test="${!vstatus.last}">,</c:if></span> 
		</c:if>
	</c:forEach>
	</div>
	</c:if>
	</c:if>
</c:forEach>
<hr>

<!-- body -->
<c:forEach items="${annotations}" var="anno">
	<!-- child concepts -->
	<c:if test="${not empty anno.key}">
	<c:if test="${configs[anno.key.id].section eq 'ANNOTATIONS' and configs[anno.key.id].displayType != 'HIDDEN'}">
	
	<div style="padding-top: 10px"><strong>${anno.key.name}:</strong></div>
	
	<c:if test="${configs[anno.key.id].displayType == 'SHOW_CONCEPT_SEGMENT'}">
	<div class="col-md-12">
		<c:forEach items="${anno.value}" var="child" varStatus="vstatus">
			<span class="text-warning">${child.key.name}: </span> 
			<c:forEach items="${child.value}" var="anno">
			<p><span class="fa fa-tag"></span> <em>${anno.segment}</em></p>
			</c:forEach>
		</c:forEach>
	</div>
	</c:if>
		
	<c:if test="${configs[anno.key.id].displayType == 'SHOW_CONCEPT_ONLY'}">
	<div class="col-md-12">
		<c:forEach items="${anno.value}" var="child" varStatus="vstatus">
		<span class="text-warning">${child.key.name}<c:if test="${!vstatus.last}">,</c:if></span> 	
		</c:forEach>
	</div>
	</c:if>
	
	</c:if>
	</c:if>
	
	<!-- top concepts -->
	<c:if test="${empty anno.key}">
	<c:forEach items="${anno.value}" var="child">
		<c:if test="${configs[child.key.id].section eq 'ANNOTATIONS'}">
		<c:if test="${configs[child.key.id].displayType == 'SHOW_CONCEPT_SEGMENT'}">
		<strong class="text-warning">${child.key.name}: </strong> 
		<div class="col-md-12">
			<c:forEach items="${child.value}" var="anno">
			<p><span class="fa fa-tag"></span> <em>${anno.segment}</em></p>
			</c:forEach>
		</div>
		</c:if>
		<c:if test="${configs[child.key.id].displayType == 'SHOW_CONCEPT_ONLY'}">
			<span class="text-warning">${child.key.name}<c:if test="${!vstatus.last}">, </c:if></span> 
		</c:if>
		</c:if>
	</c:forEach>
	</c:if>
	
</c:forEach>

</c:if>

