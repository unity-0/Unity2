/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unity.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import unity.entities.Pluss;
import unity.entities.Refuge;
import unity.services.ServicePluss;
import unity.services.ServiceRefuge;

/**
 * FXML Controller class
 *
 * @author Fadhlaoui Zied
 */
public class StatRController implements Initializable {

    @FXML
    private BarChart<?, ?> pourcentage;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    
     List<Float> listdd = new ArrayList <Float>();
     List<String> listddd = new ArrayList <String>();
     private ServicePluss cp = new ServicePluss();  
     private ServiceRefuge r = new ServiceRefuge();

     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XYChart.Series series1 = new XYChart.Series();
         listddd=r.getState();
        for(int i=0;i<listddd.size();i++)
        {
          String ref= listddd.get(i);
          System.out.println(ref);
          int nb= r.getState2(ref);
                    System.out.println(nb);

          int c= cp.refugeCount(ref);
                    System.out.println(c);

          float calc = (c*100)/nb;
                    System.out.println(calc);

               series1.getData().add(new XYChart.Data(ref,calc));
               
        }
      pourcentage.getData().addAll(series1);
    }    

    @FXML
    private void closeDialog(ActionEvent event) {
        Button btn = (Button) event.getSource();
            Stage stage =  (Stage) btn.getScene().getWindow();
            stage.close();
    }
    
}
