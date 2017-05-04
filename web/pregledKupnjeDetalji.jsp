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
                    <table id="tablicaKupnje" class="footable table table-stripped toggle-arrow-tiny">
                        <thead>
                            <tr>
                                <th>Proizvod</th>
                                <th>Količina</th>
                                <th>Cijena/kom</th>
                                 <th>Cijena ukupno</th>
                            </tr>
                        </thead>
                         <tfoot>
                            <tr>
                                <td>Ukupno</td>
                                <td></td>
                                <td></td>
                                <td id="cijenaUkupno"></td>
                            </tr>
                        </tfoot>
                        <tbody id="stavke">
                            <c:forEach var="ProizvodID" items="${Stavke}" >
                                <tr>
                                    <td>
                                        <div id="idProizvod">
                                            ${ProizvodID.getNaziv()}
                                        </div>
                                    </td>
                                    <td>
                                        ${ProizvodID.getKolicina()}
                                    </td>
                                    <td>
                                        ${ProizvodID.getCijenaKomad()}
                                    </td>
                                    <td class="cijena">
                                     ${ProizvodID.getCijenaKomad()*ProizvodID.getKolicina()}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(function () {
            $('.footable').footable();
            
             function ukupnoCijena() {
                var ukupno = 0;
                $('.cijena').each(function () {
                    ukupno += parseInt($(this).html());
                    $("#cijenaUkupno").html(ukupno);
                });
            }
            
            ukupnoCijena();
    });
    </script>
</html>


