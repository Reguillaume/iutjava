--Supprime les tables et types existants.
drop table ob_stage;
drop table ob_entreprise;
drop table ob_etudiant;
drop table statistiques;
drop type ob_stage_ty;
drop type ob_etudiant_ty;
drop type ob_entreprise_ty;

--Création du type etudiant.
create or replace type ob_etudiant_ty as object (
id number,
nom varchar(20),
prenom varchar(20),
anneeInscription number,
map member function tri return number );
/

create or replace type body ob_etudiant_ty is
map member function tri return number is
  begin
    return id;
  end tri;
end;
/

--Création du type entreprise.
create or replace type ob_entreprise_ty as object (
id number,
nom varchar(20),
ville varchar(20),
departement varchar(20),
map member function tri return number );
/

create or replace type body ob_entreprise_ty is
map member function tri return number is 
  begin
    return id;
  end tri;
end;
/

--Création du type stage.
create or replace type ob_stage_ty as object (
id number,
dateDebut date,
dateFin date,
etudiant ref ob_etudiant_ty,
entreprise ref ob_entreprise_ty,
map member function tri return number );
/

create or replace type body ob_stage_ty is
map member function tri return number is
  begin
    return id;
  end tri;
end;
/

--Création des tables.
create table ob_etudiant of ob_etudiant_ty(id primary key);
/

create table ob_entreprise of ob_entreprise_ty(id primary key);
/

create table ob_stage of ob_stage_ty(id primary key);
/

create table Statistiques(nbEtudiantAvecStage number, nbEtudiantSansStage number);
/