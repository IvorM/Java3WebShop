<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.util.List"%>
<%@page import="models.*"%>
<%@page import="DAL.Repo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link href="content/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="content/footable.core.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-3.1.0.min.js" type="text/javascript"></script>
        <script src="scripts/bootstrap.min.js" type="text/javascript"></script>
        <script src="scripts/footable.all.min.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container">
            <div class="row">
                <div class="col-md-6 center-block" style="float:none">
                    <h2>Pregled kupnje</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 center-block" style="float:none;text-align: center">
                    <input type="text" class="form-control input-sm m-b-xs" id="filter"
                           placeholder="Pretraživanje...">
                    <table id="tablicaKupnje" class="footable table table-stripped toggle-arrow-tiny" data-page-size="20" data-filter=#filter>
                        <thead>
                            <tr>
                                <th>Datum</th>
                                <th>Cijena</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="stavke">
                            <c:forEach var="RacunViewModel" items="${Racun}" >
                                <tr>
                                    <td>
                                        <div id="idProizvod">
                                            ${RacunViewModel.getDatum()}
                                        </div>
                                    </td>
                                    <td>
                                        ${RacunViewModel.getCijena()}
                                    </td>
                                    <td>
                                        <c:url value="PregledKupnjeDetalji" var="putanja">
                                            <c:param name="racunID" value="${RacunViewModel.getIDRacun()}" />
                                        </c:url>
                                        <a href="${putanja}" class="btn btn-primary">Detalji</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="5">
                                    <ul class="pagination pull-right"></ul>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(function () {
            $('.footable').footable();
        });
    </script>
</html>



