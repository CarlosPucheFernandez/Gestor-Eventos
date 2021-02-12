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
        models.Reserva re = new models.Reserva();
        re.setNombre(nombre);
        re.setApellido1(Apellido1);
        re.setApellido2(Apellido2);
        re.setMail(Mail);
        re.setObservaciones(Observaciones);
        re.setTelefono(telefono);
        re.setAcompanantes(acompanantes);
        re.setEvento(evento);
        s.save(re);
        t.commit();
        s.refresh(re);
    }

    public void editarReserva(String nombre, String Apellido1, String Apellido2, String Mail, String Observaciones, int acompanantes, String telefono, Evento evento, int id) {
        comprobarTransaccion();
        models.Reserva re = s.get(Reserva.class, id);
        re.setNombre(nombre);
        re.setApellido1(Apellido1);
        re.setApellido1(Apellido2);
        re.setMail(Mail);
        re.setObservaciones(Observaciones);
        re.setTelefono(telefono);
        re.setAcompanantes(acompanantes);
        re.setEvento(evento);
        s.update(re);
        t.commit();
        s.refresh(re);
    }

    //metodo para borrar un evento
    public void borrarReserva(Reserva res) {
        comprobarTransaccion();
        s.delete(res);
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
