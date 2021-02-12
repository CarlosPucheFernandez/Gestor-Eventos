/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestoreventos2;

import controladores.AdminEvento;
import controladores.AdminReserva;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
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
    private Spinner<Integer> espinAcompanantes;
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

    private boolean todoOk;
    private int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    @FXML
    private void ComprobarTelefono(KeyEvent event) {
        if (tfTelefono.getText().length() != 9) {
            lblErrores.setVisible(true);
            lblErrores.setText("El telefono no es correcto");
            todoOk = false;
        } else {
            lblErrores.setVisible(false);
            todoOk = true;
        }
    }

    @FXML
    private void ComprobarCorreo(KeyEvent event) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!tfMail.getText().matches(regex)) {
            lblErrores.setVisible(true);
            lblErrores.setText("El email no es correcto");
            todoOk = false;
        } else {
            lblErrores.setVisible(false);
            todoOk = true;
        }
    }

    private void comprobarRequeridos() {
        if (tfNombre.getText().isBlank() || tfApe1.getText().isBlank() || tfApe2.getText().isBlank() || tfTelefono.getText().isBlank() || tfMail.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Cuidado");
            alert.setContentText("rellene correctamente todos los parametros obligatorios");
            alert.showAndWait();
            todoOk = false;
        }
    }

    @FXML
    private void Guardar(ActionEvent event) throws IOException {
        comprobarRequeridos();
        System.out.println(todoOk);
        if (todoOk) {
            AdminReserva ar = new AdminReserva();
            ar.editarReserva(tfNombre.getText(), tfApe1.getText(), tfApe2.getText(), tfMail.getText(), taObservaciones.getText(), espinAcompanantes.getValue(), tfTelefono.getText(), comboEventos.getValue(), id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("guardar");
            alert.setContentText("reserva realizada correctamente");
            alert.showAndWait();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    public void initAttributtes(Reserva res) {
        System.out.println("init editar");
        AdminEvento ae = new AdminEvento();
        this.tfNombre.setText(res.getNombre());
        this.tfApe1.setText(res.getApellido1());
        this.tfApe2.setText(res.getApellido2());
        this.tfMail.setText(res.getMail());
        this.tfTelefono.setText(res.getTelefono());
        this.taObservaciones.setText(res.getObservaciones());
        this.comboEventos.setItems(ae.mostrarEventos());
        id = res.getId();
        ObservableList<Integer> num1 = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        SpinnerValueFactory<Integer> numeros = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(num1);
        espinAcompanantes.setValueFactory(numeros);
        KeyEvent event = null;
        ComprobarTelefono(event);
        ComprobarCorreo(event);
    }
}
