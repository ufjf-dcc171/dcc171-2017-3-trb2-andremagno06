/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.Locale;
import java.util.NoSuchElementException;

import java.io.FileWriter;
import java.io.IOException;
import Model.Item;
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
public class ItemDAO {

    private static ItemDAO instance;
    static ArrayList<Item> itens = new ArrayList<>();

    public static ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }
        return instance;
    }

    public void criar(Item prod) throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{ItemWrapper.class, Item.class});
        StreamResult saida = new StreamResult("Item.xml");
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ItemWrapper pw = new ItemWrapper(listarTodos());
        pw.getItems().add(prod);

        JAXBElement<ItemWrapper> e1 = new JAXBElement<>(new QName("itens"), ItemWrapper.class, pw);
        marshaller.marshal(e1, saida);
    }

    public void remove(int id) throws Exception {
        ItemWrapper pw = new ItemWrapper(listarTodos());
        for (int i = 0; i < pw.getItems().size(); i++) {
            if (pw.getItems().get(i).getId() == id) {
                pw.remove(i);
            }
            JAXBContext ctx = JAXBContext.newInstance(new Class[]{ItemWrapper.class, Item.class});
            StreamResult saida = new StreamResult("Item.xml");
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            JAXBElement<ItemWrapper> e1 = new JAXBElement<>(new QName("itens"), ItemWrapper.class, pw);
            marshaller.marshal(e1, saida);
        }

    }

    public ArrayList<Item> listarTodos() throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{ItemWrapper.class, Item.class});
        StreamSource entrada = new StreamSource("Item.xml");
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        JAXBElement<ItemWrapper> e1 = unmarshaller.unmarshal(entrada, ItemWrapper.class);
        ItemWrapper pw = e1.getValue();
        return pw.getItems();
    }

       public Item getItem(int id) throws Exception {
       ItemWrapper pw = new ItemWrapper(listarTodos());
        for (int i = 0; i < pw.getItems().size(); i++) {
            if (pw.getItems().get(i).getId() == id) {
                return pw.getItems().get(i);
            }
        }
        return null;
    }
    
    
    
    public int nomeParaId(String toString) throws Exception {
        ItemWrapper pw = new ItemWrapper(listarTodos());
        for (int i = 0; i < pw.getItems().size(); i++) {
            if (pw.getItems().get(i).getNome().equals(toString)) {
                return pw.getItems().get(i).getId();
            }
        }
        return -1;
    }

    public void add(String text, String text0, double parseDouble) throws Exception {
        itens = listarTodos();
        Item i = new Item(itens.size() + 1, text, text0, parseDouble);
        criar(i);
    }

    @XmlRootElement
    static class ItemWrapper {

        private ArrayList<Item> items;

        public ItemWrapper(ArrayList<Item> items) {
            this.items = items;
        }

        public ItemWrapper() {
            this(new ArrayList<Item>());
        }

        @XmlAnyElement(lax = true)
        public ArrayList<Item> getItems() {
            return items;
        }

        public void remove(int x) {
            items.remove(x);
        }

    }

}
    
    
    
    

    
    
    
    /*

    public void criarArquivo(ArrayList<Item> item) throws IOException {

        FileWriter fileW = new FileWriter("Item.txt", false);
        BufferedWriter conexao = new BufferedWriter(fileW);
        for (Item i : item) {
            conexao.write(i.getId() + " " + i.getNome() + " " + i.getDescricao() + " " + i.getPreco());
            conexao.newLine();
        }
        conexao.close();
    }

    public ArrayList<Item> lerTodos() throws IOException {
        ArrayList<Item> item = new ArrayList<>();
        entrada = new Scanner(new FileReader("item.txt")).useDelimiter(" ");
        entrada.useLocale(Locale.ENGLISH);

        while (entrada.hasNext()) {
            Item i = new Item();
            i.setId(entrada.nextInt());
            i.setNome(entrada.next());
            i.setDescricao(entrada.next());
            i.setPreco(entrada.nextDouble());
            item.add(i);
        }

        entrada.close();
        return item;
    }

    public Boolean vazio() throws IOException {

        Scanner input;
        input = new Scanner("Item.txt");
        if (arquivo.exists() && arquivo.length() != 0) {
            input.close();
            return false;
        } else {
            input.close();
            return true;
        }
        
    }
}


*/
