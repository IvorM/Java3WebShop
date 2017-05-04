<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prijava</title>
        <link href="content/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div style="text-align: center">
                <div class="row">
                    <div class="col-md-12" style="text-align: center">
                        <h2>Prijava</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12" style="text-align: center">
                        <center>
                            <form style="margin: 0 auto" method="post" action="prijava">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Korisničko ime" id="username" name="username" aria-describedby="basic-addon1">
                                </div>

                                <div class="input-group">
                                    <input type="passworod" class="form-control" placeholder="Lozinka" id="passwd" name="passwd" aria-describedby="basic-addon2">                            
                                </div>

                                <div class="input-group">
                                    <input type="submit" value="Prijava" class="btn btn-primary" style="margin-top: 10px" />
                                </div>
                            </form>
                        </center>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
