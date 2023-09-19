-- select tests
INSERT INTO MocktailEntity (id, name, beschreibung) 
VALUES (1, 'Sex on the couch', 'Kein Alkohol, nur Saft');
INSERT INTO MocktailEntity (id, name, beschreibung) 
VALUES (2, 'Short island icetea', 'Hauptsache viel Zucker');
INSERT INTO ZutatEntity (id, name, menge, einheit, mocktail_id)
VALUES (1, 'Limetten', 3, 3, 2);

-- delete tests
INSERT INTO MocktailEntity (id, name, beschreibung) 
VALUES (3, 'Mini Mojito', 'Viele Limetten');

-- modify tests
INSERT INTO MocktailEntity (id, name, beschreibung) 
VALUES (4, 'Jumbo Jet', 'mit Alkohol');
INSERT INTO MocktailEntity (id, name, beschreibung) 
VALUES (5, 'Caipi', 'Rohrzucker ist das Beste');

ALTER SEQUENCE mocktail_id_seq RESTART WITH 6;
ALTER SEQUENCE zutat_id_seq RESTART WITH 2;