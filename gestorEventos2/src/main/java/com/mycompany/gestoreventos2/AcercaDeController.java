/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestoreventos2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carlo
 */
public class AcercaDeController implements Initializable {

    @FXML
    private VBox ContenedorMenu;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnReservas;
    @FXML
    private Button btnCrearEvento;
    @FXML
    private Button btnListaEventos;
    @FXML
    private Button btnAcercaDe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void AbrirInicio(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void AbrirReservar(ActionEvent event) throws IOException {
        App.setRoot("registrarse");
    }

    @FXML
    private void AbrirCrearEvento(ActionEvent event) throws IOException {
        App.setRoot("crearEvento");
    }

    @FXML
    private void AbrirListaEventos(ActionEvent event) throws IOException {
        App.setRoot("listaEventos");
    }

    @FXML
    private void AbrirAcercaDe(ActionEvent event) throws IOException {
        App.setRoot("acercaDe");
    }

}
