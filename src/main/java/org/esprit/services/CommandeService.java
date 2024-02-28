package org.esprit.services;

import org.esprit.interfaces.IServiceC;
import org.esprit.models.Commande;
import org.esprit.utils.Connexion;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Random;

public class CommandeService implements IServiceC<Commande> {
    Connection connection = Connexion.getInstance().getConnection();


//    public void ajouter(Commande c) {
//        try{
//        String req = "INSERT INTO `commande`(`nom`, `prenon`, `adresse`, `email`, `phone` ) VALUES (?,?,?,?,?)";
//        PreparedStatement ps = connection.prepareStatement(req);
//        ps.setString(1,c.getNom());
//        ps.setString(2,c.getPrenom());
//        ps.setString(3,c.getAdress());
//        ps.setString(4,c.getMail());
//        ps.setInt(5,c.getPhone());
//
//        ps.executeUpdate();
//        System.out.println("Commande ajouté avec Succée!");
//
//     } catch (SQLException ex)
//
//    {
//        ex.printStackTrace();
//    }

    @Override
    public void ajouter(Commande c) {
        try {
            String req = "INSERT INTO `commande`(Total, `codePromo`, `nomProd`) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, c.getTotal());
            ps.setString(2, c.getCodePromo());
            ps.setString(3, c.getNomProd());
            ps.executeUpdate();
            connection.commit(); // Add this line to commit the transaction
            System.out.println("Commande ajouté avec Succès!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public List<Commande> fetch() {
        List<Commande> Commands = new ArrayList<>();
        try {

            String req = "SELECT * FROM commande";
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commande c1 = new Commande();

                c1.setTotal(rs.getInt(2));
                c1.setCodePromo(rs.getString(3));
                c1.setNomProd(rs.getString(4));

                Commands.add(c1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Commands;
    }

    public String generatePromoCode() {
        // Generate a random promo code (you can customize the format and length)
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder promoCode = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            promoCode.append(characters.charAt(index));
        }

        return promoCode.toString();
    }



    @Override
    public void modifier(Commande c, String a) {
        try {
            String req ="UPDATE `commande` SET `codePromo`= ? WHERE nomProd = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, a);
            ps.setString(2,  c.getNomProd());
            System.out.println(c.getNomProd());
            System.out.println(a);
            ps.executeUpdate();
            System.out.println("Code promo updated successfully!");

            // After updating the database, update the list to reflect changes
            List<Commande> updatedList = fetch(); // Assuming fetch() retrieves the updated list from the database
            // You might need to update the list in your UI or wherever it is being used
            // For example, if you are displaying it in a JavaFX TableView, you would update the items of the TableView
            // tableView.setItems(FXCollections.observableArrayList(updatedList));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public void supprimer(int c) {


        try {
            String req ="DELETE FROM `commande`  WHERE idCmd = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, c);
            ps.executeUpdate();
            System.out.println("Commande supprimée avec succée!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }



    public List<Commande> rechercheCommande(String nomProd) throws SQLException {
        List<Commande> Commands = new ArrayList<>();
        try {
            String req = "SELECT * FROM Commande WHERE nomProd LIKE ?";
            PreparedStatement st = connection.prepareStatement(req);
            st.setString(1, "%" + nomProd + "%"); // Adding wildcards to match any characters
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Commande c = new Commande();
                c.setIdCmd(rs.getInt(1));
                c.setIdUser(rs.getInt(2));
                c.setTotal(rs.getInt(3));

                // Add debug output to identify the issue
                System.out.println("DEBUG - CodePromo: " + rs.getString(4));

                c.setCodePromo(rs.getString(4));
                c.setNomProd(rs.getString(5));
                Commands.add(c);
            }
        } catch (SQLException ex) {
            throw ex; // Throw the exception to be caught and handled in the calling method
        }
        return Commands;
    }

    public List<Commande> filtreCommande(String nomProd) {
        List<Commande> Commands = new ArrayList<>();
        try {

            String req = "SELECT * FROM `commande` WHERE `nomProd` LIKE ?";
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setString(1, "%" + nomProd + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commande c= new Commande();
                c.setIdCmd(rs.getInt(1));
                c.setTotal(rs.getInt(2));
                c.setCodePromo(rs.getString(3));
                c.setNomProd(rs.getString(4));
                c.setIdUser(rs.getInt(5));

                Commands.add(c);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Commands;}
}

