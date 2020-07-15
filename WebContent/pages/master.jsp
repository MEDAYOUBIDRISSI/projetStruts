<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="app-container app-theme-white body-tabs-shadow fixed-header fixed-sidebar">
		<jsp:include page="/pages/Header.jsp"></jsp:include>
		<div class="app-main">
			<div class="app-sidebar sidebar-shadow">
            <div class="app-header__logo">
                <div class="logo-src"></div>
                <div class="header__pane ml-auto">
                    <div>
                        <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="app-header__mobile-menu">
                <div>
                    <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                        <span class="hamburger-box">
                            <span class="hamburger-inner"></span>
                        </span>
                    </button>
                </div>
            </div>
            <div class="app-header__menu">
                <span>
                    <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                        <span class="btn-icon-wrapper">
                            <i class="fa fa-ellipsis-v fa-w-6"></i>
                        </span>
                    </button>
                </span>
            </div>    
            <div class="scrollbar-sidebar ps ps--active-y">
                <div class="app-sidebar__inner">
                    <ul class="vertical-nav-menu metismenu">
                        <li class="app-sidebar__heading">Menu</li>
							<li>
								<a href="">
									<i class="metismenu-icon pe-7s-id"></i>
									Employes
									<i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
								</a>
								<ul class="mm-collapse">
									<li>
										List 
									</li>
									<li>
										les taches
									</li>
								</ul>
							</li> 
                        </li>
                    </ul>
                </div>
            </div>
        </div>
			<div class="app-main__outer">
 				<div class="app-main__inner">
 				<div class="app-inner-layout chat-layout">
				 <div class="app-inner-layout__wrapper">
				 <div class="app-inner-layout__content card">
 						
 						<div class="container">
 							<div class="row">
 								<div class="col-md-12">
 									<div class="main-card mb-3 card">
 										<h4>Ajouter Propriétaires</h4>
	 									<s:form style="position:relative;left:150px;top:15px;" class="form-group" namespace="/prop" action="addOrSaveProp" method="post" validate="false" >
											<s:textfield class="form-control" name="idProp" label="Votre identificateur" />
											<s:textfield class="form-control" name="nomPrenom" label="Votre Nom & Prénom" />
											<s:submit class="btn btn-primary" value="Save"/>
										</s:form>
 									</div>
								
						<h2>Liste des propriétaires</h2>
						<br />
						<table class="table table-hover table-striped table-bordered">
						<tr><th>Id Proprietaire</th><th>Nom & Prénom</th><th>Actions</th></tr>
						<s:iterator value="listeProprietaires">
						<tr><td><s:property value="id"/></td>
						<td><s:property value="nomPrenom"/></td>
						<td>
						<s:url namespace="/prop"  action="updateProp" var="update_Prop" >
						<s:param name="idProp" value="%{id}"/>
						<s:param name="nomPrenom" value="%{nomPrenom}"/>
						</s:url>
						<s:a href="%{update_Prop}">Modifier</s:a>
						-
						<s:url namespace="/prop"  action="deleteProp" var="delete_Prop" >
						<s:param name="idProp" value="%{id}"/>
						<s:param name="nomPrenom" value="%{nomPrenom}"/>
						</s:url>
						<s:a href="%{delete_Prop}">Supprimer</s:a>
						</td>
						
						</tr>
						</s:iterator>
						</table>						
						<s:a href="/test1/pages/index.jsp">Retour au Menu Principal</s:a>
 								</div>
 							</div>
 						</div>
					</div>
					</div>
					</div>
 				</div>
 			</div>
		</div>
	</div>
</body>
</html>