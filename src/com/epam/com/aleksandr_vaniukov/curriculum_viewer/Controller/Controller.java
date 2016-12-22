package com.epam.com.aleksandr_vaniukov.curriculum_viewer.Controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import com.epam.com.aleksandr_vaniukov.curriculum_viewer.model.DataStudents;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


/**
 * Created by Aleksandr_Vaniukov on 12/21/2016.
 */
public class Controller {

    private File file;
    private DataStudents dataStudents;

    public void setFile(File file){
        this.file=file;
        this.dataStudents=new DataStudents();
    }

    public void parseXML() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        NodeList nodeL=doc.getElementsByTagName("listStudents").item(0).getChildNodes();

        for(int i=0;i<nodeL.getLength();i++){
            System.out.println(nodeL.item(i).getLocalName());
        }
        //roundDOM();
    }

    public void roundDOM(Node root){
        if(root==null){
            return;
        }
        NodeList listNodes=root.getChildNodes();

        for(int i=0;i<listNodes.getLength();i++){
            printlnCommon(listNodes.item(i));
            System.out.println("###########");
            roundDOM(listNodes.item(i));
        }
    }

    private void printlnCommon(Node n) {
        System.out.print(" nodeName=\"" + n.getNodeName() + "\"");

        String val = n.getNamespaceURI();
        if (val != null) {
            System.out.print(" uri=\"" + val + "\"");
        }

        val = n.getPrefix();

        if (val != null) {
            System.out.print(" pre=\"" + val + "\"");
        }

        val = n.getLocalName();
        if (val != null) {
            System.out.print(" local=\"" + val + "\"");
        }

        val = n.getNodeValue();
        if (val != null) {
            System.out.print(" nodeValue=");
            if (val.trim().equals("")) {
                // Whitespace
                System.out.print("[WS]");
            }
            else {
                System.out.print("\"" + n.getNodeValue() + "\"");
            }
        }
        System.out.println();
    }

}
