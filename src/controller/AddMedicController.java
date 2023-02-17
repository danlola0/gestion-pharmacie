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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import modele.MedicamentModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddMedicController implements Initializable {

    @FXML
    private StackPane root;
    @FXML
    private TextField t_desc;
    @FXML
    private TextField t_prix;
    @FXML
    private TextField t_qte;
    @FXML
    private JFXButton btnSave;
    @FXML
    private ImageView iclose;
    Connection con;
    database db = new database();
    boolean edit = false;
    int id = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iclose.setImage(new Image("/images/close.png"));
        con = db.getcon();

    }

    @FXML
    private void onSave(ActionEvent event) {
        if (this.edit) {
            try {
                PreparedStatement ps = con.prepareStatement("UPDATE `medicament` SET `description`=?,`prix`=?,`qte`=? WHERE `id`=?");
                ps.setString(1, t_desc.getText());
                ps.setInt(2, Integer.parseInt(t_prix.getText()));
                ps.setInt(3, Integer.parseInt(t_qte.getText()));
                ps.setInt(4, id);

                ps.execute();
                System.out.println("Mise a jour reusssie!");
                returnMe();

            } catch (Exception e) {
                System.err.println("erreur " + e.getMessage());
            }
        } else {
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO `medicament`(`description`, `prix`, `qte`) VALUES (?,?,?)");
                ps.setString(1, t_desc.getText());
                ps.setInt(2, Integer.parseInt(t_prix.getText()));
                ps.setInt(3, Integer.parseInt(t_qte.getText()));

                ps.execute();
                System.out.println("Insertion reusssie!");
                returnMe();

            } catch (Exception e) {
                System.err.println("erreur " + e.getMessage());
            }
        }
    }

    @FXML
    private void onClose(ActionEvent event) {
        returnMe();
    }

    private void returnMe() {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/vue/medicament.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }

    public void initMe(MedicamentModel m) {
        this.t_desc.setText(m.getDesc());
        this.t_prix.setText("" + m.getPrix());
        this.t_qte.setText("" + m.getQte());
        this.edit = true;
        this.id = m.getId();
    }

}
