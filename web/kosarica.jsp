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
        <script src="scripts/notify.min.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h2>Pregled košarice</h2>
                </div>
                <div class="col-md-6" style="text-align: right">
                    <button class="btn btn-danger" type="button" id="btnObrisiSve" value="${KosaricaViewModel.getStavka()}">Obriši sve</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-7 center-block" style="float:none">
                    <input type="text" class="form-control input-sm m-b-xs" id="filter"
                           placeholder="Pretraživanje...">
                    <table id="tablicaProizvoda" class="footable table table-stripped toggle-arrow-tiny" data-filter=#filter>
                        <thead>
                            <tr>
                                <th>Proizvod</th>
                                <th>Kolicina</th>
                                <th>Ukupno</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td>Ukupno</td>
                                <td id="thUkupnoKolicina">x</td>
                                <td></td>
                                <td id="thUkupnoCijena">x</td>
                            </tr>
                        </tfoot>
                        <tbody id="stavke">
                            <c:forEach var="KosaricaViewModel" items="${kosarica}" >
                                <tr>
                                    <td>
                                        <div data-id="${KosaricaViewModel.getStavka()}" id="idProizvod">
                                            ${KosaricaViewModel.getNaziv()}
                                        </div>

                                    </td>
                                    <td>
                                        <input data-id="${KosaricaViewModel.getStavka()}" id="kolicina${KosaricaViewModel.getStavka()}" class="tdKolicina" type="number" step="1" min="1" value="${KosaricaViewModel.getKolicina()}"/>
                                    </td>
                                    <td>
                                        <p class="tdCijena" id="cijena${KosaricaViewModel.getStavka()}">${KosaricaViewModel.getCijena()}</p>            
                                    </td>
                                     <td>
                                        <p class="tdCijenaUkupno" id="cijena${KosaricaViewModel.getStavka()}">${KosaricaViewModel.getCijena()*KosaricaViewModel.getKolicina()}</p>            
                                    </td>
                                    <td>
                                        <button class="btn btn-primary" type="button" id="btnUredi" value="${KosaricaViewModel.getStavka()}" >Uredi</button>
                                    </td>
                                    <td>
                                        <button class="btn btn-danger" type="button" id="btnObrisi" value="${KosaricaViewModel.getStavka()}">Obriši</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
                <div class="col-md-6 center-block" style="float: none;text-align: right">
                    <button class="btn btn-primary" id="btnZavrsiKupnju" >Završi kupnju</button>
                </div>

            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(function () {
            $('.footable').footable();

            $("#stavke").
                    on("click", "#btnUredi", function () {
                        var proizvodID = $(this).val();
                        var kolicina = $("#kolicina" + proizvodID).val();
                        var cijena = $("#cijena" + proizvodID).html();
                        console.log(cijena);
                        if (kolicina < 1) {
                            alert("Kolicina mora biti barem 1")
                            return;
                        }
                        $.ajax({
                            url: 'KosaricaUredi',
                            type: 'POST',
                            data: {
                                'proizvodID': proizvodID,
                                'kolicina': kolicina,
                                'cijena': cijena
                            },
                            success: function () {
                                if ("success" == "success") {
                                    $.notify("OK", "success");
                                    location.reload();
                                } else {
                                    $.notify(data.message, "error");
                                }

                            },
                            error: function (xhr, status, error) {
                                $.notify("Pogreška", "error");
                            }
                        });
                    });

            $("#stavke").
                    on("click", "#btnObrisi",
                            function () {
                                var proizvodID = $(this).val();
                                $.ajax({
                                    url: 'KosaricaMakni',
                                    type: 'POST',
                                    data: {
                                        'proizvodID': proizvodID
                                    },
                                    success: function () {
                                        if ("success" == "success") {
                                            $.notify("OK", "success");
                                            location.reload();
                                        } else {
                                            $.notify(data.message, "error");
                                        }

                                    },
                                    error: function (xhr, status, error) {
                                        $.notify("Pogreška", "error");
                                    }
                                });
                            });

            $("#btnObrisiSve").click(function () {
                $.ajax({
                    url: 'KosaricaMakniSve',
                    type: 'POST',
                    success: function () {
                        if ("success" == "success") {
                            location.reload();
                        } else {
                            $.notify("Greška", "error");
                        }

                    },
                    error: function (xhr, status, error) {
                        $.notify("Pogreška", "error");
                    }
                });

            });

            $("#btnZavrsiKupnju").click(function () {
                var cijenaUkupno = $("#thUkupnoCijena").html();
                var Data = {
                    cijenaUkupno: cijenaUkupno,
                    stavke: []
                };

                $('#tablicaProizvoda > tbody  > tr').each(function ()
                {
                    Data.stavke.push({
                        proizvodID: $(this).find("#idProizvod").data("id"),
                        kolicina: $(this).find(".tdKolicina").val(),
                        cijenaKomad: $(this).find(".tdCijena").html()
                    });
                });

                $.ajax({
                    url: 'KosaricaZavrsiKupnju',
                    type: 'POST',
                    dataType: "json",
                    data: {"Data": JSON.stringify(Data)},
                    success: function () {
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        location.reload();
                        $.notify("Pogreška", "error");
                    }
                });

            });

            function ukupnoKolicina() {
                var ukupno = 0;
                $('.tdKolicina').each(function () {
                    ukupno += parseInt($(this).val());
                    $("#thUkupnoKolicina").html(ukupno);
                });
            }

            function ukupnoCijena() {
                var ukupno = 0;
                $('.tdCijenaUkupno').each(function () {

                    //var kolicina = $("").data();
                    ukupno += parseInt($(this).html());
                    $("#thUkupnoCijena").html(ukupno);
                });
            }

            ukupnoKolicina();
            ukupnoCijena();
        });

    </script>
</html>


