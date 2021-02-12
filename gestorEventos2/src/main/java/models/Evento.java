package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author carlo
 */
public class Evento implements Serializable {
//definicion de variables

    private Integer id;

    private String nombre;
    private Date fecha;
    private Integer aforo;
    
     private Set<models.Reserva> reservas;
    
//constructor sin parametros

    public Evento() {
    }
    
//constructor con parametros

    public Evento(int aforo, String nombre, Date fecha) {
        this.aforo = aforo;
        this.nombre = nombre;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return nombre + " - " + fecha + " \\ " + aforo + "." ;
    }
    
//metodos

}
