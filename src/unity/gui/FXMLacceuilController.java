/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import unity.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */
public class FXMLacceuilController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private HBox bl_search;
    @FXML
    private Button button_search;
    @FXML
    private Circle notification;
    @FXML
    private MenuItem user_nav;
    @FXML
    private Circle userImage;
    
     private boolean visible = false;

    public static Utilisateur recupererUtilisateurConnecte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         Image image = new Image("unity/image/user.png", false);
        userImage.setFill(new ImagePattern(image));
    }    

    public void setUser(Utilisateur user) {
        recupererUtilisateurConnecte = user;
        user_nav.setText(recupererUtilisateurConnecte.getNom_Utilisateur());

       /*
        notification.setVisible(false);
        if (panier != null) {

            if (service_panier.getLignePanier(panier.getId()).size() > 0) {
                notification.setVisible(true);
            }
        }*/
    }
    
    @FXML
    private void AccueilAction(MouseEvent event) {
    }

    @FXML
    private void RefugeAction(MouseEvent event) {
        loadUI("FrontRefuge");
    }

    @FXML
    private void AnnonceAction(MouseEvent event) {
    }

    @FXML
    private void EvenementAction(MouseEvent event) {
    }

    @FXML
    private void AssociationAction(MouseEvent event) {
    }

    @FXML
    private void ProfilAction(MouseEvent event) {
    }

    public void loadUI(String ui) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unity/gui/" + ui + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLacceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        borderpane.setCenter(root);
    }
    
    @FXML
    private void quitter(ActionEvent event) {
         // get a handle to the stage
        Stage stage = (Stage) borderpane.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
}
