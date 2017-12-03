package Model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mesa {
    
    private int id;
    private String  codigo;
    private int lugares;

    public Mesa() {
    }

    public Mesa(String codigo, int lugares) {
        this.codigo = codigo;
        this.lugares = lugares;
    }

    public Mesa(int id, String codigo, int lugares) {
        this.id = id;
        this.codigo = codigo;
        this.lugares = lugares;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
    }

    @Override
    public String toString() {
        return codigo; //To change body of generated methods, choose Tools | Templates.
    }

    
    
}