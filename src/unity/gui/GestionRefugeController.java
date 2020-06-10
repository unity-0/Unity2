/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.gui;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import unity.entities.Refuge;
import unity.services.ServiceRefuge;
/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */
public class GestionRefugeController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private TableView<Refuge> table;
    @FXML
    private TableColumn<Refuge,String > nom;
    @FXML
    private TableColumn<Refuge,String> adresse;
     @FXML
    private TableColumn<Refuge,Integer> nbtot;
    @FXML
    private TableColumn<Refuge,Integer> tel;
    @FXML
    private TableColumn<Refuge,String> action;

    public static ObservableList<Refuge> observableList;
    
    private ServiceRefuge sr = new ServiceRefuge();  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO should revize this
        observableList = FXCollections.observableArrayList( sr.listRefuges());
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        nbtot.setCellValueFactory(new PropertyValueFactory<>("nbtot"));

        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
       action.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));
        table.setItems(observableList);
        
      /*  table.setOnMouseClicked((MouseEvent event)->{
            Refuge refuge = table.getSelectionModel().getSelectedItem();
            modifierDialog(refuge);
            
        }); */
        
    }    

    @FXML
    private void ajouterDialog(MouseEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("/unity/gui/AjouterRefuge.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.show();
        
    }

    @FXML
    private void exportXLS(MouseEvent event) {
    }

    @FXML
    private void search(KeyEvent event) {
    }
    
}
