import api.*;
import java.sql.ResultSet;
import java.sql.Date;

public class TestClient {

    /*
     * Mise en vente d'un produit, enchere montante, non repetitivite et non revocable
     */
    public static void senario1() {
      System.out.println("\n----- Sénario 1 -----");
      System.out.println("----- Mise en vente d'un produit, enchere montante, non repetitivite et non revocable -----\n");

      System.out.println("\t(0) Les tables initiales:\n");
        SellAPI.printTable("Produit");
        SellAPI.printTable("Vente");
        SellAPI.printTable("Salle");

      System.out.println("\t(1) Mettons en vente le produit 2\n");
        // int idTypeV, String nomCat, int idProduit, int prixDep, Date date
        SellAPI.miseEnVente(2, "Moyens de transport", 2, 100, null);


      System.out.println("\t(2) Les tables au final:\n");
        SellAPI.printTable("Produit");
        SellAPI.printTable("Vente");
        SellAPI.printTable("Salle");
    }

    /*
     * Mise en vente d'un produit, enchere montante, repetitivite et revocable
     */
    public static void senario2() {
      System.out.println("\n----- Sénario 2 -----");
      System.out.println("----- Mise en vente d'un produit, enchere montante, repetitivite et revocable -----\n");

      System.out.println("\t(0) Les tables initiales:\n");
        SellAPI.printTable("Produit");
        SellAPI.printTable("Vente");
        SellAPI.printTable("Salle");
        SellAPI.printTable("Encherir"); // Ou/Et enchere ..... avoir

      System.out.println("\t(1) Mettons en vente le produit 2, prixDep == 100\n");
        // int idTypeV, String nomCat, int idProduit, int prixDep, Date date
        int idVente = SellAPI.miseEnVente(3, "Moyens de transport", 2, 100, null);
        SellAPI.printTable("Vente");

      System.out.println("\t(2) (A) encherie sur le produit 2, prix == 110\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(3) (B) encherie sur le produit 2, prix == 120\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(4) (C) encherie sur le produit 2, prix == 110\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(5) (A) encherie sur le produit 2, prix == 125\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(6) (A) encherie sur le produit 2, prix == 130\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t Le gagnant est: (A)\n");
        ResultSet rs = SellAPI.getFinEnchere(idVente);
        SellAPI.dumpResultSet(rs);

      System.out.println("\t(7) Les tables au final:\n");
        SellAPI.printTable("Produit");
        SellAPI.printTable("Vente");
        SellAPI.printTable("Salle");
        SellAPI.printTable("Encherir");
    }

    /*
     * Mise en vente d'un produit, enchere montante, repetitivite et revocable
     */
    public static void senario3() {
      System.out.println("\n----- Sénario 3 -----");
      System.out.println("----- Mise en vente d'un produit, enchere montante, non repetitivite et revocable -----\n");

      System.out.println("\t(0) Les tables initiales:\n");
        SellAPI.printTable("Produit");
        SellAPI.printTable("Vente");
        SellAPI.printTable("Salle");
        SellAPI.printTable("Encherir");

      System.out.println("\t(1) Mettons en vente le produit 1, prixDep == 100\n");
        // int idTypeV, String nomCat, int idProduit, int prixDep, Date date
        int idVente = SellAPI.miseEnVente(3, "Objets Magiques", 1, 100, null);
        SellAPI.printTable("Vente");

      System.out.println("\t(2) (A) encherie sur le produit 1, prix == 110\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(3) (B) encherie sur le produit 1, prix == 120\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(4) (C) encherie sur le produit 1, prix == 110\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(5) (A) encherie sur le produit 1, prix == 125\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(6) (A) encherie sur le produit 1, prix == 130\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(7) Le gagnant est: (B)\n");
        ResultSet rs = SellAPI.getFinEnchere(idVente);
        SellAPI.dumpResultSet(rs);

      System.out.println("\t(8) Les tables au final:\n");
        SellAPI.printTable("Produit");
        SellAPI.printTable("Vente");
        SellAPI.printTable("Salle");
        SellAPI.printTable("Encherir");
    }

    /*
     * Mise en vente d'un produit, enchere descendante
     */
    public static void senario4() {
      System.out.println("\n----- Sénario 4 -----");
      System.out.println("----- Mise en vente d'un produit, enchere descendante -----\n");

      System.out.println("\t(0) Les tables initiales:\n");
        SellAPI.printTable("Produit");
        SellAPI.printTable("Vente");
        SellAPI.printTable("Salle");
        SellAPI.printTable("Encherir");

      System.out.println("\t(1) Mettons en vente le produit 3, prixDep == 100\n");
        // int idTypeV, String nomCat, int idProduit, int prixDep, Date date
        int idVente = SellAPI.miseEnVente(4, "Objets Magiques", 3, 100, null);
        SellAPI.printTable("Vente");

      System.out.println("\t(2) (A) encherie sur le produit 3, prix == 110\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(3) (B) encherie sur le produit 3, prix == 120\n");
        // params
        // SellAPI.encherir();

      System.out.println("\t(4) Le gagnant est: (A)\n");
        ResultSet rs = SellAPI.getFinEnchere(idVente);
        SellAPI.dumpResultSet(rs);

      System.out.println("\t(5) Les tables au final:\n");
        SellAPI.printTable("Produit");
        SellAPI.printTable("Vente");
        SellAPI.printTable("Salle");
        SellAPI.printTable("Encherir");
    }

    /*
     * Mise en vente d'un produit, enchere descendante sur une sous quantité de produit
     */
    public static void senario5() {
      System.out.println("\n----- Sénario 4 -----");
      System.out.println("----- Mise en vente d'un produit, enchere descendante sur un sous lot -----\n");
      //
      // System.out.println("\t(0) Les tables initiales:\n");
      //
      // System.out.println("\t(1) Mettons en vente le produit 3, prixDep == 100\n");
      //
      // System.out.println("\t(2) L'état des tables:\n");
      //
      // System.out.println("\t(3) (A) encherie sur le produit 3, prix == 110\n");
      //
      // System.out.println("\t(4) (B) encherie sur le produit 3, prix == 120\n");
      //
      // System.out.println("\t Le gagnant est: (A)\n");
      //
      // System.out.println("\t(8) Les tables au final:\n");
    }


    public static void main(String[] args) {
        String user = "bazinj";
        String password = "bazinj";

        // Start connection
        System.out.println("Bonjour, \nChoisisser votre senario de teste!");
        SellAPI.openConnection(user, password);

//        int senarioId = Integer.parseInt(System.console().readLine());
        int senarioId = 1;
        switch (senarioId) {
          case 1: senario1(); break;
          case 2: senario2(); break;
          case 3: senario3(); break;
          case 4: senario4(); break;
          case 5: senario5(); break;
          default: System.out.println("Choisir un senario :D !");
        }

        // Close connection
        SellAPI.closeConnection();
    }
}
