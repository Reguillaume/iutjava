--Supprime le contenu des tables existantes.
DELETE FROM ob_entreprise;
DELETE FROM ob_etudiant;
DELETE FROM ob_stage;
DELETE FROM STATISTIQUES;

--Remplissage de la table entreprise.
INSERT INTO ob_entreprise
VALUES(1,'BNP Paribas', 'Paris', 'Paris');

INSERT INTO ob_entreprise
VALUES(2,'RATP', 'Palaiseau', 'Essonne');

INSERT INTO ob_entreprise
VALUES(3,'Polo Ralph Lauren', 'Paris', 'Paris');

INSERT INTO ob_entreprise
VALUES(4,'Appartoo', 'Paris', 'Paris');

INSERT INTO ob_entreprise
VALUES(5,'Improveus', 'Paris', 'Paris');

INSERT INTO ob_entreprise
VALUES(6,'iKNSA', 'Cergy', 'Val-d Oise');

INSERT INTO ob_entreprise
VALUES(7,'La Meduse', 'Paris', 'Paris');

INSERT INTO ob_entreprise
VALUES(8,'Route des Hommes', 'Paris', 'Paris');

INSERT INTO ob_entreprise
VALUES(9,'Netysoft', 'Bievres', 'Essonne');

--Remplissage de la table etudiant.
INSERT INTO ob_etudiant
VALUES(1, 'ZAPATO', 'Michel', 2015);

INSERT INTO ob_etudiant
VALUES(2, 'DAGOBERT', 'Didier', 2016);

INSERT INTO ob_etudiant
VALUES(3, 'BOMBUT', 'Tristan', 2015);

INSERT INTO ob_etudiant
VALUES(4, 'HIERNE', 'Jerome', 2015);

INSERT INTO ob_etudiant
VALUES(5 , 'BOMBUT', 'Christian', 2015);

INSERT INTO ob_etudiant
VALUES(6, 'PAQUITO', 'Ernesto', 2016);

INSERT INTO ob_etudiant
VALUES(7, 'TRILARD', 'Thibaud', 2016);

INSERT INTO ob_etudiant
VALUES(8, 'FERASSE', 'Léonard', 2016);

INSERT INTO ob_etudiant
VALUES(9, 'BALET', 'Robert', 2016);

INSERT INTO ob_etudiant
VALUES(10, 'VALEMONTRE', 'Arthur', 2016);

INSERT INTO ob_etudiant
VALUES ('11', 'HENRY', 'Thierry', 2016);

INSERT INTO ob_etudiant
VALUES ('12', 'BOND', 'James', 2016);

INSERT INTO ob_etudiant
VALUES ('13', 'POGBA', 'Paul', 2016);

INSERT INTO ob_etudiant
VALUES ('14', 'AURIER', 'Serge', 2016);

--Remplissage de la table stage
INSERT INTO ob_stage
SELECT 1, TO_DATE('15/09/2015','DD/MM/YY'), TO_DATE('15/11/2015','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='ZAPATO' AND et.prenom='Michel'
AND en.nom='BNP Paribas';

INSERT INTO ob_stage
SELECT 2, TO_DATE('09/01/2016','DD/MM/YY'), TO_DATE('09/03/2016','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='DAGOBERT' AND et.prenom='Didier'
AND en.nom='RATP';

INSERT INTO ob_stage
SELECT 3, TO_DATE('27/12/2015','DD/MM/YY'), TO_DATE('27/02/2016','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='BOMBUT' AND et.prenom='Tristan'
AND en.nom='Appartoo';

INSERT INTO ob_stage
SELECT 4, TO_DATE('11/12/2015','DD/MM/YY'), TO_DATE('11/02/2016','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='HIERNE' AND et.prenom='Jerome'
AND en.nom='Appartoo';

INSERT INTO ob_stage
SELECT 5, TO_DATE('06/10/2015','DD/MM/YY'), TO_DATE('06/12/2015','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='BOMBUT' AND et.prenom='Christian'
AND en.nom='Improveus';

INSERT INTO ob_stage
SELECT 6, TO_DATE('23/01/2016','DD/MM/YY'), TO_DATE('23/03/2016','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='PAQUITO' AND et.prenom='Ernesto'
AND en.nom='RATP';

INSERT INTO ob_stage
SELECT 7, TO_DATE('29/01/2016','DD/MM/YY'), TO_DATE('29/03/2016','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='TRILARD' AND et.prenom='Thibaud'
AND en.nom='iKNSA';

INSERT INTO ob_stage
SELECT 8, TO_DATE('01/01/2016','DD/MM/YY'), TO_DATE('01/03/2016','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='FERASSE' AND et.prenom='Léonard'
AND en.nom='La Meduse';

INSERT INTO ob_stage
SELECT 9, TO_DATE('03/02/2016','DD/MM/YY'), TO_DATE('03/05/2016','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='BALET' AND et.prenom='Robert'
AND en.nom='Route des Hommes';

INSERT INTO ob_stage
SELECT 10, TO_DATE('12/01/2016','DD/MM/YY'), TO_DATE('12/03/2016','DD/MM/YY'), ref(et), ref(en)
FROM ob_etudiant et, ob_entreprise en
WHERE et.nom='VALEMONTRE' AND et.prenom='Arthur'
AND en.nom='Netysoft';

--Remplissage de la table statistiques
insert into statistiques
values(nbEtudiantAvecStage(), NBETUDIANTSANSSTAGE());