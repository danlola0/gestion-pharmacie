/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import database.database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import modele.MedicamentModel;
import modele.VenteModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class VenteController implements Initializable {
    @FXML
    private TableView<VenteModel> table;
    @FXML
    private TableColumn<VenteModel, String> colnum;
    @FXML
    private TableColumn<VenteModel, String> colnom;
    @FXML
    private TableColumn<VenteModel, String> colnomfour;
    @FXML
    private TableColumn<VenteModel, String> colmedic;
    @FXML
    private TableColumn<VenteModel, String> colprix;
    @FXML
    private TableColumn<VenteModel, String> colqte;
    @FXML
    private JFXButton btnvente;
  Connection con;
    database db = new database();

    ObservableList<VenteModel> o = FXCollections.observableArrayList();
    @FXML
    private StackPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con = db.getcon();
        initCellule();
    }    

    
     private void initCellule() {
        //cmbFilter.getItems().addAll("Code", "Etat", "Marque", "Bailleur", "Type", "Montant");
        // cmbFilter.setValue("Code");
        colnum.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        colnomfour.setCellValueFactory(new PropertyValueFactory<>("nom_fournisseur"));
        colmedic.setCellValueFactory(new PropertyValueFactory<>("medicament"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colqte.setCellValueFactory(new PropertyValueFactory<>("qte"));

        getData();

        table.getSelectionModel().selectedIndexProperty().addListener((obs, old, newv) -> {
           // activeButton();
        });

    }

    public void getData() {
        table.getItems().clear();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `v_vente`");

            ResultSet rs = ps.executeQuery();

            int n = 1;

            while (rs.next()) {
                o.add(new VenteModel(rs.getInt("id_vente"), rs.getString("nom_client"), rs.getString("nom_fournisseur"), rs.getString("desc_medic"), rs.getInt("prix"), rs.getInt("qte")));

                n++;
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }

        table.setItems(o);
    }
    
    
    @FXML
    private void onNewVente(ActionEvent event) {
         try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/vue/addVente.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }
    
}
