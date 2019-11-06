/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tauxremise;

import java.sql.Connection;
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

    protected DataSource myDataSource;

    /**
     * @param dataSource la source de données à utiliser
     */
    public DAOmdl(DataSource dataSource) {
        this.myDataSource = dataSource;
    }

    public List<DiscountEntity> touteReducs() throws Exception {
        List<DiscountEntity> result = new LinkedList<>(); // Liste vIde

        String sql = "SELECT DISCOUNT_CODE, RATE FROM DISCOUNT_CODE";
        try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
                ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
        ) {
            while (rs.next()) { // Tant qu'il y a des enregistrements
                // On récupère les champs nécessaires de l'enregistrement courant
                String code = rs.getString("DISCOUNT_CODE");
                float taux = rs.getFloat("TAUX");
                // On crée l'objet entité
                DiscountEntity c = new DiscountEntity(code, taux);
                // On l'ajoute à la liste des résultats
                result.add(c);
                System.out.println(code);
            }
        } catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, "Probleme", ex);
                throw new Exception(ex.getMessage());
        }
        return result;
    }
}
