insert into grupakrwi(nazwa) values ('AB+'), ('AB-'), ('A+'), ('A-'), ('B+'), ('B-'), ('0+'), ('0-');

insert into dawca(imie, nazwisko, grupa_krwi_id) values
  ('Adam', 'Mickiewicz', 2),
  ('Marzena', 'Przygoda', 5),
  ('Karolina', 'Mickiewicz', 1),
  ('Tomasz', 'Więckowski', 3),
  ('Dominika', 'Kaleta', 6),
  ('Patryk', 'Kowalski', 8),
  ('Cezary', 'Baryka', 1);

insert into pielegniarka(imie, nazwisko) values
  ('Izabela', 'Wałęga'),  
  ('Patrycja', 'Wyspiańska'),
  ('Monika', 'Pomocna'),
  ('Karolina', 'Duda'),
  ('Paulina', 'Pogodzińska'),
  ('Katarzyna', 'Wielka');

insert into pobraniekrwi (ilosc, date, pielegniarka_id, dawca_id) values
  (11,'2019-01-28', 4, 2),
  (21,'2019-01-11', 1, 5),
  (12,'2019-01-15', 2, 7),
  (16,'2019-01-18', 5, 3),
  (18,'2019-11-19', 6, 1),
  (31,'2019-11-22', 3, 3),
  (31,'2019-01-02', 6, 10),
  (24,'2019-11-25', 7, 4),
  (15,'2019-01-23', 1, 6);