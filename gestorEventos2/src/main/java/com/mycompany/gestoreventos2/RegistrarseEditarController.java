/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestoreventos2;

import controladores.AdminEvento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import models.Evento;
import models.Reserva;
/**
 * FXML Controller class
 *
 * @author carlo
 */
public class RegistrarseEditarController implements Initializable {


    @FXML
    private ComboBox<Evento> comboEventos;
    @FXML
    private TextField tfNombre;
    @FXML
    private Spinner<String> espinAcompanantes;
    @FXML
    private TextField tfApe1;
    @FXML
    private TextField tfApe2;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfMail;
    @FXML
    private TextArea taObservaciones;
    @FXML
    private Label lblErrores;
    @FXML
    private Button btnGuardar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void ComprobarTelefono(KeyEvent event) {
    }

    @FXML
    private void ComprobarCorreo(KeyEvent event) {
    }

    @FXML
    private void Guardar(ActionEvent event) {
    }
    
     public void initAttributtes(Reserva res){
        System.out.println("init editar");
         AdminEvento ae = new AdminEvento();
        this.tfNombre.setText(res.getNombre());
        this.tfApe1.setText(res.getApellido1());
        this.tfApe2.setText(res.getApellido2());
        this.tfMail.setText(res.getMail());
        this.tfTelefono.setText(res.getTelefono());
        this.taObservaciones.setText(res.getObservaciones());
        this.comboEventos.setItems(ae.mostrarEventos());  
        ObservableList<String> num1 = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        SpinnerValueFactory<String> numeros = new SpinnerValueFactory.ListSpinnerValueFactory<String>(num1);
        espinAcompanantes.setValueFactory(numeros);
    }

}
