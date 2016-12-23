package com.epam.com.aleksandr_vaniukov.curriculum_viewer.Controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.epam.com.aleksandr_vaniukov.curriculum_viewer.model.Course;
import com.epam.com.aleksandr_vaniukov.curriculum_viewer.model.DataStudents;
import com.epam.com.aleksandr_vaniukov.curriculum_viewer.model.Program;
import com.epam.com.aleksandr_vaniukov.curriculum_viewer.model.Student;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


/**
 * Created by Aleksandr_Vaniukov on 12/21/2016.
 */
public class Controller {

    private final String STUDENT="student";
    private final String DESCRIPTION_STUDENT="descriptionStudent";
    private final String DESCRIPTION_PROGRAM_STUDENT="descriptionProgramStudent";
    private final String LIST_COURSES="listCourses";
    private final String COURSE="course";
    private final String TASK="task";
    private final String THEORY_TASK="theoryTask";
    private final String PRACTICE_TASK="practiceTask";

    private final String CITY="city";
    private final String DATE_START="dateStart";
    private final String EMAIL="e-mail";
    private final String FULL_NAME="fullname";
    private final String SIGN_CONTRACT="signContract";

    private final String NAME="name";
    private final String AUTHOR="author";
    private final String DATE_CREATE="dateCreate";

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
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        NodeList nodeL=doc.getElementsByTagName("listStudents").item(0).getChildNodes();
        System.out.println(nodeL.getLength());
        /*
        for (int i=0;i<nodeL.getLength();i++){
            roundDOM(nodeL.item(i));
        }*/

        for(int i=0;i<nodeL.getLength();i++){
            nodeL.item(i);
            if(nodeL.item(i).getLocalName()!=null
                    &&
                    nodeL.item(i).getLocalName().equals(STUDENT)) {
                dataStudents.addStudent(createStudent(nodeL.item(i), new Student()));
            }
        }

    }

    private Student createStudent(Node root, Student student){

        NodeList nodeL=root.getChildNodes();

        for(int i=0;i<nodeL.getLength();i++){
            if(nodeL.item(i).getLocalName()!=null
                    &&
                    nodeL.item(i).getLocalName().equals(DESCRIPTION_STUDENT)){
                    fillAttributesStudent(nodeL.item(i).getAttributes(),student);
                student.setProgram(createProgram(root,new Program()));
            }
        }

        return student;
    }

    private void fillAttributesStudent(NamedNodeMap attributes,Student student) {
        student.setFullName(attributes.getNamedItem(FULL_NAME).getTextContent());
        student.setRegion(attributes.getNamedItem(CITY).getTextContent());
        student.setEmail(attributes.getNamedItem(EMAIL).getTextContent());
        student.setStartDate(attributes.getNamedItem(DATE_START).getTextContent());
        student.setContractSigned(attributes.getNamedItem(SIGN_CONTRACT).getTextContent());
    }


    private Program createProgram(Node root, Program program){
        if(root==null) {
            return program;
        }

        NodeList nodeL=root.getChildNodes();

        for(int i=0;i<nodeL.getLength();i++){
            if(nodeL.item(i).getLocalName()!=null
                    &&
                    nodeL.item(i).getLocalName().equals(DESCRIPTION_PROGRAM_STUDENT)){
                fillAttributesProgram(nodeL.item(i),program);
            }
        }

        return program;
    }

    private void fillAttributesProgram(Node node, Program program){
        NamedNodeMap attributes=node.getAttributes();
        program.setTitle(attributes.getNamedItem(NAME).getTextContent());
        program.setAuthor(attributes.getNamedItem(AUTHOR).getTextContent());
        program.setLatsModified(attributes.getNamedItem(DATE_CREATE).getTextContent());
        program.setCourses(createCourses(node.getChildNodes()));
        program.setDuration(calculateDurationProgram(program));
    }


    private ArrayList<Course> createCourses(NodeList nodeList){

        ArrayList<Course> outCourses=new ArrayList<>();
        Node listCourses=null;
        for(int i=0;i<nodeList.getLength();i++){
            if(nodeList.item(i).getLocalName()!=null
                    &&
                    nodeList.item(i).getLocalName().equals(LIST_COURSES)){
                listCourses=nodeList.item(i);
                break;
            }
        }

        NodeList courses=listCourses.getChildNodes();

        for(int i=0;i<courses.getLength();i++){
            if(courses.item(i).getLocalName()!=null
                    &&
                    courses.item(i).getLocalName().equals(COURSE)){
                outCourses.add(createCourse(courses.item(i), new Course()));
            }
        }

        return outCourses;
    }


    private Course createCourse(Node root, Course course){

    }

    private void fillAttributesCourse(Node node, Course course){
        NamedNodeMap attributes=node.getAttributes();
        course.setTitle(attributes.getNamedItem());
        course.setAuthor();
        course.setLastModified();
        course.setTasks(createTasks());
        course.setDuration(calculateDurationCourse(course));
    }



    public void roundDOM(Node root){
        if(root==null){
            return;
        }

        NodeList listNodes=root.getChildNodes();

        System.out.println("Length: "+listNodes.getLength());

        for(int i=0;i<listNodes.getLength();i++){
            printlnCommon(listNodes.item(i));
            System.out.println("###########");
            roundDOM(listNodes.item(i));
        }
    }

    private void printlnCommon(Node n) {
        System.out.print(" nodeName=\"" + n.getNodeName() + "\"");

        String val = n.getLocalName();
        if (val != null) {
            System.out.print(" local=\"" + val + "\"");
        }

        val=n.getTextContent();
        if (val != null) {
            System.out.print(" textContext=\"" + val + "\"");
        }

        val = n.getPrefix();

        if (val != null) {
            System.out.print(" pre=\"" + val + "\"");
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
