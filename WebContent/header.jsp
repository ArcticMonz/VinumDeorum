<%@ page import="model.Utente" %>
<%@ page import="model.BottigliaDAO" %>
<%@ page import="model.Bottiglia" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%
    String label;
    String destination;
    Utente user = (Utente) request.getSession().getAttribute("user");
    if (user == null) {
        label = "Login";
        destination = request.getContextPath() + "/login.jsp";
    } else if (user.getAdmin()) {
        label = "Admin";
        destination = request.getContextPath() + "/adminPage.jsp";
    } else {
        label = "Area Personale";
        destination = request.getContextPath() + "/personalPage.jsp";
    }
%> 
<link rel="stylesheet" type="text/css" href="style/header.css?version=1">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="index.jsp">
    	<img src="images/logo.png" alt="..." height="36">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:#ffffff">
                    Shop
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    
                    <li><a class= "dropdown-item"
                             href="#">Vini Bianchi</a></li>
                    <li><a class="dropdown-item"
                             href="#">Vini Rossi</a></li>
                             
                    
          </ul>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="">Esplora<span class="sr-only"></span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="<%=response.encodeURL(destination)%>"><%=label%><span class="sr-only"></span></a>
            </li>
            <%if(user!=null){%>
            <li class="nav-item active">
                <a class="nav-link" href="">Log Out<span
                        class="sr-only">(current)</span></a>
            </li>
            <%}%>
        </ul>
    </div>
</nav>
