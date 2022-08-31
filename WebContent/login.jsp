<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Utente" %>
<%
    Utente user = (Utente) request.getSession().getAttribute("user");
    if (user != null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    String destination = (String) request.getAttribute("destination");
    Boolean error = (Boolean) request.getAttribute("error");
    if (error == null) {
        error = false;
    }
    if (destination == null) {
        destination = "/index.jsp";
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

	<script src="libraries/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-3" style="border:1px solid #cecece;">
<form name="loginForm" onsubmit="event.preventDefault(); validateForm(this)">
  
  <div class="form-group">
    <label for="InputUsername">Username</label>
    <input type="text" class="form-control" name="Username" id="InputUsername" placeholder="mariorossi2" required>
    	
  </div>
  
  <div class="form-group">
    <label for="InputPassword">Password</label>
    <input type="password" class="form-control" name="Password" id="InputPassword" required>
    
  </div>
  
  <div class="form-group">
	<input name="destination" value="<%=destination%>" style="visibility: hidden">
	<input name="action" value="login" style="visibility:hidden;">
  </div>

<button type="submit" class="btn btn-primary mb-2" >Login</button>
</form>
<div class="container-fluid text-center" id=page>
<h5>Vuoi iscriverti?</h5>
	<form method="post" action="<%=response.encodeURL(request.getContextPath() + "/registrati.jsp")%>">
            <button type="submit" class="btn btn-primary mb-2" >Registrati</button>
        </form>
     
</div>
</div>
  
</form>

</body>
</html>