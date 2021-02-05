/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import config.HibernateUtil;
import java.util.Date;
import java.util.List;
import java.util.Set;
import models.Evento;
import models.Reserva;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlo
 */
public class AdminReserva {
//definicion de variables

    private Session s = HibernateUtil.getSessionFactory().openSession();
    private Transaction t = s.beginTransaction();
    
//Inicializador

    public AdminReserva() {
    }

//metodos acceso a datos
    
    public void nuevoReserva(String nombre, String Apellido1, String Apellido2, String Mail, String Observaciones, int acompanantes, String telefono, Evento evento) {
        comprobarTransaccion();
        models.Reserva ac = new models.Reserva();
        ac.setNombre(nombre);
        ac.setApellido1(Apellido1);
        ac.setApellido1(Apellido2);
        ac.setMail(Mail);
        ac.setObservaciones(Observaciones);
        ac.setTelefono(telefono);
        ac.setAcompanantes(acompanantes);
        ac.setEvento(evento);
        s.save(ac);
        t.commit();
    }
    
    public void editarReserva(String nombre, String Apellido1, String Apellido2, String Mail, String Observaciones, int acompanantes, String telefono, Evento evento, int id) {
        comprobarTransaccion();
        models.Reserva ac = s.get(Reserva.class, id);
        ac.setNombre(nombre);
        ac.setApellido1(Apellido1);
        ac.setApellido1(Apellido2);
        ac.setMail(Mail);
        ac.setObservaciones(Observaciones);
        ac.setTelefono(telefono);
        ac.setAcompanantes(acompanantes);
        ac.setEvento(evento);
        s.update(ac);
        t.commit();
    }
    
    //metodo para borrar un evento
    
    public void borrarReserva(int id) {
        comprobarTransaccion();
        Query borrarsql = s.createQuery("DELETE Reservas where id=:id_borrar");
        borrarsql.setParameter("id_borrar", id);
        if (borrarsql.executeUpdate() != 0) {
            System.out.println("Todo correcto");
        } else {
            System.out.println("No se elimino nada");
        }
        t.commit();
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
