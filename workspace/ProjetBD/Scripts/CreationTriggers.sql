create or replace trigger TRG_en
after insert or update on ob_entreprise
for each row
begin
update Statistiques
set nbEtudiantAvecStage=nbEtudiantAvecStage(),
nbEtudiantSansStage=nbEtudiantSansStage();
end;
/

create or replace trigger TRG_et
after insert or update on ob_etudiant
for each row
begin
update Statistiques
set nbEtudiantAvecStage=nbEtudiantAvecStage(),
nbEtudiantSansStage=nbEtudiantSansStage();
end;
/

