package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import modelo.automovil;
import modelo.registro;
import vista.registroEntrada;

/**
 *
 * @author Francisco
 */
public class contRegistro implements ActionListener {

    registro log = new registro();
    automovil auto = null;

    registroEntrada venEnt = null;

    public contRegistro(registroEntrada venEnt) {
        this.venEnt = venEnt;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object objeto = ae.getSource();

        if (objeto == venEnt.bnAcepta) {

            if (validaEntrada()) {
                log.registraEntrada(auto);
                System.out.println("se registro"+auto.getPlaca() + " " + auto.getTipoAcceso());
            }

        } else if (objeto == venEnt.comboAcceso) {
            venEnt.lbTarifa.setText(venEnt.tarifas.get(venEnt.comboAcceso.getSelectedIndex()).toString());
        }

    }

    public boolean validaEntrada() {

        String placa = venEnt.txtPlaca.getText();

        if (placa.equals("")) {
            JOptionPane.showMessageDialog(null, "Error, ingrese un numero de placa");
        } else {
            this.auto = new automovil();
            this.auto.setPlaca(placa);
            this.auto.setTipoAcceso((int) venEnt.idTarifas.get(venEnt.comboAcceso.getSelectedIndex()));

           
            LocalDateTime now = LocalDateTime.now();
            
            this.auto.setEntrada(Timestamp.valueOf(now));
            
            System.out.println(this.auto.getEntrada());

            return true;
        }

        return false;
    }

}
