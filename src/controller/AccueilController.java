/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AccueilController implements Initializable {
    @FXML
    private JFXButton btnMedic;
    @FXML
    private JFXButton btnfour;
    @FXML
    private JFXButton btnvente;
    @FXML
    private JFXButton btnDecon;
    @FXML
    private StackPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onActiveMedic();
    }    

    @FXML
    private void onMedic(ActionEvent event) {
        onActiveMedic();
    }

    @FXML
    private void onFourn(ActionEvent event) {
        onActiveFour();
    }

    @FXML
    private void onente(ActionEvent event) {
        onActiveVente() ;
    }

    @FXML
    private void onDeconnection(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vue/login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.show();
                Stage s = (Stage) btnDecon.getScene().getWindow();
                s.close();
        } catch (Exception e) {
        }
    }
      public void onActiveMedic() {
        gotos("/vue/medoc.fxml");
        btnMedic.setStyle(setting.setting.activeStyle);
        btnfour.setStyle(setting.setting.defaultStyle);
                btnvente.setStyle(setting.setting.defaultStyle);

        
    }
        public void onActiveFour() {
        gotos("/vue/fournisseur.fxml");
        btnMedic.setStyle(setting.setting.defaultStyle);
        btnfour.setStyle(setting.setting.activeStyle);
                btnvente.setStyle(setting.setting.defaultStyle);

        
    }
          public void onActiveVente() {
        gotos("/vue/vente.fxml");
        btnMedic.setStyle(setting.setting.defaultStyle);
        btnfour.setStyle(setting.setting.defaultStyle);
                btnvente.setStyle(setting.setting.activeStyle);

        
    }
     public void gotos(String path) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(path).openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }
    
}
