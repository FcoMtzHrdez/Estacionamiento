package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Francisco
 */
public class tarifasMenu {

    conexion miConexion;
    public Connection accesoBD = null;
    public PreparedStatement sentencia = null;

    //resultSet de consultas
    public ResultSet rs = null;

    //sentencia de entrada de automovil
    private String consulAcceso = "SELECT idAcceso,tipoAcceso,tarifa FROM acceso;";

    public tarifasMenu() {
        miConexion = new conexion();
    }

    public void llenaCombo(JComboBox combo,ArrayList tarifas,ArrayList id) {
        
        accesoBD = miConexion.getConexion();
        
        try {
            sentencia = accesoBD.prepareStatement(consulAcceso);
            rs = sentencia.executeQuery();
            
            while(rs.next()){
                id.add(rs.getInt(1));
                combo.addItem(rs.getString(2));
                tarifas.add(rs.getDouble(3));
            }   
        } catch (SQLException ex) {
           System.out.println("error en consulta de tarifas");
        }
    }
}
