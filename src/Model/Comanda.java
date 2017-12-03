
package Model;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comanda {
    
    private int id;
    private int mesa_id;
    private String abertura;
    private String fechamento;
    

    public Comanda() {
    }

    public Comanda(int id, int mesa_id, String abertura, String fechamento) {
        this.id = id;
        this.mesa_id = mesa_id;
        this.abertura = abertura;
        this.fechamento = fechamento;
    }

    public Comanda(int mesa_id, String abertura, String fechamento) {
  
        this.mesa_id = mesa_id;
        this.abertura = abertura;
        this.fechamento = fechamento;
    }


    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public int getMesa() {
        return mesa_id;
    }

    public void setMesa(int mesa) {
        this.mesa_id = mesa;
    }

  
    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getFechamento() {
        return fechamento;
    }

    public void setFechamento(String fechamento) {
        this.fechamento = fechamento;
    }
}
    

