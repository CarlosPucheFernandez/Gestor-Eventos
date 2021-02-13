/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestoreventos2;

import config.JasperConection;
import controladores.AdminEvento;
import controladores.AdminReserva;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Evento;
import models.Reserva;
import net.sf.jasperreports.engine.JRException;

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
    @FXML
    private Button btnImprimir;
    private ObservableList<Reserva> ol = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set de la tabla
        tablaEventos.setItems(ol);
        colNombre.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombre"));
        colApe1.setCellValueFactory(new PropertyValueFactory<Reserva, String>("apellido1"));
        colApe2.setCellValueFactory(new PropertyValueFactory<Reserva, String>("apellido2"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Reserva, String>("telefono"));
        colMail.setCellValueFactory(new PropertyValueFactory<Reserva, String>("mail"));
        colAcompañantes.setCellValueFactory(new PropertyValueFactory<Reserva, Integer>("acompanantes"));
        //set del combobox
        cmbEvento.setItems(ae.mostrarEventos());
        btnImprimir.setText("Imprimir Todo");
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
        btnImprimir.setText("Imprimir Evento");
    }

    private void UpdateTabla() {
        System.out.println("update");
        System.out.println(ae.mostrarReserva(cmbEvento.getValue().getId()).toString());
        tablaEventos.setItems(ae.mostrarReserva(cmbEvento.getValue().getId()));
    }

    @FXML
    private void editarReserva(ActionEvent event) {
        try {
            Reserva res = this.tablaEventos.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrarseEditar.fxml"));
            Parent root;
            root = fxmlLoader.load();
            RegistrarseEditarController controlador = fxmlLoader.getController();
            controlador.initAttributtes(res);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Editar Reserva");
            stage.setScene(new Scene(root));
            stage.showAndWait();
//            tablaEventos.getItems().clear();
            UpdateTabla();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void eliminarReserva(ActionEvent event) {
        Reserva res = this.tablaEventos.getSelectionModel().getSelectedItem();
        AdminReserva ar = new AdminReserva();
        ar.borrarReserva(res);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guardar");
        alert.setHeaderText("");
        alert.setContentText("reserva eliminada correctamente");
        alert.showAndWait();
        tablaEventos.getItems().clear();
        actualizarTabla(event);
    }

    @FXML
    private void imprimir(ActionEvent event) throws JRException, IOException, FileNotFoundException, SQLException {
        JasperConection jc = new JasperConection();
        if (btnImprimir.getText() == "Imprimir Todo") {
            jc.ImprimirTodos();
        } else {
            jc.ImprimirIndividual(cmbEvento.getValue().getNombre(), cmbEvento.getValue().getId());
        }
    }

}
