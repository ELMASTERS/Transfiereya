package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author mcabral
 */
public class Conexion {

    //conecion local
    public static Connection conectar() {

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/bd_transfiereya?autoReconnect=true&useSSL=false", "root", "123456");
            return cn;
        } catch (SQLException e) {

            System.out.println("Error en la conexion local" + e);
        }

        return null;

    }

}
