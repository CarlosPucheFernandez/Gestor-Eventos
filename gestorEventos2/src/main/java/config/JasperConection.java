/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author carlo
 */
public class JasperConection {

    public Connection conexionDB() throws FileNotFoundException, IOException, SQLException {
        Properties params = new Properties();
        params.load(new FileReader("bbdd.cfg"));

        String protocol = params.getProperty("protocol");
        String server = params.getProperty("server");
        String port = params.getProperty("port");
        String database = params.getProperty("database");
        String extra = params.getProperty("extra");
        String user = params.getProperty("user");
        String password = params.getProperty("password");

        String url = protocol + server + ":" + port + "/" + database + "?" + extra;

        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println("conexion establecida");

        return con;
    }

    public void ImprimirTodos() throws JRException, IOException, FileNotFoundException, SQLException {
        String fileName = "GestorTodos.jasper";

        HashMap hm = new HashMap();
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(fileName, hm, conexionDB());

        JRPdfExporter exp = new JRPdfExporter();

        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteReserva.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }

    public void ImprimirIndividual(String nombreEvento, int idEvento) throws JRException, IOException, FileNotFoundException, SQLException {
        String fileName = "GestorIndividual.jasper";

        HashMap hm = new HashMap();
        hm.put("idEvento", idEvento);

        JasperPrint jasperPrint = JasperFillManager.fillReport(fileName, hm, conexionDB());

        JRPdfExporter exp = new JRPdfExporter();

        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ListaEvento" + nombreEvento + ".pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }
}
