package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.automovil;
import modelo.consultaAutos;
import modelo.registro;
import vista.registroSalida;

/**
 *
 * @author Francisco
 */
public class contConsultas implements ActionListener {
    
    //lenar la tabla de autos en el estacionamiento
    DefaultTableModel miTabla;
    JTable tablaAutos;
    consultaAutos con=new consultaAutos();
    //registrar la salida de un auto
    registro reg= new registro();
    automovil auto=null;
    
    registroSalida ventSal;
    
    public contConsultas(registroSalida ventSal){
        this.ventSal=ventSal;
        this.tablaAutos=ventSal.tablaAutos;
    }
    
    public void iniciaTablaAuto(){
        miTabla = new DefaultTableModel();

        con.llenaTabla(miTabla, tablaAutos);
    }
    
    public boolean validaRenglon(){
        int row=this.tablaAutos.getSelectedRow();
        
        if(row==-1){
            JOptionPane.showMessageDialog(null, "seleccione un renglon para"
                    + "registrar la salida del auto porfavor");
        }else{
            auto= new automovil();
            
            auto.setPlaca((String) this.tablaAutos.getValueAt(row, 0));
            auto.setEntrada(Timestamp.valueOf(this.tablaAutos.getValueAt(row, 1).toString()));
            
             LocalDateTime now = LocalDateTime.now();
             
            auto.setSalida(Timestamp.valueOf(now));
            
            System.out.println(auto.getPlaca()+" "+auto.getEntrada()+" "+auto.getTipoAcceso());
            
            return true;
            
        }
        
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         Object objeto = ae.getSource();
         
         if(objeto==this.ventSal.bnAcepta){
             
             if(validaRenglon()){
                 reg.registraSalida(auto);
                 iniciaTablaAuto();
             } 
         }
    }
    
}
