/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestoreventos2;

import config.HibernateUtil;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import models.Evento;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author carlo
 */
public class RegistrarseController implements Initializable {

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

    private boolean todoOk;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //set del spinner
        ObservableList<String> num1 = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        SpinnerValueFactory<String> numeros = new SpinnerValueFactory.ListSpinnerValueFactory<String>(num1);
        espinAcompanantes.setValueFactory(numeros);
        
        //set del combobox
        AdminEvento ae = new AdminEvento();
        comboEventos.setItems(ae.mostrarEventos());  
    
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
    private void ComprobarTelefono(KeyEvent event) {
        if(tfTelefono.getText().length() != 9){
            lblErrores.setVisible(true);
            lblErrores.setText("El telefono no es correcto");
            todoOk = false;
        }else{
            lblErrores.setVisible(false);
            todoOk = true;
        }
    }

    @FXML
    private void ComprobarCorreo(KeyEvent event) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      if(!tfMail.getText().matches(regex)){
          lblErrores.setVisible(true);
          lblErrores.setText("El email no es correcto");
          todoOk = false;
      }else{
          lblErrores.setVisible(false);
          todoOk = true;
      }
    }
    
    private void comprobarRequeridos(){
        if(tfNombre.getText().isBlank() || tfApe1.getText().isBlank() || tfApe2.getText().isBlank() || tfTelefono.getText().isBlank() || tfMail.getText().isBlank()){
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
        if(todoOk){
            AdminReserva ar = new AdminReserva();
            System.out.println(tfNombre.getText()+ " , " + tfApe1.getText()+ " , " + tfApe2.getText()+ " , " + tfMail.getText()+ " , " + taObservaciones.getText()+ " , " + espinAcompanantes.getValue().charAt(0)+ " , " + tfTelefono.getText()+ " , " + comboEventos.getValue());
            ar.nuevoReserva(tfNombre.getText(), tfApe1.getText(), tfApe2.getText(), tfMail.getText(), taObservaciones.getText(), espinAcompanantes.getValue().charAt(0), tfTelefono.getText(), comboEventos.getValue());
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setHeaderText("Guardar");
            alert.setTitle("guardar");
            alert.setContentText("reserva realizada correctamente");
            alert.showAndWait();
            App.setRoot("registrarse");
        }
    }
    
}
