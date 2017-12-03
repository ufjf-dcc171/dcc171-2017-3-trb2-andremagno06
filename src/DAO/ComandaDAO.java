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
import Model.Comanda;

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
public class ComandaDAO {

    private static ComandaDAO instance;

    public static ComandaDAO getInstance() {
        if (instance == null) {
            instance = new ComandaDAO();
        }
        return instance;
    }

    public void criar(Comanda prod) throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{ComandaWrapper.class, Comanda.class});
        StreamResult saida = new StreamResult("Comanda.xml");
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ComandaWrapper pw = new ComandaWrapper(listarTodos());
        pw.getComandas().add(prod);
        JAXBElement<ComandaWrapper> e1 = new JAXBElement<>(new QName("comandas"), ComandaWrapper.class, pw);
        marshaller.marshal(e1, saida);
    }

    public void remove(int id) throws Exception {
        ComandaWrapper pw = new ComandaWrapper(listarTodos());
        for (int i = 0; i < pw.getComandas().size(); i++) {
            if (pw.getComandas().get(i).getId() == id) {
                pw.remove(i);
            }
            JAXBContext ctx = JAXBContext.newInstance(new Class[]{ComandaWrapper.class, Comanda.class});
            StreamResult saida = new StreamResult("Comanda.xml");
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            JAXBElement<ComandaWrapper> e1 = new JAXBElement<>(new QName("comandas"), ComandaWrapper.class, pw);
            marshaller.marshal(e1, saida);
        }

    }

    public Comanda getComanda(int id) throws Exception {
        ComandaWrapper pw = new ComandaWrapper(listarTodos());
        for (int i = 0; i < pw.getComandas().size(); i++) {
            if (pw.getComandas().get(i).getId() == id) {
                return pw.getComandas().get(i);
            }
        }
        System.out.println("nulldgadg");
        return null;
    }

    public ArrayList<Comanda> listarTodos() throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{ComandaWrapper.class, Comanda.class});
        StreamSource entrada = new StreamSource("Comanda.xml");
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        JAXBElement<ComandaWrapper> e1 = unmarshaller.unmarshal(entrada, ComandaWrapper.class);
        ComandaWrapper pw = e1.getValue();
        return pw.getComandas();
    }

    public void add(Comanda i) throws Exception {
        ArrayList<Comanda> c = listarTodos();
        i.setId(c.size() + 1);
        criar(i);
    }

    public void setHoraFechamento(int id, String data) throws JAXBException, Exception {
        ComandaWrapper pw = new ComandaWrapper(listarTodos());
        for (int i = 0; i < pw.getComandas().size(); i++) {
            if (pw.getComandas().get(i).getId() == id) {
                pw.getComandas().get(i).setFechamento(data);
            }
        }
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{ComandaWrapper.class, Comanda.class});
        StreamResult saida = new StreamResult("Comanda.xml");
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        JAXBElement<ComandaWrapper> e1 = new JAXBElement<>(new QName("comandas"), ComandaWrapper.class, pw);
        marshaller.marshal(e1, saida);

    }

    @XmlRootElement
    static class ComandaWrapper {

        private ArrayList<Comanda> items;

        public ComandaWrapper(ArrayList<Comanda> items) {
            this.items = items;
        }

        public ComandaWrapper() {
            this(new ArrayList<Comanda>());
        }

        @XmlAnyElement(lax = true)
        public ArrayList<Comanda> getComandas() {
            return items;
        }

        public void remove(int x) {
            items.remove(x);
        }

    }

}
