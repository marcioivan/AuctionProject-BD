/*
- La mise en place d une Salle de Vente et la sélection de produits déjà
disponibles à la vente et permettant le choix du type d'enchères et du prix
de départ.
*/

/*
Actuellement le type d'enchère est montante, répétitive et non revocable à duréee illimité.
Le prix du départ du produit 0 est de 5e
*/


Insert into Salle(idTypeV, nomCat) values (1, 'Moyens de transport');
Insert into Vente(idSalle, idProduit, prixDep) values (1, 3, 5);
Commit;

select Salle.idSalle, nomProduit from Salle
  join Vente on Salle.idSalle = Vente.idSalle
  join Produit on Vente.idProduit = Produit.idProduit;
