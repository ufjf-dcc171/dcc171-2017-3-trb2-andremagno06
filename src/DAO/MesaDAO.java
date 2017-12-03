/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Comanda;
import Model.Item;
import java.util.Locale;
import java.util.NoSuchElementException;

import java.io.FileWriter;
import java.io.IOException;
import Model.Mesa;

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
public class MesaDAO {

    private static MesaDAO instance;
    static ArrayList<Mesa> mesas = new ArrayList<>();

    public static MesaDAO getInstance() {
        if (instance == null) {
            instance = new MesaDAO();
        }
        return instance;
    }



    public void criar(Mesa prod) throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{MesaWrapper.class, Mesa.class});
        StreamResult saida = new StreamResult("Mesa.xml");
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        MesaWrapper pw = new MesaWrapper(listarTodos());
         pw.getMesas().add(prod);
        JAXBElement<MesaWrapper> e1 = new JAXBElement<>(new QName("mesas"), MesaWrapper.class, pw);
        marshaller.marshal(e1, saida);
    }
   public void remove(int id) throws Exception{
         MesaWrapper pw = new MesaWrapper(listarTodos());
         for (int i = 0; i < pw.getMesas().size(); i++) {
           if(pw.getMesas().get(i).getId()==id){
               pw.remove(i);
           }
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{MesaWrapper.class, Mesa.class});
        StreamResult saida = new StreamResult("Mesa.xml");
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        JAXBElement<MesaWrapper> e1 = new JAXBElement<>(new QName("mesas"), MesaWrapper.class, pw);
        marshaller.marshal(e1, saida);
       }
         

         
   }
   
   
       public String getMesa(int id) throws Exception {
       MesaWrapper pw = new MesaWrapper(listarTodos());
        for (int i = 0; i < pw.getMesas().size(); i++) {
            if (pw.getMesas().get(i).getId()==id) {
                return pw.getMesas().get(i).getCodigo();
            }
        }
        return "Nao retornou nada";
    }
       
       public int nomeParaId(String toString) throws Exception {
        MesaWrapper pw = new MesaWrapper(listarTodos());
           System.out.println("mmm"+toString);
        for (int i = 0; i < pw.getMesas().size(); i++) {
             System.out.println(pw.getMesas().get(i).getCodigo());
            if (pw.getMesas().get(i).getCodigo().equals(toString)) {
                return pw.getMesas().get(i).getId();
            }
        }
        return -1;
    }
   
    public ArrayList<Mesa> listarTodos() throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{MesaWrapper.class, Mesa.class});
        StreamSource entrada = new StreamSource("Mesa.xml");
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        JAXBElement<MesaWrapper> e1 = unmarshaller.unmarshal(entrada, MesaWrapper.class);
        MesaWrapper pw = e1.getValue();
        return pw.getMesas();
    }

    public void add(String codigo, int lugares) throws Exception {
        mesas=listarTodos();
        Mesa i=new Mesa(mesas.size()+1,codigo,lugares);
        criar(i);
    }
    
        @XmlRootElement
    static class MesaWrapper{
            
         
            
        private ArrayList<Mesa> items;

      
        
        public MesaWrapper(ArrayList<Mesa> items) {
            this.items = items;
        }

        public MesaWrapper() {
            this(new ArrayList<Mesa>());
        }

        @XmlAnyElement(lax = true)
        public ArrayList<Mesa> getMesas() {
            return items;    
        }
        
        
       
        public void remove(int x) {
            items.remove(x);
        }
      
        
    }
    
    
    
}
    