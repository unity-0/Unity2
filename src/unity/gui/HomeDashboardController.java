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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import unity.services.ServiceLogin;
import unity.services.ServiceRefuge;

/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */
public class HomeDashboardController implements Initializable {

    @FXML
    private Label countRefugiers;
    @FXML
    private Label countRef;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ServiceRefuge ps = new ServiceRefuge();
        countRef.setText(String.valueOf(ps.listRefuges().size()));
        
        ServiceLogin se = new ServiceLogin();
        countRefugiers.setText(String.valueOf(se.getTtUtilisRef().size()));
    }    
    
    @FXML
    private void statAction(MouseEvent event ) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("/unity/gui/StatR.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.show();
    
      
     }
    
}
