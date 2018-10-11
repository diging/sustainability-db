<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<sec:authorize access="isAnonymous()">


<h2 class="text-center" style="padding: 30px;">Which perspective do you choose?</h2>

<div class="col-md-6">
<div class="panel panel-default" style="background-color: #75c175;">
  <div class="panel-body" >
    <h1 class="text-center" style="min-height: 200px; line-height:200px;">
    <a href="<c:url value="/perspective/researcher" />">Researcher</a>
    </h1>
  </div>
</div>
</div>

<div class="col-md-6">
<div class="panel panel-default" style="background-color: #75c175;">
  <div class="panel-body" >
  <h1 class="text-center" style="min-height: 200px; line-height:200px;">
    Practitioner
    </h1>
  </div>
</div>
</div>

</sec:authorize>


<sec:authorize access="isAuthenticated()">
<div class="jumbotron col-md-12">
<h1>Whoohoo!</h1>
<p>You are logged in.</p>
</div>
</sec:authorize>
