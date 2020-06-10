/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import unity.entities.Refuge;
import unity.services.ServiceRefuge;

/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */
public class FrontRefugeController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox content;
    @FXML
    private HBox ListeDeRefuge;

    private ObservableList<Refuge> listRefuge; 
    private ServiceRefuge sr = new ServiceRefuge();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            
            setListRefuge();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrontRefugeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    private void setListRefuge() throws FileNotFoundException{
        
        listRefuge= FXCollections.observableArrayList(sr.listRefuges());
        

        for (Refuge refuge : listRefuge) {
            
            VBox content = new VBox();
            
            Image image = new Image( new FileInputStream("C:\\Users\\Fadhlaoui Zied\\Desktop\\download\\"+refuge.getImg()));
            ImageView imageView = new ImageView(image);
            Label title = new Label(refuge.getNom());
          //  title.getStyleClass().add("nom_refuge");
            imageView.setFitHeight(180);
            imageView.setFitWidth(246);
            
            content.getChildren().addAll(title,imageView);
            Button item = new Button("",content);
            item.setOnAction(event -> {
                detailRefuge(event, refuge);
            });
            
            ListeDeRefuge.getChildren().add(item);
            
            
        }
    }
    
    private void detailRefuge (ActionEvent event,Refuge r){
       BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/unity/gui/DetailRefuge.fxml"));
       
       Parent root = null;
        try {
             root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FrontRefugeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       border_pane.setCenter(root);
       
       DetailRefugeController controller = loader.<DetailRefugeController>getController();
       
        try {
            controller.setData(r);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrontRefugeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}