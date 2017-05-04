<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.util.List"%>
<%@page import="models.*"%>
<%@page import="DAL.Repo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link href="content/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-3.1.0.min.js" type="text/javascript"></script>
        <script src="scripts/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container">
            <div class="row">
                <c:forEach var="proizvod" items="${kategorije}" >
                    <div class="col-md-4">
                        <div style="text-align: center">
                            <h3>${proizvod.getNaziv()}</h3>
                        </div>
                        <div col-lg-12 style="min-height: 200px">
                            <img src="${proizvod.getSlika()}" class="img-responsive" style="max-width: 100%;max-height: 100%;height: auto" alt="slika"/>
                        </div>
                        <div style="text-align: left">
                            <strong> <fmt:formatNumber value="${proizvod.getCijena()}" type="currency"/></strong>
                        </div>
                        <div class="col-lg-12" style="text-align: right;margin-top: 5px">
                            <c:url value="Proizvod" var="putanja">
                                <c:param name="proizvodID" value="${proizvod.getIDProizvod()}" />
                            </c:url>

                            <a href="${putanja}" class="btn btn-primary">Detalji</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</body>
</html>


