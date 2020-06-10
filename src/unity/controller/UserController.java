/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;
import unity.entities.Utilisateur;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import unity.services.ServiceLogin;
import unity.gui.FXMLacceuilController;
import unity.gui.DashboardController;
import unity.services.ServicePluss;


//import unity.services.ServiceLogin;

public class UserController {

    @FXML
    private AnchorPane GUI;
    /* utilisateur inscri + login */
    @FXML
    private TextField inscription_nom_utilisateur_fx;

    @FXML
    private PasswordField inscription_mot_de_passe_utilisateur_fx;

    @FXML
    private TextField inscription_email_utilisateur_fx;

    @FXML
    private TextField login_nom_utilisateur_fx;

    @FXML
    private PasswordField login_mot_de_passe_utilisateur_fx;

    /* end utilisateur */
    /**
     * design login *
     */
    double x = 0;
    double y = 0;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /**
     * end design login *
     */
    @FXML
    void Inscription(ActionEvent event) throws IOException {

        int status = 0;
        
        String nomUtilisateur = inscription_nom_utilisateur_fx.getText();
        String motDePasseUtilisateur = inscription_mot_de_passe_utilisateur_fx.getText();

        String emailUtilisateur = inscription_email_utilisateur_fx.getText();

        Utilisateur Uti = new Utilisateur();
        Uti.setNom_Utilisateur(nomUtilisateur);
        Uti.setMotDePasse_Utilisateur(motDePasseUtilisateur);
        Uti.setEmail(emailUtilisateur);
        
        Utilisateur existenceUtilisateur = new Utilisateur();
        existenceUtilisateur = ServiceLogin.getUtilisateur(nomUtilisateur);
        
        if (inscription_nom_utilisateur_fx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("completez les champs manquants");
            alert.showAndWait();
            return;

        }
           else if (inscription_email_utilisateur_fx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("completez les champs manquants");
            alert.showAndWait();
            return;

        } else if (inscription_mot_de_passe_utilisateur_fx.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("completez les champs manquants");
            alert.showAndWait();
            return;
        }
       
        
         
        
        
        if (existenceUtilisateur == null) {
            status = ServiceLogin.Inscription(Uti);
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le nom est déja utilisé");
            alert.showAndWait();
            return;

        }
        

        if (status > 0) {
            
            Stage stage = (Stage) GUI.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unity/gui/FXMLacceuil.fxml"));
            Parent root = loader.load();
            FXMLacceuilController controller = (FXMLacceuilController ) loader.getController();
            existenceUtilisateur = ServiceLogin.getUtilisateur(nomUtilisateur);
            controller.setUser(existenceUtilisateur);

            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ImageView imag = new ImageView("unity/image/welcome.png");
                imag.setFitWidth(70);
                imag.setFitHeight(70);
            Notifications notification = Notifications.create()
                        .title("Bienvenue -  welcome  - أهلا بك ")
                        
                        .text(nomUtilisateur+", veuillez choisir votre refuge s'il existe dans SOLIDARITE ")
                        .graphic(imag)

                        .hideAfter(Duration.seconds(13))
                        .position(Pos.CENTER);
                notification.show();
            return;

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERREUR D'inscription ! Completez les champs");
            alert.showAndWait();
            return;
        }
        
    }

    @FXML
    void connexionUtilisateur(ActionEvent event) throws IOException {

        if (login_nom_utilisateur_fx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("nom d'utilisateur est vide");
            alert.showAndWait();
            return;

        } else if (login_mot_de_passe_utilisateur_fx.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("mot de passe est vide");
            alert.showAndWait();
        }

        List<Utilisateur> listUtilisateur = ServiceLogin.getTtUtilisateur();
        HashMap<String, String> hashmapUtilisateur = new HashMap<>();

        for (Utilisateur utilisateur : listUtilisateur) {
            hashmapUtilisateur.put(utilisateur.getNom_Utilisateur(), utilisateur.getMotDePasse_Utilisateur());
        }

      
        String nomUtilisater = login_nom_utilisateur_fx.getText();
        String motDePasseUtilisateur = login_mot_de_passe_utilisateur_fx.getText();
        for (Map.Entry<String, String> Uti : hashmapUtilisateur.entrySet()) {
            if (nomUtilisater.equals(Uti.getKey())) {
                if (ServiceLogin.testMotDePasse(motDePasseUtilisateur, Uti.getValue())) {
                    Utilisateur utilisateur = ServiceLogin.getUtilisateur(Uti.getKey());
                    if (utilisateur.getRole_Utilisateur().equals("a:0:{}")) {
                        
                        Stage stage = (Stage) GUI.getScene().getWindow();
                        stage.close();
                      
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unity/gui/FXMLacceuil.fxml"));
                        Parent root = loader.load();
                        FXMLacceuilController controller = (FXMLacceuilController ) loader.getController();
                        controller.setUser(ServiceLogin.getUtilisateur(Uti.getKey()));

                        Stage primaryStage = new Stage();
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        return;
                    } else {
                      
                        Stage stage = (Stage) GUI.getScene().getWindow();
                        stage.close();
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unity/gui/Dashboard.fxml"));
                        Parent root = loader.load();
                        DashboardController controller = (DashboardController ) loader.getController();
                        controller.setUser(ServiceLogin.getUtilisateur(Uti.getKey()));

                        Stage primaryStage = new Stage();
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        ServicePluss ps = new ServicePluss();
       int n = ps.notificCount() ;
       if (n!=0)
       {
       ImageView imag = new ImageView("unity/image/téléchargement.png");
                imag.setFitWidth(70);
                imag.setFitHeight(70);
                
                Notifications notification = Notifications.create()
                        .title("Alerte")
                        .text("Vous avez rècu "+n+"  alertes ")
                       .graphic(imag)
                        
                .position(Pos.TOP_RIGHT);
                notification.show();
       }
                        return;
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("mot de passe incorrect");
                    alert.showAndWait();
                    return;
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("nom incorrect");
        alert.showAndWait();

    }
    
    

     @FXML
    public void connexionLoad(ActionEvent even) throws IOException {
        try {
            
            Stage stage = (Stage) GUI.getScene().getWindow();
            stage.close();
            
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/unity/gui/FXMLlogin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
           
            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void CreerCompte(ActionEvent even) throws IOException {
        try {
            
            Stage stage = (Stage) GUI.getScene().getWindow();
            stage.close();
            
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/unity/gui/FXMLinscription.fxml"));
            Scene scene = new Scene(root);
       
            primaryStage.setScene(scene);
            
            primaryStage.initStyle(StageStyle.UNDECORATED);
            
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quitter() {
        // get a handle to the stage
        Stage stage = (Stage) GUI.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
