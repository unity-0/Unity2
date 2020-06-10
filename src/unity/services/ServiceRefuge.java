/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import unity.entities.Refuge;
import unity.utils.MyConnection;

/**
 *
 * @author Fadhlaoui Zied
 */
public class ServiceRefuge implements IServiceRefuge  {
    

    static Statement statement = null;
    PreparedStatement pst;

    MyConnection cnx = MyConnection.getInstance();
    Connection connection = cnx.getCnx();
    
   @Override
    public ObservableList<Refuge> listRefuges() {
        ObservableList<Refuge> listrefuges = FXCollections.observableArrayList();
       
        String req = " SELECT * FROM refuge";
	
        try {
                Statement statement = connection.createStatement();
                ResultSet res = statement.executeQuery(req);
                
                while (res.next()) {
                 Refuge r = new Refuge(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6));

                 

                   r.setBtn_delete(new Button("Supprimer"));
                   listrefuges.add(r);
                }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        return listrefuges;
    }

     @Override
    public boolean ajouterRefuge(Refuge r) {
        
       
        String req = "INSERT INTO refuge(nom,adresse,nbtot,tel,image) VALUES (?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(req);	
            pst.setString(1, r.getNom());
            pst.setString(2, r.getAdresse());
            pst.setInt(3, r.getNbtot());

            pst.setInt(4, r.getTel());
            pst.setString(5, r.getImg());
        


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
  

 @Override
    public boolean supprimerRefuge(int id) {
          
        String req="DELETE FROM refuge WHERE id="+id;
              
        try {
            pst = connection.prepareStatement(req);
            int res = pst.executeUpdate();

            if(res > 0) {
                    return true;
            }	
        } catch (SQLException e1) {
                e1.printStackTrace();
        }   
        return false;
    }
    
    public List<String> getState() {
        String req11 = "Select nom  From refuge ";
        List<String> liste = new ArrayList<String>();
        try {
             statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(req11);
          
            while (rs.next()) {
                  
                
                liste.add(rs.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    
     public Integer getState2(String s) {
             int g=0;
        String req11 = "Select nbtot From refuge where nom ='"+s+"'  ";
       
         PreparedStatement ps;
        try {
               ps = connection.prepareStatement(req11);

            ResultSet rs = ps.executeQuery();
          
            while (rs.next()) {
                  
                
                    g= rs.getInt(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }
}
