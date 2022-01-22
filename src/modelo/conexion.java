
package modelo;
import java.sql.*;
import javax.swing.JOptionPane;
import java.io.*;

/**
 *
 * @author Francisco
 */
public class conexion {
    
    public Connection miConexion;
    
    public conexion(){
        miConexion = null;
    }
    
    public Connection getConexion() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/estacionamiento", "root", "");
            System.out.println("Conexion Establecida");

        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al Conectarse" + "\n" + error, "Mensaje Error", JOptionPane.ERROR_MESSAGE);
        }

        return miConexion;
    }
    
}
