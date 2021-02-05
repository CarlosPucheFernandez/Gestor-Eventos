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
        models.Evento ac = new models.Evento();
        ac.setNombre(nombre);
        ac.setFecha(fecha);
        ac.setAforo(aforo);
        s.save(ac);
        t.commit();
    }
    
    public void editarEvento(String nombre, Date fecha, int aforo, int id) {
        comprobarTransaccion();
        models.Evento ac = s.get(Evento.class, id);
        ac.setNombre(nombre);
        ac.setFecha(fecha);
        ac.setAforo(aforo);
        s.update(ac);
        t.commit();
    }
    
    //metodo para borrar un evento
    
    public void borrarEvento(int id) {
        comprobarTransaccion();
        Query borrarsql = s.createQuery("DELETE Evento where id=:id_borrar");
        borrarsql.setParameter("id_borrar", id);
        if (borrarsql.executeUpdate() != 0) {
            System.out.println("Todo correcto");
        } else {
            System.out.println("No se elimino nada");
        }
        t.commit();
    }

    public ObservableList<Reserva> mostrarReserva(int id) {
        ObservableList<Reserva> reservas = FXCollections.observableArrayList();
        reservas.addAll(s.createQuery("FROM Reserva where id_evento = " + id).list());
        return reservas;
    }
    
    public ObservableList<Evento> mostrarEventos() {
        ObservableList<Evento> Eventos = FXCollections.observableArrayList();
        Eventos.addAll(s.createQuery("FROM Evento").list());
        return Eventos;
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
