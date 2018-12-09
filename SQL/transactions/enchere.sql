/*

L'enchère faite par un Utilisateur sur un produit mis en vente dans une
Salle de vente.

*/

Insert into Enchere(prixAchat, dateE, qttproduit, validite) values(8, SYSDATE, 5, 1);
Insert into Encherir values(1, 'harry.potter@hogwarts.scot', 1);
commit;

select nom, prenom, nomProduit, prixAchat, qttProduit from Enchere
join Encherir on Enchere.idEnchere = Encherir.idEnchere
join Utilisateur on Utilisateur.email = Encherir.email
join Vente on Vente.idVente = Encherir.idVente
join Produit on Produit.idProduit = Vente.idProduit;
