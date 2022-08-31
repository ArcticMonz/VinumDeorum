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
<link rel="stylesheet" type="text/css" href="style/header.css">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="index.jsp">Vinum Deorum</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown">
                <a style="color: white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Shop
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    
                    <span><a class=dropdown-item
                             href="TipologiaController?tipologia=bianchi">Vini Bianchi</a></span>
                    <span><a class=dropdown-item
                             href="TipologiaController?tipologia=bianchi">Vini Rossi</a></span>
                             
                    
                </div>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="">Esplora<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href=""><%=label%><span class="sr-only">(current)</span></a>
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
