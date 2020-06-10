/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.entities;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Fadhlaoui Zied
 */
public class Pluss {
    
    public int id;
    public String pseudo;
    public String emplacement;
    public int notific;
   
   public Pluss(int id, String pseudo, String emplacement,  int notific) {
        this.id = id;
        this.pseudo = pseudo;
        this.emplacement = emplacement;
        this.notific = notific;

    }
   
   public Pluss( String pseudo, String emplacement,  int notific) {
        this.pseudo = pseudo;
        this.emplacement = emplacement;
        this.notific = notific;

    }
   public Pluss() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
        
    }
    
     public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
        
    }
     public int getNotific() {
        return notific;
    }

    public void setNotific(int notific) {
        this.notific = notific;
    }
    
    
}
