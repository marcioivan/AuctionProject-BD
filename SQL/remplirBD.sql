
/* Instructions pour remplir la base de donnée au début */


Insert into Utilisateur values ('harry.potter@hogwarts.scot', 'Harry', 'Potter', 'Gryffindor');
Insert into Utilisateur values ('hermione.granger@hogwarts.scot', 'hermione', 'Granger', 'Gryffindor');
Insert into Utilisateur values ('ronald.weasley@hogwarts.scot', 'Ronald', 'Weasley', 'Gryffindor');
Insert into Utilisateur values ('draco.malfoy@hogwarts.scot', 'Draco', 'Malfoy', 'Slytherin');
Insert into Utilisateur values ('tom.riddle@hogwarts.scot', 'Tom', 'Riddle', 'Slytherin');
Insert into Utilisateur values ('albus.dumbledore@hogwarts.scot', 'Albus', 'Dumbledore', 'Gryffindor');
Insert into Utilisateur values ('minerva.mcgonagall@hogwarts.scot', 'Minerva', 'Mcgonagall', 'Gryffindor');
Insert into Utilisateur values ('severus.snape@hogwarts.scot', 'Severus', 'Snape', 'Slytherin');
Insert into Utilisateur values ('sirius.black@azkaban.scot', 'Sirius', 'Black', 'Gryffindor');
Insert into Utilisateur values ('rubeus.hagrid@hogwarts.scot', 'Rubeus', 'Hagrid', 'Gryffindor');
Insert into Utilisateur values ('luna.lovegood@hogwarts.scot', 'Luna', 'Lovegood', 'Ravenclaw');
Insert into Utilisateur values ('ginerva.weasley@hogwarts.scot', 'Ginevra', 'Weasley', 'Gryffindor');
Insert into Utilisateur values ('neville.longbottom@hogwarts.scot', 'Neville', 'Longbottom', 'Gryffindor');
Insert into Utilisateur values ('bellatrix.black@azkaban.scot', 'Bellatrix', 'Black', 'Slytherin');


Insert into Categorie values ('Friandises', 'Bonbons et friandises en cas de faim');
Insert into Categorie values ('Objets Magiques', 'Objets divers à caractéristique magique');
Insert into Categorie values ('Magie Noire', 'Objets dangereux et puissants, à utiliser avec précaution');
Insert into Categorie values ('Moyens de transport', 'Objets permettant de se déplacer dans le monde magique');


Insert into Produit(nomProduit, prixRevient, stock, email) values ('Déluminateur', 150, 5, 'albus.dumbledore@hogwarts.scot');
Insert into Produit(nomProduit, prixRevient, stock, email) values ('Pensine', 10000, 1, 'albus.dumbledore@hogwarts.scot');
Insert into Produit(nomProduit, prixRevient, stock, email) values ('Nimbus 2000', 600, 3, 'harry.potter@hogwarts.scot');
Insert into Produit(nomProduit, prixRevient, stock, email) values ('Portoloin', 2000, 200, 'severus.snape@hogwarts.scot');
Insert into Produit(nomProduit, prixRevient, stock, email) values ('Moto volante', 5000, 1, 'sirius.black@azkaban.scot');
Insert into Produit(nomProduit, prixRevient, stock, email) values ('Chocogrenouille', 9000, 3000, 'rubeus.hagrid@hogwarts.scot');
Insert into Produit(nomProduit, prixRevient, stock, email) values ('Fizwizbiz', 50, 50, 'ginerva.weasley@hogwarts.scot');
Insert into Produit(nomProduit, prixRevient, stock, email) values ('Horcruxe', 1000000, 7, 'tom.riddle@hogwarts.scot');

Insert into Categorieproduit(idProduit, nomCat) values (1, 'Objets Magiques');
Insert into Categorieproduit(idProduit, nomCat) values (2, 'Objets Magiques');
Insert into Categorieproduit(idProduit, nomCat) values (3, 'Moyens de transport');
Insert into Categorieproduit(idProduit, nomCat) values (4, 'Objets Magiques');
Insert into Categorieproduit(idProduit, nomCat) values (5, 'Moyens de transport');
Insert into Categorieproduit(idProduit, nomCat) values (6, 'Friandises');
Insert into Categorieproduit(idProduit, nomCat) values (7, 'Friandises');
Insert into Categorieproduit(idProduit, nomCat) values (8, 'Magie Noire');

Insert into TypeVente(typeV, repetitivite, revocable, dateV) values ('montante', 1, 0, NULL);
Insert into TypeVente(typeV, repetitivite, revocable, dateV) values ('montante', 0, 1, NULL);
Insert into TypeVente(typeV, repetitivite, revocable, dateV) values ('montante', 0, 0, NULL);
Insert into TypeVente(typeV, repetitivite, revocable, dateV) values ('montante', 1, 1, NULL);
Insert into TypeVente(typeV, repetitivite, revocable, dateV) values ('descendante', 1, 0, NULL);
Insert into TypeVente(typeV, repetitivite, revocable, dateV) values ('descendante', 0, 1, NULL);
Insert into TypeVente(typeV, repetitivite, revocable, dateV) values ('descendante', 0, 0, NULL);
Insert into TypeVente(typeV, repetitivite, revocable, dateV) values ('descendante', 1, 1, NULL);
