/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import unity.entities.Refuge;
import unity.services.ServiceRefuge;
/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */ 
public class AjouterRefugeController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private Label errorsNom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private Label errorsAdresse;
    @FXML
    private Label errorsStock1;
    @FXML
    private Label errorsStock;
    @FXML
    private JFXTextField tel;
    @FXML
    private Label errorsTel;
    @FXML
    private Label errorsStock2;
    @FXML
    private Label errorsStock12;
    @FXML
    private JFXTextField nbtot;
    @FXML
    private Label errorsNbtot;
    @FXML
    private ImageView imageView;
    @FXML
    private Label errorsimg;
    @FXML
    private JFXButton btn_partager;
    
    private FileChooser filechooser;
  
   // private Image image;
  
    private File file;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    
    }    

    @FXML
    private void nomEvent(KeyEvent event) {
    }

    @FXML
    private void adresseEvent(KeyEvent event) {
    }

    @FXML
    private void telEvent(KeyEvent event) {
    }

    @FXML
    private void nbtotEvent(KeyEvent event) {
    }

    @FXML
    private void uploadImage(MouseEvent event) {
        filechooser = new FileChooser();
         filechooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("image files","*.png","*.jpg","*.jpeg"));
         
         file = filechooser.showOpenDialog(null);
         
         if(file != null){
             Image image = new Image(file.toURI().toString());
             imageView.setImage(image);
             errorsimg.setText("");
             
         }else {
             errorsimg.setText("Choose Type :  PNG JPEG JPG");
         }
  

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException{
     
        if(nom.getText().equals("")){
            errorsNom.setText("entrez le nom"); 
        }
       else if(adresse.getText().equals("")){
            errorsAdresse.setText("entrez l'adresse");
        }
        else if(tel.getText().equals("")){
            errorsTel.setText("entrez le tel");
        }
        else if(nbtot.getText().equals("")){
            errorsNbtot.setText("entrez le nombre totale");
        }
       else if(file==null){
            errorsimg.setText("choisissez image");
        }
        
        else {
            
            String no = nom.getText();
            String adr = adresse.getText();
            Integer te = Integer.parseInt(tel.getText());
            Integer nbt = Integer.parseInt(nbtot.getText());
           
            
           

           String img = replaceFile(file.getAbsolutePath());
         //  String img = "j";


            
    
            
            Refuge r = new Refuge(no,adr,nbt,te,img);
     
            ServiceRefuge sr = new ServiceRefuge();
           // sr.ajouterRefuge(r);
            if(sr.ajouterRefuge(r)){
                
               closeDialog(event);
              ImageView image = new ImageView("unity/image/success.png");
                image.setFitWidth(70);
                image.setFitHeight(70);
                
                Notifications notification = Notifications.create()
                        .title("Success")
                        .text("Ajouter avec succès")
                       .graphic(image)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                notification.show();
                
                ObservableList observableList = FXCollections.observableArrayList( sr.listRefuges());
                GestionRefugeController gestionrefuge = new GestionRefugeController();
               
                gestionrefuge.observableList.clear();
                gestionrefuge.observableList.addAll(observableList);
               
               
            }
        }
        
    }

   private String generateFileName(){
        
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 25) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr; 
    }

    private String replaceFile(String file) {
        

            String extension = file.substring(file.lastIndexOf("."), file.length());
            
            String filename = generateFileName()+extension;
            
            Path sourceDirectory = Paths.get(file);
            Path targetDirectory = Paths.get("C:\\Users\\Fadhlaoui Zied\\Desktop\\download\\"+filename);
            try {
                //copy source to target using Files Class
                Files.copy(sourceDirectory, targetDirectory);
            } catch (IOException ex) {
                Logger.getLogger(AjouterRefugeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return filename; 
    } 
   
     @FXML
    private void closeDialog(ActionEvent event) {
            Button btn = (Button) event.getSource();
            Stage stage =  (Stage) btn.getScene().getWindow();
            stage.close();
    } 
}
