/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestoreventos2;

import controladores.AdminEvento;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import models.Evento;
import models.Reserva;
/**
 * FXML Controller class
 *
 * @author carlo
 */
public class ListaEventosController implements Initializable {


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
    @FXML
    private ComboBox<Evento> cmbEvento;
    @FXML
    private TableView<Reserva> tablaEventos;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableColumn<Reserva, String> colNombre;
    @FXML
    private TableColumn<Reserva, String> colApe1;
    @FXML
    private TableColumn<Reserva, String> colApe2;
    @FXML
    private TableColumn<Reserva, String> colTelefono;
    @FXML
    private TableColumn<Reserva, String> colMail;
    @FXML
    private TableColumn<Reserva, Integer> colAcompañantes;
    
    AdminEvento ae = new AdminEvento();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set del combobox
        cmbEvento.setItems(ae.mostrarEventos());  

        //set de la tabla
        ObservableList<Reserva> ol = null;
        tablaEventos.setItems(ol);
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

    @FXML
    private void actualizarTabla(ActionEvent event) {
        tablaEventos.setItems(ae.mostrarReserva(cmbEvento.getValue().getId()));
    }

    @FXML
    private void editarReserva(ActionEvent event) {
       try {
            Reserva res = this.tablaEventos.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registrarseEditar.fxml"));
            Parent root;
            root = (Parent) fxmlLoader.load();
            RegistrarseEditarController controlador = fxmlLoader.getController();
//            controlador.initAttributtes();
//            Stage stage = new Stage();
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initStyle(StageStyle.DECORATED);
//            stage.setTitle("Editar");
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void eliminarReserva(ActionEvent event) {
    }
    


}
