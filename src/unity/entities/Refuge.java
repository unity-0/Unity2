/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.entities;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import unity.gui.GestionRefugeController;
import unity.services.ServiceRefuge;


/**
 *
 * @author Fadhlaoui Zied
 */
public class Refuge {
    
    public int id;
    public String nom;
    public String adresse;
    public int nbtot;
    public int tel;
    private String img;
    private ImageView image;
   private Button btn_delete;

    public Refuge(int id, String nom, String adresse,  int nbtot , int tel,String img) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.nbtot = nbtot;
        this.tel = tel;
        this.img = img;

    }
      public Refuge(String nom, String adresse, int nbtot , int tel,String img) {
        
        this.nom = nom;
        this.adresse = adresse;
        this.nbtot = nbtot;
        this.tel = tel;
        this.img = img;

    }
    
    public Refuge() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
   
   
    public int getNbtot() {
        return nbtot;
    }

    public void setNbtot(int nbtot) {
        this.nbtot = nbtot;
    }
    
    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
    
    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
}

  
    public Button getBtn_delete() {
        return btn_delete;
    }

    public void setBtn_delete(Button btn_delete) {
        this.btn_delete = btn_delete;
        
        this.btn_delete.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Vous voulez vraiment supprimer cet refuge");
               
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    ServiceRefuge service = new ServiceRefuge();
                    if(service.supprimerRefuge(id)){
                         GestionRefugeController gestionrefuge = new GestionRefugeController();
                         gestionrefuge.observableList.remove(this);
                    }
                } else {
                    
                }
            });
    }
/*
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", souscategorie_id=" + souscategorie_id + ", souscategorie=" + souscategorie + ", libelle=" + libelle + ", description=" + description + ", prix=" + prix + ", stock=" + stock + ", firstimg=" + firstimg + ", secondimg=" + secondimg + ", thirdimg=" + thirdimg + ", color=" + color + ", created=" + created + ", Image=" + Image + ", btn_delete=" + btn_delete + '}';
*/
    }