<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
  <head>
  	<title>Gestion des Factures</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<style type="text/css">
			<jsp:include page="../css/style.css"></jsp:include>
		</style>
  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
			<nav id="sidebar">
				<div class="p-4 pt-5">
		  		<a href="#" class="img logo rounded-circle mb-5" style="background-image: url(../images/logo.jpg);"></a>
	       <ul class="list-unstyled components mb-5">
	         <li>
				<s:url namespace="/prop" action="index" var="lancer_prop" />
				<s:a href="%{lancer_prop}">Gestion des proprietaires</s:a><br/>
				</li>
				<li>
				<s:url namespace="/credit"  action="index" var="lancer_credit" />
				<s:a href="%{lancer_credit}">Gestion des cartes</s:a><br/>
				</li>
				<li>
				<s:url namespace="/transaction"  action="index" var="lancer_trans" />
				<s:a href="%{lancer_trans}">Gestion des Transactions</s:a><br/>
				</li>
				<li class="active">
				<s:url namespace="/facture"  action="index" var="lancer_fact" />
				<s:a href="%{lancer_fact}">Gestion des Factures</s:a><br/>
				</li>
	        </ul>
	         <div class="footer">
	        	<p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved  <i class="icon-heart" aria-hidden="true"></i>
						  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</p>
	        </div>
	      </div>
    	</nav>

        <!-- Page Content  -->
      <div id="content" class="p-4 p-md-5">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">

            <button type="button" id="sidebarCollapse" class="btn btn-primary">
              <i class="fa fa-bars"></i>
              <span class="sr-only">Toggle Menu</span>
            </button>
            <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="nav navbar-nav ml-auto">
                <li class="nav-item ">
                    <s:url namespace="/prop" action="index" var="lancer_prop" />
					<s:a class="nav-link" href="%{lancer_prop}">Proprietaires</s:a><br/>
				
                </li>
                <li class="nav-item">
                    <s:url namespace="/credit"  action="index" var="lancer_credit" />
					<s:a class="nav-link" href="%{lancer_credit}">Cartes</s:a><br/>
				
                </li>
                <li class="nav-item">
                    <s:url namespace="/transaction"  action="index" var="lancer_trans" />
					<s:a class="nav-link" href="%{lancer_trans}">Transactions</s:a><br/>
				
                </li>
                <li class="nav-item">
                    <s:url namespace="/facture"  action="index" var="lancer_fact" />
					<s:a class="nav-link active" href="%{lancer_fact}">Factures</s:a><br/>
                </li>
              </ul>
            </div>
          </div>
        </nav>
		<!-- content -->
       		<h2>AJOUTER FACTURE:</h2>
<s:form method="post" action="addFacture" namespace="/facture">
	<s:textfield  class="form-control" label="Saisissez Le Num Facture " name="numFacture"/>
	<s:textfield class="form-control" label="Saisissez Le Montant " name="montant"/>
	<s:textfield class="form-control" label="Saisissez La Date Facture" name="dateFacture"/>
	<s:submit class="btn btn-primary btn-block" value="Add"/>
</s:form>
<h2>Liste des Factures </h2>
<br/>

<table class="table table-hover table-striped table-bordered">
<tr><th>num Facture</th><th>Montant</th><th>DateFacture</th><th>Action</th></tr>
<s:iterator value="listFactures" var="cp">
<tr>
	<td>
		<s:property value="#cp.numFacture"/>
	</td>
	<td>
		<s:property value="#cp.montant"/>
	</td>
	<td>
		<s:property value="#cp.dateFacture"/>
	</td>
	<td>
		<s:url namespace="/facture"  action="updateFacture" var="update_Facture" >
			<s:param name="numFacture" value="%{numFacture}"/>
			<s:param name="montant" value="%{montant}"/>
			<s:param name="dateFacture" value="%{dateFacture}"/>
		</s:url>
		<s:a href="%{update_Facture}">Modifier</s:a>
		
		<s:url namespace="/facture"  action="deleteFacture" var="delete_Facture" >
			<s:param name="numFacture" value="%{numFacture}"/>
		</s:url>
		<s:a href="%{delete_Facture}">Supprimer</s:a>
	</td>
</tr>
</s:iterator>
</table>

      	<!-- end content -->
      </div>
		</div>

	
    <script><jsp:include page="../js/jquery.min.js"></jsp:include></script>
    <script ><jsp:include page="../js/popper.js"></jsp:include></script>
    <script ><jsp:include page="../js/bootstrap.min.js"></jsp:include></script>
    <script ><jsp:include page="../js/main.js"></jsp:include></script>
  </body>
</html>