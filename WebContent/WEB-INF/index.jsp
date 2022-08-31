<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List" %>
<%@ page import= "model.Bottiglia" %>
<%@ page import= "model.Utente" %>
<%@ page import= "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Vinum Deorum</title>

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

<style>
.card-img-top {
    height: auto; 
    width: auto; 
    max-width: 200px; 
    max-height: 400px;
}
</style>
<body>
<jsp:include page="header.jsp"/>
<br/>
<div id="recipeCarousel" class="carousel slide carousel-multi-item" data-interval="false">

	 <% 
       Collection<Bottiglia> listaViniCamp = (Collection<Bottiglia>) request.getAttribute("listaViniCampani");
	   
    %>

	
	
	<div class="container text-center my-3">
    <h2 class="font-weight-light">Vini preferiti dai clienti</h2>
    </div>
    <!--Slides-->
    <div class="carousel-inner" role="listbox">

      <!--First slide-->
      <div class="carousel-item active">
      
      <%
      //Iterator<Bottiglia> i = listaViniCamp.iterator();
      int i=0;
      while(i<2) {
    	 
    	 
    	 if(i>0){%>
    	 
    	 <div class="carousel-item">
    	 <%}%>
      <%for(int j=0; j<4; j++){
    	 
       %>  
      <div class="col-md-3" style="float:left">
          <div class="card mb-2">
            <img class="card-img-top mx-auto"
                 src="https://data.callmewine.com/imgprodotto/sirica-feudi-di-san-gregorio-2018_36768_zoom.jpg" alt="Card image cap">
            <div class="card-body">
              <h4 class="card-title"><%=j+i%></h4>
              <p class="card-text"><%=j+i %></p>
              <a class="btn btn-primary" style="background-color: #800000; color:white">Acquista</a>
            </div>
          </div>
        </div>
        
      	
      
       
       
       <%
       }%>
 	   </div>
       <%i++;
       } %>
       
        <a class="carousel-control-prev w-auto" href="#recipeCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon bg-dark border border-dark rounded-circle" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next w-auto" href="#recipeCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon bg-dark border border-dark rounded-circle" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
    </div>
    <!--/.Slides-->

  </div>

  

      








</body>
</html>