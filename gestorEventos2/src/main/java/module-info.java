module com.mycompany.gestoreventos2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.logging;
    requires java.sql;
    requires java.xml.bind;
    requires java.naming;
    requires org.jboss.logging;
    requires java.base;

    opens com.mycompany.gestoreventos2 to javafx.fxml, org.hibernate.orm.core, java.sql;
    opens models;
    exports com.mycompany.gestoreventos2;
}
