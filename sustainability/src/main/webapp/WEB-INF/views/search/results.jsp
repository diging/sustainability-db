<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Search Results</h1>
<h1>
	<small>You searched for: 
		<c:forEach items="${concepts}" var="concept">
			<span class="label label-warning">${concept.name}</span>
		</c:forEach>
	</small>
</h1>

<ul class="list-group">
	<c:forEach items="${resource}" var="entry" varStatus="vs">
		<c:if test="${not empty entry and empty entry.updatedOn}">
			<li id="part${vs.index}" class="list-group-item">
				<i class="fa fa-spinner fa-pulse"></i> 
				Please wait while we are loading the text for you.
			</li>
			<script>
				setTimeout(reload('${entry.uri}','${vs.index}'), 3000);
				function reload(uri, index) {
				    $.ajax({
					  url: "<c:url value="/perspective/researcher/resource"/>",
					  type : 'GET',
					  data: { 'uri' : uri },
					  success: function (rsrc) {
					  	setTimeout(function() {
							if(rsrc.updatedOn != null) {
							  var updateResults = "<li class = 'list-group-item'>" +
								"<c:url value='/text?uri=' var='rsrcUrl' />" +
									"<a href = ${rsrcUrl}" + rsrc.uri + ">" + 
										rsrc.title + " (" +	rsrc.year + ")</a></li>";
							$('#part' + index).replaceWith(updateResults);
						  }
						  else if(rsrc.updatedOn == null){
							  reload(rsrc.uri, index);
						  }
					    }, 3000);
				      },
				      error: function(e) {
				    	  var updateResults = "<li class = 'list-group-item'>" + 
				    	  		"<i class='glyphicon glyphicon-remove-sign'></i>" + 
				    	      	" Error loading the resource ${entry.uri}</li>";
				    	  $('#part' + index).replaceWith(updateResults);
					  }
				    });
			    }
			</script>
		</c:if>
		<c:if test="${not empty entry.updatedOn}">
			<li class="list-group-item">
			  <a href="<c:url value="/text?uri=${entry.uri}"/>">
				${entry.title} (${entry.year}) 
			  </a>
			</li>
		</c:if>
	</c:forEach>
</ul>