package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco
 */
public class consultaAutos {

    conexion miConexion;
    public Connection accesoBD = null;

    //resultSet de consultas
    public ResultSet rs = null;

    //sentencias para registro de datos personales
    private Statement consulta;

    //sentencia de entrada de consulta de automoviles
    private String consulAutos = "SELECT a.placa,a.entrada,c.tipoAcceso,c.tarifa FROM"
            + " automovil a, acceso c WHERE a.tipoAcceso=c.idAcceso AND a.salida IS NULL";

    private String consulReporte = "SELECT a.placa,a.entrada,a.salida,TIMESTAMPDIFF(minute,a.entrada,a.salida) "
            + "as tiempo, c.tipoAcceso, (c.tarifa*TIMESTAMPDIFF(minute,a.entrada,a.salida)) "
            + "as costo FROM automovil a,acceso c WHERE a.tipoAcceso=c.idAcceso AND "
            + "a.salida IS NOT NULL ";

    public consultaAutos() {
        miConexion = new conexion();
    }

    public void llenaTabla(DefaultTableModel dm, JTable tabla) {
        accesoBD = miConexion.getConexion();

        dm.addColumn("Num.Placa");
        dm.addColumn("Entrada");
        dm.addColumn("Tipo de Acceso");
        dm.addColumn("Tarifa por min");

        try {
            consulta = accesoBD.createStatement();
            rs = consulta.executeQuery(consulAutos);

            String datos[] = new String[4];

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = "$" + rs.getDouble(4);

                dm.addRow(datos);

                tabla.setModel(dm);
                tabla.repaint();

            }
        } catch (SQLException ex) {
            System.out.println("error tablaAutos " + ex.toString());
        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }
                if (consulta != null) {
                    consulta.close();
                }

                if (accesoBD != null) {
                    accesoBD.close();
                }

            } catch (SQLException ex) {
                System.out.println("tablaAutos, error cerrarBD " + ex.toString());
            }
        }

    }

    public void llenaReporte(DefaultTableModel dm, JTable tabla) {
        accesoBD = miConexion.getConexion();
        rs = null;

        dm.addColumn("Num.placa");
        dm.addColumn("Entrada");
        dm.addColumn("Salida");
        dm.addColumn("Tiempo estcionado (min)");
        dm.addColumn("tipo");
        dm.addColumn("importe");

        try {
            consulta = accesoBD.createStatement();
            rs = consulta.executeQuery(consulReporte);

            String datos[] = new String[6];

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);

                dm.addRow(datos);

                tabla.setModel(dm);
                tabla.repaint();
            }

        } catch (SQLException ex) {
            System.out.println("error tablaReporte " + ex.toString());
        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }
                if (consulta != null) {
                    consulta.close();
                }

                if (accesoBD != null) {
                    accesoBD.close();
                }

            } catch (SQLException ex) {
                System.out.println("tablaReporte, error cerrarBD " + ex.toString());
            }
        }

    }

}
