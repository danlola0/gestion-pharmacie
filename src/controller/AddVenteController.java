/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import database.database;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;
import modele.VenteModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddVenteController implements Initializable {
    @FXML
    private StackPane root;
    @FXML
    private TextField t_nom_client;
    @FXML
    private TextField t_telephone;
    @FXML
    private TextField t_qte;
    @FXML
    private JFXButton btnSave;
    @FXML
    private ImageView iclose;
    @FXML
    private JFXComboBox<String> cmb_medic;
    @FXML
    private ImageView ifacture;
    @FXML
    private JFXComboBox<String> cmb_fourn;
    Connection con;
    database db = new database();
    @FXML
    private Pane pane;
    @FXML
    private Label lfournisseur;
    @FXML
    private Label lpu;
    @FXML
    private Label lqte;
    @FXML
    private Label lcontact;
    @FXML
    private Label lproduit;
    @FXML
    private Label lnom;
    @FXML
    private Label lpt;
     Calendar calendar;
    SimpleDateFormat formatter1;
    SimpleDateFormat formatter2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         iclose.setImage(new Image("/images/close.png"));
                  ifacture.setImage(new Image("/images/facture.png"));
                  
                      calendar = Calendar.getInstance();
        formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        formatter2 = new SimpleDateFormat("HH:mm:ss");
        
        

        con = db.getcon();
        getAllFournisseur();
        getAllMedicament();
        t_nom_client.textProperty().addListener((oldv,newv,c)->{
         lnom.setText(c);
        });
        t_telephone.textProperty().addListener((oldv,newv,c)->{
         lcontact.setText(c);
        });
        t_qte.textProperty().addListener((oldv,newv,c)->{
         lqte.setText(c);
         if(cmb_medic.getValue()!=null){
                lpt.setText(""+(Integer.parseInt(lpu.getText())*Integer.parseInt(t_qte.getText())));
            }
        });
//        t.textProperty().addListener((oldv,newv,c)->{
//         lcontact.setText(c);
//        });
        
        cmb_fourn.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue)->{
                    lfournisseur.setText(newValue.substring(2, newValue.length()));
        });
         cmb_medic.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue)->{
            lproduit.setText(newValue.substring(2, newValue.length()));
            lpu.setText(""+getPU(Integer.parseInt(newValue.substring(0, 1))));
            if(!t_qte.getText().equals("")){
                lpt.setText(""+(Integer.parseInt(lpu.getText())*Integer.parseInt(t_qte.getText())));
            }
        });
    }    

    @FXML
    private void onSave(ActionEvent event) {
        try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO `vente`( `nom_client`, `telephone_client`, `id_medicament`, `qte`, `id_fournisseur`) VALUES (?,?,?,?,?)");
                ps.setString(1, t_nom_client.getText());
                ps.setInt(2, Integer.parseInt(t_telephone.getText()));
                ps.setInt(3, Integer.parseInt(cmb_medic.getValue().toString().split("|")[0]));
                ps.setInt(4, Integer.parseInt(t_qte.getText()));
                ps.setInt(5, Integer.parseInt(cmb_fourn.getValue().toString().split("|")[0]));

                ps.execute();
                System.out.println("insertion a jour reusssie!");
                printFacture();
                returnMe();

            } catch (Exception e) {
                System.err.println("erreur " + e.getMessage());
            }
    }

    @FXML
    private void onClose(ActionEvent event) {
        returnMe();
    }

    private void returnMe() {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/vue/vente.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }
    
    public void initMe(VenteModel vm){
        
    }
    
   void getAllFournisseur(){
        try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM `fournisseur`");

               ResultSet rs= ps.executeQuery();
               
               while(rs.next()){
                   cmb_fourn.getItems().add(rs.getString("id")+"|"+rs.getString("nom"));
               }
            } catch (Exception e) {
                System.err.println("erreur " + e.getMessage());
            }
        
    }
   
     void getAllMedicament(){
        try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM `medicament`");

               ResultSet rs= ps.executeQuery();
               
               while(rs.next()){
                   cmb_medic.getItems().add(rs.getString("id")+"|"+rs.getString("description"));
               }
            } catch (Exception e) {
                System.err.println("erreur " + e.getMessage());
            }
    }
     
     int getPU(int id){
         int r=-1;
          try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM `medicament` WHERE `id`=?");
ps.setInt(1, id);
               ResultSet rs= ps.executeQuery();
               
               if(rs.next()){
                   r=rs.getInt("prix");
               }
            } catch (Exception e) {
                System.err.println("erreur " + e.getMessage());
            }
          
          return r;
     }

    private void printFacture() {
        WritableImage image = pane.snapshot(new SnapshotParameters(), null);

        String nameFile = "";
        nameFile = "facture/tfc_" + lnom.getText()+ formatter2.format(calendar.getTime()).replace(":", "").replace("-", "") + formatter1.format(calendar.getTime()).replace(":", "").replace("-", "") + "_recto.png";

        // TODO: probably use a file chooser here
        File file = new File(nameFile);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Impression terminer");
           
        } catch (IOException e) {
            // TODO: handle exception here
            System.err.println("errerur lors de l impression " + e);
        }
    }
}
