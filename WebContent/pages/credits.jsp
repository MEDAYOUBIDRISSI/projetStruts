<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
  <head>
  	<title>Gestion des Cartes Credit</title>
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
				<s:a href="%{lancer_prop}">Gestion des Proprietaires</s:a><br/>
				</li>
				<li class="active">
				<s:url namespace="/credit"  action="index" var="lancer_credit" />
				<s:a href="%{lancer_credit}">Gestion des  Cartes</s:a><br/>
				</li>
				<li>
				<s:url namespace="/transaction"  action="index" var="lancer_trans" />
				<s:a href="%{lancer_trans}">Gestion des  Transactions</s:a><br/>
				</li>
				<li>
				<s:url namespace="/facture"  action="index" var="lancer_fact" />
				<s:a href="%{lancer_fact}">Gestion des  Factures</s:a><br/>
				</li>
	        </ul>

	        <div class="footer">
	        	<p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved  <i class="icon-heart" aria-hidden="true"></i>
						  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
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
                <li class="nav-item active">
                    <s:url namespace="/credit"  action="index" var="lancer_credit" />
					<s:a class="nav-link" href="%{lancer_credit}">Cartes</s:a><br/>
				
                </li>
                <li class="nav-item">
                    <s:url namespace="/transaction"  action="index" var="lancer_trans" />
					<s:a class="nav-link" href="%{lancer_trans}">Transactions</s:a><br/>
				
                </li>
                <li class="nav-item">
                    <s:url namespace="/facture"  action="index" var="lancer_fact" />
					<s:a class="nav-link" href="%{lancer_fact}">Factures</s:a><br/>
                </li>
              </ul>
            </div>
          </div>
        </nav>
		<!-- content -->
     		<h2>Ajouter Credit Carte</h2>					
 					<s:form namespace="/credit" action="addCredit" method="post" validate="false" >
											<s:textfield class="form-control" name="numCarte" label="Num Carte" />
											<s:select class="multiselect-dropdown form-control"
												 label="Proprietaire"
												 list="listeProprietaires"
												 name="propSelected"
												 listKey="id"
												 listValue="nomPrenom"
												 >
											</s:select>
											<s:submit class="btn btn-primary btn-block" value="Save"/>
					</s:form>
 									
						<h2>Liste des Cartes Credits </h2>
						<br />
						<table class="table">
								<tr><th>N� Carte cr�dit</th><th>Nom Pr�nom du Propri�taire</th><th>Action</th></tr>
						<s:iterator value="listCarteCredit" var="cp">
						<tr><td>
						<s:property value="#cp.numCarte"/>
						</td>
						<td>
						<s:property value="#cp.proprietaire.nomPrenom"/>
						</td>
						
						<td>
						<s:url namespace="/credit"  action="updateCarte" var="update_Credit" >
						<s:param name="numCarte" value="%{numCarte}"/>
						<s:param name="propSelected" value="%{proprietaire.id}"/>
						</s:url>
						<s:a href="%{update_Credit}">Modifier</s:a>
						<s:url namespace="/credit"  action="deleteCarte" var="delete_Credit" >
						<s:param name="numCarte" value="%{numCarte}"/>
						</s:url>
						<s:a href="%{delete_Credit}">Supprimer</s:a>
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