INSERT INTO prodotto(prezzo,  descrizione, foto, nome)VALUES(7,  ' Le tazze da museo sono prodotti che rappresentano o celebrano opere d arte. Le tazze sono realizzate con materiali di alta qualità come ceramica o porcellana. Immagini sulle tazze sono stampati con cura per garantire una riproduzione fedele dell opera.', '/tazza-museo.png', 'Tazze museo egizio');
INSERT INTO prodotto(prezzo,  descrizione, foto, nome)VALUES(20,  'Benvenuto nella nostra sezione libri, un tesoro di conoscenza che ti accompagna in un viaggio attraverso le epoche e le culture. Scegli tra una varietà di opere che si fondono armoniosamente con la vastità e la diversità delle nostre collezioni. ', 'https://i.pinimg.com/originals/97/bd/c4/97bdc4bdda3c33e762a96637db28b61c.jpg', 'Libri Di Storia e Epica');
INSERT INTO prodotto(prezzo,  descrizione, foto, nome)VALUES(10,  'I poster si presentano come un’affascinante fusione di arte e informazione. Raffigurano le opere d’arte più famose e importanti.  Con sfondi sfumati che evocano un senso di mistero e scoperta, il design complessivo emana un\'aura di eleganza. ', 'https://i.pinimg.com/originals/5f/60/70/5f607049abb0386f2637fce208ce3342.jpg', 'Poster rinascimentali');
INSERT INTO prodotto(prezzo,  descrizione, foto, nome)VALUES(30,  'Le repliche di statue sono riproduzioni artistiche di opere originali, realizzate per emulare l\'aspetto e i dettagli delle creazioni originali. Queste repliche possono essere realizzate in diversi materiali e possono variare nelle dimensioni.', '/statua-arte.png', 'Repliche statue');
INSERT INTO prodotto(prezzo,  descrizione, foto, nome)VALUES(25,  'Un set completo di gioielli che fondono design contemporaneo con lusso senza tempo. Ogni pezzo è caratterizzato da linee pulite e materiali di alta qualità, creando un insieme armonioso che si adatta ad ogni occasione.', '/gioielli-arte.png', 'Gioielli antichi');
INSERT INTO prodotto(prezzo, descrizione, foto, nome)VALUES(15,  'Immergiti in un\'avventura unica nel suo genere con il nostro puzzle "Viaggio nel Tempo". Questo puzzle è progettato per portarti attraverso le epoche storiche e ti sfiderà a collegare i pezzi del passato in un affascinante mosaico di conoscenza.', '/puzzle-arte.png', 'Puzzle a tema');
INSERT INTO prodotto(prezzo,  descrizione, foto, nome)VALUES(8,  'Le cartoline del museo sono progettate per catturare l\'essenza delle opere d\'arte esposte. Presentano immagini di opere d\'arte famose, sculture, reperti storici. Le cartoline di un museo contengono informazioni sulla storia delle opere d\'arte.', '/cartoline-arte.png', 'Cartoline');
INSERT INTO prodotto(prezzo,  descrizione, foto, nome)VALUES(35,  'I modelli architettonici esposti in un museo sono parte di mostre che mirano a presentare l\'architettura in tutte le sue sfaccettature. I modelli architettonici sono rappresentazioni tridimensionali di progetti architettonici, realizzate in scala.', '/TEMPIO-EGIZIO-modellino.png', 'Modellini architettonici');
INSERT INTO prodotto(prezzo,  descrizione, foto, nome) VALUES (60,  'Il vaso artistico è un capolavoro che cattura l\'attenzione degli spettatori con la sua eleganza e la sua bellezza intrinseca. Realizzato con una maestria artigianale impeccabile, il vaso si presenta come un\'armoniosa fusione di forme e colori. ', '/vaso-arte.png', 'Vaso Antico');
INSERT INTO prodotto(prezzo,  descrizione, foto, nome) VALUES (180, 'La tela d\'arte moderna cattura immediatamente l\'attenzione degli spettatori con la fusione di colori, forme e concetti. Il colore è uno degli elementi chiave di questa opera d\'arte moderna', '/tela-moderna.png', 'Tela d\'Arte Moderna');
INSERT INTO `role` (name) VALUES('ADMIN');
INSERT INTO `role` (name) VALUES('USER');
INSERT INTO museum_user (email, first_name, last_name, password) VALUES('Admin', 'Jane', 'Doe', '{noop}admin');
INSERT INTO museum_user (email, first_name, last_name, password) VALUES('User', 'John', 'Doe', '{noop}user');
INSERT INTO museum_user (email, first_name, last_name, password) VALUES('User2', 'gina', 'bellina', '{noop}user2');
INSERT INTO museum_user_role_set (museum_user_id, role_set_name) VALUES(1, 'ADMIN');
INSERT INTO museum_user_role_set (museum_user_id, role_set_name) VALUES(2, 'USER');
INSERT INTO museum_user_role_set (museum_user_id, role_set_name) VALUES(3, 'USER');
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Tazze museo egizio', 'Ceramic industries', 2, 200, 1);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Libri di Storia e Epica', 'libreria antica', 7, 200,2);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Poster rinascimentali', 'History art', 3, 200, 3);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Repliche statue', 'Ceramic industries', 14, 200, 4);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Gioielli antichi', 'Ceramic industries', 6, 200, 5);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Puzzle a tema', 'jewerly industries', 3, 200, 6);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Cartoline', 'libreria antica ', 1, 200, 7);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Modelli archittetonici', 'Ceramic industries', 22, 200, 8);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Vaso antico', 'Ceramic industries', 34, 200, 9);
INSERT INTO assortimento(data_assortimento, nome, nome_fornitore, prezzo_singolo, quantita_acquistata, prodotto_id)VALUES('2024-01-28', 'Vela d\'arte moderna  ',' libreria antica', 89, 200, 10);
INSERT INTO acquisto(codice, data_acquisto, nome, prezzo_singolo, quantita, prodotto_id, user_id)VALUES(234535, '2024-01-29', 'Tazze', 7, 10, 1, 1);
INSERT INTO acquisto(codice, data_acquisto, nome, prezzo_singolo, quantita, prodotto_id, user_id)VALUES(234535, '2024-01-29', 'Repliche statue', 30, 50, 4, 3);
INSERT INTO acquisto(codice, data_acquisto, nome, prezzo_singolo, quantita, prodotto_id, user_id)VALUES(234535, '2024-01-29', 'Libri di Storia e Epica', 20, 200, 2, 2);
INSERT INTO acquisto(codice, data_acquisto, nome, prezzo_singolo, quantita, prodotto_id, user_id)VALUES(234535, '2024-01-29', 'Repliche statue', 30, 300, 4, 3);




