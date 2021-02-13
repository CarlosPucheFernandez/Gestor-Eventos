/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import config.HibernateUtil;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Evento;
import models.Reserva;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlo
 */
public class AdminEvento {
//definicion de variables

    private Session s = HibernateUtil.getSessionFactory().openSession();
    private Transaction t = s.beginTransaction();

//Inicializador
    public AdminEvento() {
    }

//metodos acceso a datos
    public void nuevoEvento(String nombre, Date fecha, int aforo) {
        comprobarTransaccion();
        models.Evento ev = new models.Evento();
        ev.setNombre(nombre);
        ev.setFecha(fecha);
        ev.setAforo(aforo);
        s.save(ev);
        t.commit();
    }

    public void editarEvento(String nombre, Date fecha, int aforo, int id) {
        comprobarTransaccion();
        models.Evento ev = s.get(Evento.class, id);
        ev.setNombre(nombre);
        ev.setFecha(fecha);
        ev.setAforo(aforo);
        s.update(ev);
        t.commit();
        s.refresh(ev);
    }

    //metodo para borrar un evento
    public void borrarEvento(Evento ev) {
        comprobarTransaccion();
        s.delete(ev);
        t.commit();
    }

    public ObservableList<Reserva> mostrarReserva(int id) {
        ObservableList<Reserva> reservas = FXCollections.observableArrayList();
        reservas.addAll(s.createQuery("FROM Reserva where id_evento = " + id).list());
        return reservas;
    }

    public ObservableList<Evento> mostrarEventos() {
        ObservableList<Evento> eventos = FXCollections.observableArrayList();
        eventos.addAll(s.createQuery("FROM Evento").list());
        return eventos;
    }

//metodo para comprobar si la transaccion de hibernate esta abierta o no y activarla
    public void comprobarTransaccion() {
        if (t == null) {
            t = s.beginTransaction();
        } else if (!this.t.isActive()) {
            t = s.beginTransaction();
        }
    }
}
