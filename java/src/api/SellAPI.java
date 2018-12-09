package api;

import java.sql.*;
import oracle.jdbc.driver.OracleDriver;

public class SellAPI {
    static final String CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    private static Connection connection;
    private static PreparedStatement command;
    private static ResultSet result;


    public static void openConnection(String user, String passwd) {
        try {
            // Enregistrement du driver Oracle
            System.out.print("Loading Oracle driver... ");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Loaded");
            // Etablissement de la connection
            System.out.print("Connecting to the database... ");
            connection = DriverManager.getConnection(CONN_URL, user, passwd);
            System.out.println("Connected");

            connection.setAutoCommit(false);
            System.out.println("\nAutoCommit set to false\n");

        } catch (SQLException e) {
            System.err.println("Failed");
            e.printStackTrace(System.err);
        }
    }

    public static void closeConnection() {
        try {
            if (result != null) {
                result.close();
            }
            if (command != null) {
                command.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    public static void dumpResultSet(ResultSet result) {

        if(result == null) {
            System.out.println("No results.");
            return;
        }

        try {
            ResultSetMetaData rsetmd = result.getMetaData();
            int i = rsetmd.getColumnCount();
            for (int k=1; k<=i; k++) System.out.print(rsetmd.getColumnName(k) + "\t");

            System.out.println();

            while (result.next()) {
                for (int j = 1; j <= i; j++) System.out.print(result.getString(j) + "\t");
                System.out.println();
            }

            System.out.println();

        } catch (SQLException e) {
            System.err.println("Failed to get result");
            e.printStackTrace(System.err);
        }
    }

//    private int validateEncherir(String utilisateurMail, int venteId, long prixAchat, int qttProduit) {
//        String statement = "SELECT prixAchat, qttProduit "
//                + "FROM Enchere ";
//    }

    public static int encherir(String utilisateurMail, int venteId, long prixAchat, int qttProduit) {
        int idEncherir = -1;
        try {

            // Validation of encherir
//            int validite = validateEncherir(utilisateurMail, venteId, prixAchat, qttProduit);

            String statement = "Insert into Enchere(prixAchat, dateE, qttproduit, validite) values(?, ?, ?, ?)";

            String returnCols[] = { "idEnchere" };
            command = connection.prepareStatement(statement, returnCols);

            command.setLong(1, prixAchat);
            command.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            command.setInt(3, qttProduit);
            command.setInt(4, 1);

            int affectedRows = command.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("E table 'enchere' failed, no rows affected.");
            }
            result = command.getGeneratedKeys();

            if (result.next()) {

                command.clearParameters();

                statement = "Insert into Encherir values(?, ?, ?)";

                command = connection.prepareStatement(statement);

                command.setInt(1, result.getInt(1));
                command.setString(2, utilisateurMail);
                command.setInt(3, venteId);

                affectedRows = command.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating table 'encherir' failed, no rows affected.");
                }
                else {
                    System.out.println("Encherir successfully made!");

                    result = command.getGeneratedKeys();
                    command.clearParameters();

                    if(result.next()) idEncherir = result.getInt(1);

                    connection.commit();
                }
            } else {
                throw new SQLException("Encherir failed, no ID obtained.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error: " + e.getErrorCode());
        }

        return idEncherir;
    }

    /*
     * L'enchère faite par un Utilisateur sur un produit mis en vente dans une
     * Salle de vente.
     */
    public static ResultSet getEnchereFaite(String email, int idProduit) {
        try {

            String statement = "SELECT nom, prenom, nomProduit, prixAchat, qttProduit "
                    + "FROM Enchere "
                    + "JOIN Encherir ON Enchere.idEnchere = Encherir.idEnchere "
                    + "JOIN Utilisateur ON Utilisateur.email = Encherir.email "
                    + "JOIN Vente ON Vente.idVente = Encherir.idVente "
                    + "JOIN Produit ON Produit.idProduit = Vente.idProduit "
                    + "WHERE Utilisateur.email = ? AND Produit.idProduit = ?";

            command = connection.prepareStatement(statement);

            command.setString(1, email);
            command.setInt(2, idProduit);

            command.execute();
            result = command.getResultSet();
            command.clearParameters();

        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error: " + e.getErrorCode());
        }

        return result;
    }


    /*
     * Le processus de fin d'enchère déterminant le (ou les) Utilisateurs ayant remporté une vente,
     * en tenant compte du type d'enchère.
     *
     * On cherche pour l'idVente = idVente
     * Ordre desc
     */
    public static ResultSet getFinEnchere(int idVente) {
        try {

            String statement = "SELECT nom, prenom, qttProduit "
                    + "FROM Enchere "
                    + "JOIN Encherir ON Enchere.idEnchere = Encherir.idEnchere "
                    + "JOIN Utilisateur ON Utilisateur.email = Encherir.email "
                    + "JOIN Vente ON Vente.idVente = Encherir.idVente "
                    + "WHERE validite = ? AND Vente.idvente = ? "
                    + "ORDER BY prixAchat";

            connection.prepareStatement(statement);

            command.setInt(1, 1);
            command.setInt(2, idVente);

            command.execute();
            result = command.getResultSet();
            command.clearParameters();

        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error: " + e.getErrorCode());
        }

        return result;
    }

    /**
     *  Mise en ventre
     */
    public static int miseEnVente(int idTypeV,
                                    String nomCat,
                                    int idProduit,
                                    int prixDep,
                                    Date date) {
      int idSalle = 0; int idVente = -1;
      try {
          // Pas creer la salle tout le temps, mais du coup les dates vont dependre les unes des autres
          // Comment je peux dire quelle quantité je vends sur le meme produit
          // Pour les encheres descendante, le prix decroit quand ? --> il faut bien une date de depart
          String statement = "Insert into Salle(idTypeV, nomCat) "
                    + "values (?, ?)";

          command = connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
          command.setInt(1, idTypeV);
          command.setString(2, nomCat);

          command.executeUpdate();
          result = command.getGeneratedKeys();
          command.clearParameters();
          if(result.next()) idSalle = result.getInt(1);

          statement = "Insert into Vente(idSalle, idProduit, prixDep) "
                    + "values (?, ?, ?)";

          command = connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
          command.setInt(1, idSalle);
          command.setInt(2, idProduit);
          command.setInt(3, prixDep);

          command.executeUpdate();
          result = command.getGeneratedKeys();
          command.clearParameters();
          if(result.next()) idVente = result.getInt(1);
          connection.commit();

      } catch (SQLException e) {
          System.out.println("SQLException: " + e.getMessage());
          System.out.println("SQLState: " + e.getSQLState());
          System.out.println("Error: " + e.getErrorCode());
      }
      return idVente;
    }

    /**
     * Affiche toute une table
     */
    public static void printTable(String tableName) {
      ResultSet result = null;
        try {
            String statement = "Select * from " + tableName;

            command = connection.prepareStatement(statement);

            command.execute();
            result = command.getResultSet();
            dumpResultSet(result);
            command.clearParameters();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error: " + e.getErrorCode());
        }
    }
  }
