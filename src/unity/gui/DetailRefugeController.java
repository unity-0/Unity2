/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import unity.entities.Pluss;
import unity.entities.Refuge;
import unity.entities.Utilisateur;
import static unity.gui.FXMLacceuilController.recupererUtilisateurConnecte;
import unity.services.ServicePluss;

/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */
public class DetailRefugeController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private ImageView present_img;
    @FXML
    private Label nom;
    @FXML
    private Label adresse;
    @FXML
    private Label tel;
    @FXML
    private Label nbtot;
    
    private Refuge refuge;

    private Image image;
    private boolean visible = false;
    //public static Utilisateur recupererUtilisateurConnecte;
    //private String pseud="";
        String pseud = FXMLacceuilController.recupererUtilisateurConnecte.getNom_Utilisateur();

    private String ref="" ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Refuge r) throws FileNotFoundException {
        this.refuge = r;
        nom.setText(r.getNom());
        adresse.setText(r.getAdresse());
        tel.setText(String.format("%d",r.getTel()));
        nbtot.setText(String.format("%d",r.getNbtot()));
       
        image = new Image(new FileInputStream("C:\\Users\\Fadhlaoui Zied\\Desktop\\download\\"+refuge.getImg()));
        present_img.setImage(image);
        System.out.println("testttttttt");
        ref = r.getNom();
    }


    @FXML
    private void ajoutPlus(ActionEvent event) {
            String test="";
            ServicePluss sp1 = new ServicePluss();


             String p = pseud;
             String e = ref;
             
             Integer n = 0;
             
             if((sp1.existeEN(pseud))==true)
             {           

                  Pluss pp = new Pluss();
                  pp=sp1.getEN(pseud);
                  
                  test =  pp.getEmplacement();

                        if (test.equals(e))
                         {

                          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("Vous etes déja un habitant dans ce refuge");
                                alert.showAndWait(); 
                                return;
                         }
                        else
                        {
                            sp1.supprimerPluss(pseud);
                             Pluss pl = new Pluss(p,e,n);
     
                            ServicePluss sp = new ServicePluss();
        
                             if(sp.ajouterPluss(pl)){
        
                             ImageView imag = new ImageView("unity/image/success.png");
                             imag.setFitWidth(70);
                             imag.setFitHeight(70);
                
                                 Notifications notification = Notifications.create()
                           .title("Confirmation")
                           .text("Votre emplacment est confirmé")
                         .graphic(imag)
                          .hideAfter(Duration.seconds(5))
                          .position(Pos.TOP_RIGHT);
                              notification.show();
                                 }  
                        }
             }
             
             else {
             Pluss pl = new Pluss(p,e,n);
     
            ServicePluss sp = new ServicePluss();
        
        if(sp.ajouterPluss(pl)){
        
        ImageView imag = new ImageView("unity/image/success.png");
                imag.setFitWidth(70);
                imag.setFitHeight(70);
                
                Notifications notification = Notifications.create()
                        .title("Confirmation")
                        .text("Votre emplacment est confirmé")
                       .graphic(imag)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                notification.show();
        }  
             }
    } 
    

    @FXML
    private void ajouterNot(ActionEvent event) {
        //closeDialog(event);
           String test1="";
              ServicePluss ser = new ServicePluss();


                String pseu = pseud;
                String empl = ref;
             
                Integer noti = 1;
         if((ser.existeEN(pseud))==true)
             {           

                  Pluss ppp = new Pluss();
                  ppp=ser.getEN(pseud);
                  
                  test1 =  ppp.getEmplacement();

                        if (test1.equals(empl))
                         {

                         ser.supprimerPluss(pseud);
                             Pluss pl = new Pluss(pseu,empl,noti);
     
                            ServicePluss sp = new ServicePluss();
        
                             if(sp.ajouterPluss(pl)){
                                  ImageView imag = new ImageView("unity/image/téléchargement.png");
                              imag.setFitWidth(70);
                                imag.setFitHeight(70);
                
                                 Notifications notification = Notifications.create()
                                 .title("Alerte")
                                 .text("Votre Alerte sera traité immédiatement")
                                .graphic(imag)
                                     .hideAfter(Duration.seconds(5))
                                     .position(Pos.TOP_RIGHT);
                                     notification.show();
                             }
                         }
                        else{
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("cette action est valable juste pour les habitants de ce refuge ! ");
                                alert.showAndWait(); 
                                return;
                        }
             }
         else {            
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("Veuillez s'inscrire au refuge d'abord !");
                                alert.showAndWait(); 
                                return;
    }
    
    }
}
