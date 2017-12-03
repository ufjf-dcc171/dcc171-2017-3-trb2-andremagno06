package Model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pedido{
    
    
    private int id;
    private int IdComanda;
    private int item;
    private int quantidade;
    private double preco;
    

    public Pedido() {
    }

    public Pedido(int id, int IdComanda, int item, int quantidade, double preco) {
        this.id = id;
        this.item = item;
        this.quantidade = quantidade;
        this.preco = preco;
        this.IdComanda = IdComanda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdComanda(int IdComanda) {
        this.IdComanda = IdComanda;
    }

    public int getIdComanda() {
        return IdComanda;
    }


    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
    
}

