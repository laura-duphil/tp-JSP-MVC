/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tauxremise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author pedago
 */
public class DAOmdl {

    protected final DataSource myDataSource;

    /**
     * @param dataSource la source de données à utiliser
     */
    public DAOmdl(DataSource dataSource) {
        this.myDataSource = dataSource;
    }

    public List<DiscountEntity> touteReducs() throws Exception {
        List<DiscountEntity> reductions = new LinkedList<>(); // Liste vIde

        String sql = "SELECT DISCOUNT_CODE, RATE FROM DISCOUNT_CODE";
        try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
                ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
        ) {
            while (rs.next()) { // Tant qu'il y a des enregistrements
                // On récupère les champs nécessaires de l'enregistrement courant
                String code = rs.getString("DISCOUNT_CODE");
                float taux = rs.getFloat("RATE");
                // On crée l'objet entité
                DiscountEntity red = new DiscountEntity(code, taux);
                // On l'ajoute à la liste des résultats
                reductions.add(red);
            }
        } catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, "Probleme", ex);
        }
        return reductions;
    }
    
    
    public void ajouterCode(String code, float taux) {
        String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";
        
        try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                PreparedStatement stmt = connection.prepareStatement(sql) // Un ResultSet pour parcourir les enregistrements du résultat
                ) {
            stmt.setString(1, code);
            stmt.setFloat(2, taux);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void delete(String code, float taux) {
        String sql = "DELETE FROM DISCOUNT_CODE VALUES (?, ?)";
        
        try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                PreparedStatement stmt = connection.prepareStatement(sql) // Un ResultSet pour parcourir les enregistrements du résultat
                ) {
            stmt.setString(1, code);
            stmt.setFloat(2, taux);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
        }
            
    }
}
