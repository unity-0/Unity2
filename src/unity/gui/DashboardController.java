/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.gui;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import unity.entities.Utilisateur;
import unity.services.ServicePluss;
import unity.services.ServiceRefuge;

/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */
public class DashboardController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private MenuButton user_nav;
    @FXML
    private Circle circleImage;
    
    public static Utilisateur recupererUtilisateurConnecte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
     Image image = new Image("unity/image/user.png",false);
       circleImage.setFill(new ImagePattern(image));
       Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/unity/gui/HomeDashboard.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       borderpane.setCenter(root);
      
       
       
        // TODO
    }    
    
     public void setUser(Utilisateur user){
        recupererUtilisateurConnecte = user;
        user_nav.setText(recupererUtilisateurConnecte.getNom_Utilisateur());
    }
     
    @FXML
    private void homedashboard(MouseEvent event) {
                loadUI("HomeDashboard");

    }

   

    @FXML
    private void List(MouseEvent event) {
    }

    @FXML
    private void ListRefuge(MouseEvent event) {
         loadUI("GestionRefuge");
    }
    
     private void loadUI(String ui){
       
       Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/unity/gui/"+ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
