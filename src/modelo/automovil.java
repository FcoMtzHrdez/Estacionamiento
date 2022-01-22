package modelo;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author Francisco
 */
public class automovil {
    
    private int idAuto;
    private String placa;
    private Timestamp entrada;
    private Timestamp salida;
    private int tipoAcceso;
    
    public automovil(){
        
    }
    
    public automovil(int idAuto, String placa, Timestamp entrada, Timestamp salida, int tipoAcceso) {
        this.idAuto = idAuto;
        this.placa = placa;
        this.entrada = entrada;
        this.salida = salida;
        this.tipoAcceso = tipoAcceso;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Timestamp getEntrada() {
        return entrada;
    }

    public void setEntrada(Timestamp entrada) {
        this.entrada = entrada;
    }

    public Timestamp getSalida() {
        return salida;
    }

    public void setSalida(Timestamp salida) {
        this.salida = salida;
    }

    public int getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(int tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }
    
    
}
