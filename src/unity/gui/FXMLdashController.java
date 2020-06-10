/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import unity.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */
public class FXMLdashController implements Initializable {

    /**
     * Initializes the controller class.
     */
        public static Utilisateur recupererUtilisateurConnecte;

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setUser(Utilisateur user){
        recupererUtilisateurConnecte = user;
        //user_nav.setText(recupererUtilisateurConnecte.getNom_Utilisateur());
    }
}
