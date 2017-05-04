CREATE DATABASE WebShop
GO

USE WebShop
GO


-- Structure
create table Kategorija
(
    IDKategorija int primary key identity(1,1),
	Naziv nvarchar(50)
)
GO

create table Proizvod
(
    IDProizvod int primary key identity(1,1),
    Naziv nvarchar(50) not null unique,
	Cijena decimal not null,
	KategorijaID int not null foreign key references Kategorija(IDKategorija)
)
GO

create table Kupac
(
	IDKupac int primary key identity(1,1),
	KorisnickoIme nvarchar(50) unique,
	Lozinka nvarchar(50)
)
GO

create table Racun
(
	IDRacun int primary key identity(1,1),
	KupacID int not null foreign key references Kupac(IDKupac)
)
GO
create table  Stavka
(
	IDStavka int primary key identity(1,1),
	RacunID int foreign key references Racun(IDRacun),
	ProizvodID int foreign key references Proizvod(IDProizvod),
	Kolicina int not null,
	CijenaKomad decimal not null,
	CijenaUkupno decimal not null
)
GO

create table Logging
(
	Tekst nvarchar(300)
)
GO

-- Data
insert into kategorija
values ('CPU'),('GPU'),('HDD'),('SSD'),('RAM')

insert into proizvod
values 
('IntelProc','1500','1'),
('AMDProc','1500','1'),
('NvidiaGrafa','1500','2'),
('AMDGrafa','1500','2'),
('Toshiba1TB','1500','3'),
('Hitachi1TB','1500','3'),
('SamsungEVO250GB','1500','4'),
('HyperX250GB','1500','4'),
('HyperX8GB','1500','5'),
('CrucialX8GB','1500','5')
go

-- CRUD

--GET
CREATE PROC GetProizvodi
AS
SELECT TOP 10 * FROM Proizvod
go

CREATE PROC GetRacuni
AS
SELECT TOP 10 * FROM Racun
go

CREATE PROC GetKupci
AS
SELECT TOP 10 * FROM Kupac
go

CREATE PROC GetKategorije
AS
SELECT TOP 10 * FROM Kategorija
go

CREATE PROC GetStavkeRacuna
	@IDRacun int
AS
SELECT * FROM Stavka AS S
WHERE S.RacunID = @IDRacun
go

CREATE PROC GetRacuniKupca
	@IDKupac int
AS
SELECT * FROM Racun AS r
WHERE r.KupacID = @IDKupac
go

CREATE PROC GetProizvodiKategorije
	@IDKategorija int
AS
SELECT * FROM Proizvod AS p
WHERE p.KategorijaID = @IDKategorija
go

-- INSERT

CREATE PROC InsertRacun
	@IDRacun int OUTPUT,
	@kupacID  int
AS
INSERT INTO Racun
VALUES (@kupacID)
SET @IDRacun = SCOPE_IDENTITY();
RETURN 0
GO

CREATE PROC InsertStavka
	@RacunID int,
	@ProizvodID int,
	@Kolicina int,
	@CijenaKomad decimal,
	@CijenaUkupno decimal
AS
INSERT INTO Stavka
VALUES (@RacunID, @ProizvodID,@Kolicina,@CijenaKomad,@CijenaUkupno)
RETURN 0
GO


CREATE PROC InsertKupac
	@KorisnickoIme nvarchar(50),
	@Lozinka nvarchar(50)
AS
INSERT INTO Kupac
VALUES (@KorisnickoIme, @Lozinka)
RETURN 0
GO

CREATE PROC InsertLog
	@Message nvarchar(300)
AS
INSERT INTO Logging
VALUES (@Message)
RETURN 0
GO