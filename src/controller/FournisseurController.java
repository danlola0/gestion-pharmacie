/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import modele.FournisseurModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FournisseurController implements Initializable {
    @FXML
    private StackPane root;
    @FXML
    private TableView<FournisseurModel> table;
    @FXML
    private TableColumn<FournisseurModel, String> colnum;
    @FXML
    private TableColumn<FournisseurModel, String> colnom;
    @FXML
    private TableColumn<FournisseurModel, String> coladresse;
    @FXML
    private TableColumn<FournisseurModel, String> colphone;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnMod;
    @FXML
    private JFXButton btnDel;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private ImageView ireche;
    @FXML
    private JFXTextField trecherche;
 Connection con;
    database db = new database();

    ObservableList<FournisseurModel> o = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ireche.setImage(new Image("/images/search.png"));
        deactiveButton();
        con = db.getcon();
        initCellule();
    }    

    private void initCellule() {
        //cmbFilter.getItems().addAll("Code", "Etat", "Marque", "Bailleur", "Type", "Montant");
        // cmbFilter.setValue("Code");
        colnum.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colphone.setCellValueFactory(new PropertyValueFactory<>("tel"));

        getData();

        table.getSelectionModel().selectedIndexProperty().addListener((obs, old, newv) -> {
            activeButton();
        });

    }

    public void getData() {
        table.getItems().clear();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `fournisseur`");

            ResultSet rs = ps.executeQuery();

            int n = 1;

            while (rs.next()) {
                o.add(new FournisseurModel(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("telephone")));

                n++;
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }

        table.setItems(o);
    }

    @FXML
    private void onAdd(ActionEvent event) {
        gotos("/vue/addFour.fxml");
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

    @FXML
    private void onModifier(ActionEvent event) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/vue/addFour.fxml").openStream());
            AddFourController cont = fXMLLoader.getController();
            cont.initMe(table.getSelectionModel().getSelectedItem());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM `fournisseur` WHERE `id`=?");

            ps.setInt(1, table.getSelectionModel().getSelectedItem().getId());

            ps.execute();
            System.out.println("Mise a jour reusssie!");
            getData();
        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    @FXML
    private void onSearchttext(ActionEvent event) {
        if (trecherche.getText().equals("")) {
            getData();
        } else {
            getData2();
        }
    }

    public void getData2() {
        table.getItems().clear();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `fournisseur` WHERE `nom` like ?");
            ps.setString(1, trecherche.getText() + "%");
            ResultSet rs = ps.executeQuery();

            int n = 1;

            while (rs.next()) {
              o.add(new FournisseurModel(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("telephone")));

                n++;
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }

        table.setItems(o);
    }

    private void activeButton() {
        btnDel.setDisable(false);
        btnMod.setDisable(false);
    }

    private void deactiveButton() {
        btnDel.setDisable(true);
        btnMod.setDisable(true);
    }
}
