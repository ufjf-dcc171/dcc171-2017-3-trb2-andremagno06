/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Model.Item;
import java.util.Locale;
import java.util.NoSuchElementException;

import java.io.FileWriter;
import java.io.IOException;
import Model.Pedido;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.NodeList;

/**
 *
 * @author Desenvolvedor
 */
public class PedidoDAO {

    private static PedidoDAO instance;
    static ArrayList<Pedido> pedidos = new ArrayList<>();

    public static PedidoDAO getInstance() {
        if (instance == null) {
            instance = new PedidoDAO();
        }
        return instance;
    }



    public void criar(Pedido prod) throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{PedidoWrapper.class, Pedido.class});
        StreamResult saida = new StreamResult("Pedido.xml");
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        PedidoWrapper pw = new PedidoWrapper(listarTodos());
         pw.getPedidos().add(prod);
        JAXBElement<PedidoWrapper> e1 = new JAXBElement<>(new QName("pedidos"), PedidoWrapper.class, pw);
        marshaller.marshal(e1, saida);
    }
   public void remove(int id) throws Exception{
         PedidoWrapper pw = new PedidoWrapper(listarTodos());
         for (int i = 0; i < pw.getPedidos().size(); i++) {
           if(pw.getPedidos().get(i).getId()==id){
               pw.remove(i);
           }
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{PedidoWrapper.class, Pedido.class});
        StreamResult saida = new StreamResult("Pedido.xml");
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        JAXBElement<PedidoWrapper> e1 = new JAXBElement<>(new QName("pedidos"), PedidoWrapper.class, pw);
        marshaller.marshal(e1, saida);
       }
         

         
   }
    public ArrayList<Pedido> listarTodos() throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{PedidoWrapper.class, Pedido.class});
        StreamSource entrada = new StreamSource("Pedido.xml");
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        JAXBElement<PedidoWrapper> e1 = unmarshaller.unmarshal(entrada, PedidoWrapper.class);
        PedidoWrapper pw = e1.getValue();
        return pw.getPedidos();
    }

    public void add(int comanda,int item,int quant, double preco) throws Exception {
        pedidos=listarTodos();
        Pedido i=new Pedido(pedidos.size()+1,comanda,item,quant, preco);
        criar(i);
    }

    public ArrayList<Pedido> listarTodosComanda(int id) throws JAXBException, Exception {
        PedidoWrapper pw = new PedidoWrapper(listarTodos());
        ArrayList<Pedido> pedidosC= new ArrayList<>();
        System.out.println("aaaa="+id);
         for (int i = 0; i < pw.getPedidos().size(); i++) {
             System.out.println("aaaa="+pw.getPedidos().get(i).getIdComanda());
           if(pw.getPedidos().get(i).getIdComanda()==id){
               pedidosC.add(pw.getPedidos().get(i));
           }
         }
        return pedidosC;
    }



        @XmlRootElement
    static class PedidoWrapper{
        private ArrayList<Pedido> items;

      
        
        public PedidoWrapper(ArrayList<Pedido> items) {
            this.items = items;
        }

        public PedidoWrapper() {
            this(new ArrayList<Pedido>());
        }

        @XmlAnyElement(lax = true)
        public ArrayList<Pedido> getPedidos() {
            return items;    
        }
        
 
        public void remove(int x) {
            items.remove(x);
        }
      
        
    }
    
    
    
}
    