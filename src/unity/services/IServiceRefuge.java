/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.services;

import java.util.List;
import unity.entities.Refuge;
/**
 *
 * @author Fadhlaoui Zied
 */
public interface IServiceRefuge {
    public List<Refuge> listRefuges();
    
   // public List<Refuge> getListRefugeFilter(String filter,String categorie,String sous_categorie,String tri);
    
 //   public Refuge getRefuge(int id);
    
   public boolean ajouterRefuge(Refuge r);
    
   // public boolean modifierProduit(Produit p);
    
   public boolean supprimerRefuge(int id);
    
 //   public List<Produit> search(String libelle);
    
  //  public boolean exportXLS();
    
}
