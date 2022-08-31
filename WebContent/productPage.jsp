<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "model.Bottiglia" %>
<%@ page import= "java.text.DecimalFormat" %>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina Prodotto</title>
<script src="libraries/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
            
    <style>
    	#liReg{
    		list-style-image:url(images/regione.png);
    	}
    	
    	#liCan{
    		list-style-image:url(images/cantina.png);
    	}
    	
    	#liAnno{
    		list-style-image:url(images/anno.png);
    	}
    	
    	#liTipo{
    		list-style-image:url(images/tipo.png);
    	}
    	
    	#disp{
    		font-weight: 300;
    	}
    </style>
</head>

<% 
Bottiglia bean = (Bottiglia) request.getAttribute("bottiglia");
Collection<Bottiglia> viniCorrelati = (Collection<Bottiglia>) request.getAttribute("bottiglieCorrelate");
double prezzo = bean.getPrezzo() + (bean.getPrezzo() * bean.getIva() / 100);
DecimalFormat df = new DecimalFormat("#.00");
   
%>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="header.jsp"/>

<!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src=<%="images/" + bean.getPathImage()%> style="max-width:600; max-height:700;"/></div>
                    <div class="col-md-6">
                        
                        <h1 class="display-5 fw-bolder"><%=bean.getNome() %></h1>
                        <div class="fs-5 mb-5">
                            
                            <h3><%=df.format(prezzo)%>€</h3>
                        </div>
                        <p class="lead"><%=bean.getDescrizione() %></p>
                        <br/>
                        <ul style="font-size:1.25em; font-weight:300;" class="ml-2">
                        <li id="liReg"><strong>Regione: </strong><%=bean.getRegione() %></li>
                        <li id="liCan"><strong>Cantina: </strong><%=bean.getCantina() %></li>
                        <li id="liAnno"><strong>Annata: </strong><%=bean.getAnno() %></li>
                        <li id="liTipo"><strong>Tipologia: </strong><%=bean.getTipologia() %></li>
                        </ul>
                        <br/>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0 ml-1" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Aggiungi al carrello
                            </button>
                            <%if(bean.getDisp()>5){ %>
                            <p class="ml-1 mb-0 mt-1" id="Disp" style="color:green;">Disponibilità immediata</p>
                            <%}
                            else if(bean.getDisp()<=5 && bean.getDisp()>0){
                            %>
                            <p class="ml-1 mb-0 mt-1" id="Disp" style="color:red">Affrettati! Ne rimangono solo <%=bean.getDisp() %> pezzi</p>
                            <%} 
                            else if(bean.getDisp()==0){
                            %>
                            <p class="ml-1 mb-0 mt-1" id="Disp" style="color:red">Non Disponibile</p>
                            <%} %>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Related items section-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Scopri i vini della cantina <%=bean.getCantina()%></h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4">
                    <%  Iterator<Bottiglia> i = viniCorrelati.iterator(); 
                    	for(int j=0; j<4; j++){
                    		if(!i.hasNext()) break;
                    		
                    		Bottiglia bott = (Bottiglia) i.next();	
                    		
                    		if(bott.getId()== bean.getId() && i.hasNext()) bott=i.next();
                    		else if(bott.getId()== bean.getId() && !i.hasNext()) break;
                    %>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src=<%="images/" + bott.getPathImage() %> alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder"><%=bott.getNome() %></h5>
                                    <!-- Product price-->
                                    <h6><%=df.format(prezzo)%>€</h6>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto"  href="./ProdottoDetail?id=<%=bott.getId()%>">Visualizza</a></div>
                            </div>
                        </div>
                    </div>
                    <%} %>
                   
                </div>
            </div>
        </section>

<jsp:include page="footer.jsp"/>

</body>
</html>