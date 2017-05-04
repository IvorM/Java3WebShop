<%@page import="models.KupacViewModel"%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp">WebShop</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Kategorije <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="Kategorija?kategorija=1">CPU</a></li>
            <li><a href="Kategorija?kategorija=2">GPU</a></li>
            <li><a href="Kategorija?kategorija=3">HDD</a></li>
            <li><a href="Kategorija?kategorija=4">SSD</a></li>
            <li><a href="Kategorija?kategorija=5">RAM</a></li>
          </ul>
          <li><a href="kosarica.jsp">Kosarica</a></li>
         <% if (session.getAttribute("aktivniKorisnik") != null) { %>
          <% if (((KupacViewModel)session.getAttribute("aktivniKorisnik")).isAdministrator()==false) { %>
         <li><a href="PregledKupnje">Pregled kupnji</a></li>
 
         <% } else {%>
         <li><a href="PregledKupnjeSvi">Pregled svih kupnji</a></li>
         <li><a href="PrikazSpajanja">Pregled svih spajanja</a></li>
           <% }%>
        <% }%>
       
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
         <% if (session.getAttribute("aktivniKorisnik") == null) { %>
         <li><a href="prijava.jsp">Prijava</a></li>
         <li><a href="registracija.jsp">Registracija</a></li>
        <% } else {%>
         <li><a htref="#">Pozdrav <%= ((KupacViewModel)session.getAttribute("aktivniKorisnik")).getKorisnickoIme() %></a></li>
    <li><a href="odjava">Odjava</a></li>
    <% } %>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>