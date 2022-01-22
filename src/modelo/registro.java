package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Francisco
 */
public class registro {

    conexion miConexion;
    public Connection accesoBD = null;

    //resultSet de consultas
    public ResultSet rs = null;

    //sentencias
    private PreparedStatement registra;
    private Statement sentencia;

    //sentencia de entrada de automovil
    private String regEntrada = "INSERT INTO automovil (placa,entrada,tipoAcceso,salida)"
            + " VALUES (?,?,?,?)";

    //sentencia de salida de automovil
    private String regSalida = "UPDATE automovil SET salida='";

    public registro() {
        miConexion = new conexion();
    }

    public void registraEntrada(automovil auto) {
        accesoBD = miConexion.getConexion();

        try {
            //ponemos el autocommit como falso para ejecutar una transaccion
            accesoBD.setAutoCommit(false);

            registra = accesoBD.prepareStatement(regEntrada);

            registra.setString(1, auto.getPlaca());
            registra.setTimestamp(2, auto.getEntrada());
            registra.setInt(3, auto.getTipoAcceso());
            registra.setTimestamp(4, null);

            registra.executeUpdate();
            accesoBD.commit();
            JOptionPane.showMessageDialog(null, "Registro exitoso!");

        } catch (SQLException ex) {

            try {
                if (accesoBD != null) {
                    accesoBD.rollback();
                    JOptionPane.showMessageDialog(null, "Hubo problemas para registrar los datos"
                            + "\nIntente mas tarde porfavor");
                }
            } catch (SQLException u) {
                System.out.println("err: regEntr " + ex.toString());
            }

            System.out.println("111 err: regEntr " + ex.toString());
        }
//al finalizar cerramos los objetos de base de datos
        try {
            if (registra != null) {
                registra.close();
            }
            if (accesoBD != null) {
                accesoBD.close();
            }

        } catch (SQLException ux) {
            System.out.println("registro 117: error al cerrar BD " + ux.toString());
        }

    }

    public void registraSalida(automovil auto) {
        accesoBD = miConexion.getConexion();

        try {
            //sentencia de busqueda de automovil
            String busAuto = "SELECT idAuto FROM automovil WHERE "
                    + "placa='" + auto.getPlaca() + "' AND entrada='" + auto.getEntrada()+"'";
            
            System.out.println(busAuto);

            //ponemos el autocommit como falso para ejecutar una transaccion
            accesoBD.setAutoCommit(false);
            sentencia = accesoBD.createStatement();

            rs = sentencia.executeQuery(busAuto);

            if (rs.next()) {
                auto.setIdAuto(rs.getInt(1));

                regSalida = regSalida + auto.getSalida()
                        +"' WHERE idAuto="+auto.getIdAuto();
                
                System.out.println("SALIDA "+regSalida);

                sentencia = accesoBD.createStatement();

                 sentencia.executeUpdate(regSalida);
                 
                 accesoBD.commit();
                 
                 JOptionPane.showMessageDialog(null, "se registro salida!");

            } else {
                JOptionPane.showMessageDialog(null, "no se encontro auto");
            }

        }catch (SQLException ex) {

            try {
                if (accesoBD != null) {
                    accesoBD.rollback();
                    JOptionPane.showMessageDialog(null, "Hubo problemas para registrar la salida"
                            + "\nIntente mas tarde porfavor");
                }
            } catch (SQLException u) {
                System.out.println("err: regSal " + ex.toString());
            }

            System.out.println("111 err: regSAL " + ex.toString());
        }
//al finalizar cerramos los objetos de base de datos
        try {
            if (sentencia != null) {
                sentencia.close();
            }
            if (accesoBD != null) {
                accesoBD.close();
            }

        } catch (SQLException ux) {
            System.out.println("registro 117: error al cerrar BD " + ux.toString());
        }

    }

}
