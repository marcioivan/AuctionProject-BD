/*
- le processus de fin d'enchère déterminant le (ou les) Utilisateurs ayant
remporté une vente, en tenant compte du type d'enchère.
*/
/* On cherche pour l'idVente = 0 */
/* Desc */
select nom, prenom, qttProduit from Enchere
join Encherir on Enchere.idEnchere = Encherir.idEnchere
join Utilisateur on Utilisateur.email = Encherir.email
join Vente on Vente.idVente = Encherir.idVente
where validite = 1
AND Vente.idvente = 1
ORDER BY prixAchat;

commit;
