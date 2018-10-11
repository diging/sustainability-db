<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="tree"></div>

<script
	src="<c:url value="/resources/treeview/js/bootstrap-treeview.js" />"></script>
<link
	href="<c:url value="/resources/treeview/bootstrap-treeview.css" />"
	rel="stylesheet">

<script>
	//# sourceURL=tree.js
	$(document).ready(function() {
		$.ajax({
			url : '<c:url value="/admin/concept/list.json" />',
			type : 'GET',
			cache : false,
			complete : function(xhr, status) {
				if (status === 'error' || !xhr.responseText) {
					console.log(error);
				} else {
					$('#tree').treeview({ 
						data: JSON.parse(xhr.responseText),
						expandIcon: "fa fa-caret-right",
						collapseIcon: "fa fa-caret-down",
					});
				}
			}
		});
	});
</script>