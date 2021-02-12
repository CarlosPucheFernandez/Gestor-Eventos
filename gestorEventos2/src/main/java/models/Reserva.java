package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Carlo
 */
public class Reserva implements Serializable {
    
//definicion de variables

    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Integer acompanantes;
    private String observaciones;
    private String telefono;
    private String mail;
    private Evento evento;

//constructor sin parametros

    public Reserva() {
    }
    
//constructor con parametros
    
    public Reserva(String nombre, String apellido1, String apellido2, String observaciones, int acompanantes, String telefono, String mail, Evento ev) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.observaciones = observaciones;
        this.acompanantes = acompanantes;
        this.telefono = telefono;
        this.evento = ev;
        this.mail = mail;
    }

//getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Integer getAcompanantes() {
        return acompanantes;
    }

    public void setAcompanantes(Integer acompanantes) {
        this.acompanantes = acompanantes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    

    @Override
    public String toString() {
        return "Reserva{" + "nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", observaciones=" + observaciones + ", acompanantes=" + acompanantes + ", telefono=" + telefono + ", mail=" + mail + '}';
    }
    
//metodos
    

}
