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
        <script src="scripts/jquery-3.1.0.min.js" type="text/javascript"></script>
        <script src="scripts/bootstrap.min.js" type="text/javascript"></script>
        <script src="scripts/notify.min.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="row">
            <div class="col-md-6 center-block" style="float: none">
                <div class="row">
                    <div class="col-md-6" style="min-height: 200px">
                        <img src="${proizvod.getSlika()}" class="img-responsive" style="max-width: 100%;max-height: 100%;height: auto" alt="slika"/>
                    </div>
                    <div class="col-md-6">
                        <h3>${proizvod.getNaziv()}</h3>
                        <strong>Cijena: </strong> <input type="number" readonly id="cijena" value="${proizvod.getCijena()}" >
                        <div>
                            <label>Količina: </label><input type="number" step="1" min="1" id="kolicina" style="max-width: 50px"/>
                        </div>
                        <div>
                            <button type="button" class="btn btn-primary" id="btnDodaj" value="${proizvod.getIDProizvod()}">Dodaj u košaricu</button>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </body>
    <script type="text/javascript">
        $("#btnDodaj")
                .click(function () {
                    var proizvodID = $(this).val();
                    var kolicina = $('#kolicina').val();
                    var cijena = $('#cijena').val();
                    console.log(kolicina);
                    console.log(cijena);

                    if (kolicina < 1) {
                        alert("Kolicina mora biti barem 1")
                        return;
                    }
                    $.ajax({
                        url: 'KosaaricaDodaj',
                        type: 'POST',
                        data: {
                            'proizvodID': proizvodID,
                            'kolicina': kolicina,
                            'cijena': cijena
                        },
                        success: function () {
                            if ("success" == "success") {
                                $.notify("OK", "success");

                            } else {
                                $.notify("Greška", "error");
                            }

                        },
                        error: function (xhr, status, error) {
                            $.notify("Pogreška", "error");
                        }
                    });
                });
    </script> 
</html>
