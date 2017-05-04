<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registracija</title>
        <link href="content/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-3.1.0.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.min.js" type="text/javascript"></script>
        <script src="scripts/notify.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div style="text-align: center">
                <div class="row">
                    <div class="col-md-12" style="text-align: center">
                        <h2>Registracija</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12" style="text-align: center">
                        <center><form id="registracijaForm" style="margin: 0 auto" action="Registracija" method="post">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="username" name="username" placeholder="Korisničko ime" aria-describedby="basic-addon1">
                                </div>

                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Lozinka" id="passwd" name="passwd" aria-describedby="basic-addon2">                            
                                </div>

                                <div class="input-group">
                                    <input type="submit" value="Registracija" class="btn btn-primary" style="margin-top: 10px" />
                                </div>
                            </form>
                        </center>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
                $(function () {

                $("#registracijaForm")
                        .validate({
                        rules: {
                        username: {
                        required: true
                        },
                                passwd: {
                                required: true,
                                        minlength: 4
                                }
                        },
                                messages: {
                                username: {
                                required: 'Korisničko ime je obavezno polje!',
                                        remote:'Korisničko ime se koristi'
                                },
                                        passwd: {
                                        required: 'Lozinka ime je obavezno polje!',
                                                minlength:'Barem 4!'
                                        }
                                }
                        });
                        //treba bih dodati nekakvu animaciju, da korisnik zna da se nesto dogada
                        $('#registracijaForm')
                        .submit(function (e) {
                        e.preventDefault();
                                if (!$("#registracijaForm").valid()) {
                        return;
                        };
                                $.ajax({
                                url: 'Registracija',
                                        type: 'POST',
                                        dataType: 'json',
                                        data: {
                                        'username':$('#username').val(),
                                                'passwd':$('#passwd').val()
                                        },
                                        success: function (data) {
                                        if (data.status == "OK") {
                                        $.notify("Uspješno ste se registrirali", "success");
                                        } else {
                                        $.notify("Greška prilikom registracije", "error");
                                        }

                                        },
                                                error: function (xhr, status, error) {
                                                $.notify("Greška prilikom registracije", "error");
                                                }
                                        });
                                        });
                                        });
                                    

    </script>
</html>

