
package controlador;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.consultaAutos;
import vista.reportes;

/**
 *
 * @author Francisco
 */
public class contReporte {
    
    //lenar la tabla de autos en el estacionamiento
    DefaultTableModel miTabla;
    JTable tablaReport;
    
    consultaAutos con=new consultaAutos();
    
    reportes vent;
    
    public contReporte(reportes vent){
        this.vent=vent;
        this.tablaReport=vent.tablaReporte;
        
    }
    
    public void iniciaTablaRep(){
        miTabla= new DefaultTableModel();
        con.llenaReporte(miTabla, tablaReport);
    }
    
}
