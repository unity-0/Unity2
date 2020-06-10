/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.services;

/**
 *
 * @author Fadhlaoui Zied
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import unity.entities.Pluss;
import unity.entities.Utilisateur;
import unity.utils.MyConnection;

public class ServicePluss {
    
  static Statement statement = null;
    PreparedStatement pst;

    MyConnection cnx = MyConnection.getInstance();
    Connection connection = cnx.getCnx();

    
    
    public boolean ajouterPluss(Pluss p) {
        
        
        String req = "INSERT INTO pluss(pseudo,emplacement,notific) VALUES (?,?,?)";
        try {
            pst = connection.prepareStatement(req);	
            pst.setString(1, p.getPseudo());
            pst.setString(2, p.getEmplacement());
            pst.setInt(3, p.getNotific());
          


            int res = pst.executeUpdate();

            if(res > 0) {
                    return true;
            }
			
        } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
        }   
        return false;
    }

    public Pluss getEN(String pseud) {
        
        Pluss pluss= null;
        
        String req = "SELECT * FROM pluss WHERE pseudo Like '"+pseud+"'";
	
        try {
                statement = connection.createStatement();
                ResultSet res = statement.executeQuery(req);
                
                while (res.next()) {
                   pluss = new Pluss(res.getString(2),res.getString(3), res.getInt(4));
                }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        return pluss;
    }
    
    
    
    public boolean existeEN(String pseud) {
        
        Pluss pluss= null;
        
        String req = "SELECT * FROM pluss WHERE pseudo LIKE '"+pseud+"' ";
	
        try {
                statement = connection.createStatement();
                ResultSet res = statement.executeQuery(req);
                
                while (res.next()) {
                return true ;
                  }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        return false;
    }
    
    public void supprimerPluss(String pseud) {
        
        String req="DELETE FROM pluss WHERE pseudo  Like '"+pseud+"' ";
              
        try {
            pst = connection.prepareStatement(req);
            int res = pst.executeUpdate();

            if(res > 0) {
                    return ;
            }	
        } catch (SQLException e1) {
                e1.printStackTrace();
        }   
      //  return false;
    }
    
    public int notificCount() {
        int nb = 0;
	
        try {
                String req = "SELECT COUNT(*) FROM  pluss WHERE notific=1";

                statement = connection.createStatement();
                ResultSet res = statement.executeQuery(req);
                
                while (res.next()) {
                    nb = res.getInt(1);
                }

        } catch (SQLException e) {
                e.printStackTrace();
        } 
        return nb;
    }
    
      public int refugeCount(String nom) {
        int nb = 0;
	
        try {
                String req = "SELECT COUNT(*) FROM  pluss WHERE  emplacement Like '"+nom+"'";

                statement = connection.createStatement();
                ResultSet res = statement.executeQuery(req);
                
                while (res.next()) {
                    nb = res.getInt(1);
                }

        } catch (SQLException e) {
                e.printStackTrace();
        } 
        return nb;
    }
      
     
    
    
}
