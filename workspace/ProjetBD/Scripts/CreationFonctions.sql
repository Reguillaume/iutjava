--Récupère le nombre d'étudiant avec stage cette année.
create or replace function nbEtudiantAvecStage return number is
res number;
begin
select count(*) into res
from ob_etudiant et
where ref(et) IN (select s.etudiant from ob_stage s)
AND extract(year from (select dateDebut from ob_stage s where s.etudiant=ref(et)))=extract(year from sysdate);
return res;
end;
/

--Récupère le nombre d'étudiants sans stage cette année.
create or replace function nbEtudiantSansStage return number is
res number;
begin
select count(*) into res
from ob_etudiant et
where ref(et) NOT IN (select s.etudiant from ob_stage s)
AND et.anneeInscription=extract(year from sysdate);
return res;
end;
/

--Récupère le nombre d'étudiant sans stage à une année donnée.
create or replace function nbEtudiantSansStageAnnee(annee number) return number is
res number;
begin
select count(*) into res
from ob_etudiant et
where ref(et) NOT IN (select s.etudiant from ob_stage s where extract(year from dateDebut)=annee)
AND anneeInscription=annee;
return res;
end;
/

--Récupère le nombre de stagiaires pris par chaque entreprise dans les n dernières années.
create or replace procedure nbStagiaireEntreprise(n in number, res out sys_refcursor) as
begin
open res for
select deref(s.entreprise).nom , count(*)
from ob_stage s
where s.dateDebut > sysdate - (365*n)
group by deref(s.entreprise).nom;
end;
/

--Récupère le nombre moyen de stagiaire encadrés par les entreprises dans les n dernières années.
create or replace function nbMoyenStagiaireEntreprise(n in number) return number is
res number:=0;
cpt number:=0;
cursor monCurs IS
select count(*) as nbEtudiant
from ob_stage s
where dateDebut > sysdate - (365*n);
begin
for ligne in monCurs loop
cpt := cpt+1;
res := res+ligne.nbEtudiant;
end loop;
res := res/cpt;
return res;
end;
/

--Récupère le nombre de stages par zone géographique choisi par l'utilisateur.
create or replace function nbStageZone(ville in varchar, departement in varchar) return number is
res number;
begin
  select count(*) into res
  from ob_stage s
  where deref(s.entreprise).ville=ville
  and deref(s.entreprise).departement=departement;
  return res;
end;
/

--Récupère le nombre de stages pour toutes les zones géographiques.
create or replace function nbStageZone(ville in varchar, departement in varchar) return number is
res number:=0;
begin
  select count(*) into res
  from ob_stage s
  where deref(entreprise).ville=ville
  and deref(entreprise).departement=departement;
return res;
end;
/

--Récupère toutes les entreprises et leur contact ayant eu au moins un stage dans les n dernières années.
create or replace procedure contactEntrepriseAnnee(n in number, res out sys_refcursor) as
begin
open res for
select deref(s.entreprise).nom, deref(s.etudiant).nom, deref(s.etudiant).prenom
from ob_stage s
where dateDebut > sysdate - (365*n)
group by deref(s.entreprise).nom, deref(s.etudiant).nom, deref(s.etudiant).prenom;
end;
/