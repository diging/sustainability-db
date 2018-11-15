<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				<c:forEach items="${concept.children}" var="child" varStatus="childIter">
					<div class="col-md-3">
						<div role="alert">
							<input type="hidden" name="selectedConcepts" id="hidconcept${conceptIter.index}child${childIter.index}" value="" />
							<input type="buttons" class="btn btn-default"
								 value="${child.name}" id="concept${conceptIter.index}child${childIter.index}" onClick=updateBox('${child.id}')/>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
	<script>
	//# sourceURL=tree.js
	$(document).ready(function() {
		$("input[id^='concept']").on("click", function(event){
			var x = '#'+event.target.id;
			$(x).toggleClass('btn-default btn-success');
			if($(x).siblings('#hid'+event.target.id).attr("value") == ""){
				console.log('ok');
				var childId= 'child'+$(x).attr("id").split('child')[1];
				$(x).siblings('#hid'+event.target.id).attr("value",childId);	
			}
			else {
				console.log('nok');
				$(x).siblings('#hid'+event.target.id).attr("value", "");	
			}
		});
	});
	function updateBox(childId){
		
	}
	</script>
	<div class="text-right">
		<button type="submit" class="btn btn-default">Search</button>
	</div>
</form:form>