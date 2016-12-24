package com.epam.com.aleksandr_vaniukov.curriculum_viewer.Controller;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.epam.com.aleksandr_vaniukov.curriculum_viewer.ParserErrorHandler;
import com.epam.com.aleksandr_vaniukov.curriculum_viewer.model.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import static com.sun.org.apache.xerces.internal.jaxp.JAXPConstants.JAXP_SCHEMA_LANGUAGE;
import static com.sun.org.apache.xerces.internal.jaxp.JAXPConstants.JAXP_SCHEMA_SOURCE;


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
    private final String TYPE="type";
    private final String PERIOD="period";
    private final String STATUS="status";

    private final String SCHEMA_PATH=System.getProperty("user.dir")+"/resources/Report.xsd";
    private File file;
    private File schemaFile;
    private DataStudents dataStudents;

    public void setFile(File file){
        this.file=file;
        this.dataStudents=new DataStudents();
    }

    public DataStudents getData(){
        return dataStudents;
    }

    public void parseXML() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        try {
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
        }
        catch (IllegalArgumentException x) {
            System.err.println("Error: JAXP DocumentBuilderFactory "
                    + " attribute not recognized: " + JAXP_SCHEMA_LANGUAGE);
            System.err.println("Check to see if parser conforms "
                    + "to JAXP 1.2 spec.");
        }

        schemaFile=new File(SCHEMA_PATH);
        factory.setAttribute(JAXP_SCHEMA_SOURCE, schemaFile);

        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new ParserErrorHandler());
        Document doc = builder.parse(file);

        NodeList nodeList=doc.getElementsByTagName("listStudents").item(0).getChildNodes();
        for(int i=0;i<nodeList.getLength();i++) {
            dataStudents.addStudent(createStudent(nodeList.item(i), new Student()));
        }

    }

    private Student createStudent(Node root, Student student){

        NodeList nodeL=root.getChildNodes();
        for(int i=0;i<nodeL.getLength();i++){
            if(nodeL.item(i).getLocalName().equals(DESCRIPTION_STUDENT)) {
                fillAttributesStudent(nodeL.item(i), student);
                continue;
            }

            if(nodeL.item(i).getLocalName().equals(DESCRIPTION_PROGRAM_STUDENT)){
                student.setProgram(createProgram(nodeL.item(i), new Program()));
            }
        }

        return student;
    }

    private void fillAttributesStudent(Node node,Student student) {
        NamedNodeMap attributes=node.getAttributes();
        student.setFullName(attributes.getNamedItem(FULL_NAME).getTextContent());
        student.setRegion(attributes.getNamedItem(CITY).getTextContent());
        student.setEmail(attributes.getNamedItem(EMAIL).getTextContent());
        student.setStartDate(attributes.getNamedItem(DATE_START).getTextContent());
        student.setContractSigned(attributes.getNamedItem(SIGN_CONTRACT).getTextContent());
    }


    private Program createProgram(Node root, Program program){

        fillAttributesProgram(root,program);

        return program;
    }

    private void fillAttributesProgram(Node node, Program program){
        NamedNodeMap attributes=node.getAttributes();
        program.setTitle(attributes.getNamedItem(NAME).getTextContent());
        program.setAuthor(attributes.getNamedItem(AUTHOR).getTextContent());
        program.setLastModified(attributes.getNamedItem(DATE_CREATE).getTextContent());
        program.setCourses(createCourses(node));
        program.setDuration(calculateDurationProgram(program));
    }


    private ArrayList<Course> createCourses(Node node){
        NodeList nodeList=node.getChildNodes();
        ArrayList<Course> outCourses=new ArrayList<>();
        Node listCourses=null;
        for(int i=0;i<nodeList.getLength();i++){
            if(nodeList.item(i).getLocalName().equals(LIST_COURSES)){
                listCourses=nodeList.item(i);
            }
        }

        NodeList courses=listCourses.getChildNodes();

        for(int i=0;i<courses.getLength();i++) {
            outCourses.add(createCourse(courses.item(i), new Course()));
        }

        return outCourses;
    }


    private Course createCourse(Node root, Course course){
        fillAttributesCourse(root,course);
        return course;
    }

    private void fillAttributesCourse(Node node, Course course){
        NamedNodeMap attributes=node.getAttributes();
        course.setTitle(attributes.getNamedItem(NAME).getTextContent());
        course.setAuthor(attributes.getNamedItem(AUTHOR).getTextContent());
        course.setLastModified(attributes.getNamedItem(DATE_CREATE).getTextContent());
        course.setTasks(createTasks(node, course));
        course.setDuration(calculateDurationCourse(course));
    }

    private ArrayList<Task> createTasks(Node node, Course course){
        NodeList nodeList=node.getChildNodes();
        ArrayList<Task> outTasks=new ArrayList<>();
        for(int i=0;i<nodeList.getLength();i++) {
            outTasks.add(createTask(nodeList.item(i), course, new Task()));
        }

        return outTasks;
    }

    private Task createTask(Node node,Course course, Task task){
        NodeList nodeList=node.getChildNodes();
        for(int i=0;i<nodeList.getLength();i++) {
            fillAttributesTask(nodeList.item(i), course, task);
        }
        return task;
    }

    private void fillAttributesTask(Node node, Course course, Task task){

        NamedNodeMap attributes=node.getAttributes();

        task.setType(attributes.getNamedItem(TYPE).getTextContent());
        task.setTitle(attributes.getNamedItem(NAME).getTextContent());
        task.setDuration(Integer.parseInt(attributes.getNamedItem(PERIOD).getTextContent()));
        task.setAuthor(course.getAuthor());
        task.setLastModified(course.getLastModified());
        task.setStatus(attributes.getNamedItem(STATUS).getTextContent());
    }

    private int calculateDurationCourse(Course course){
        int outValue=0;

        ArrayList<Task> tasks=course.getTasks();
        for (Task task : tasks) {
            outValue += task.getDuration();
        }

        return outValue;
    }

    private int calculateDurationProgram(Program program){
        int outValue=0;

        ArrayList<Course> courses=program.getCourses();
        for (Course course : courses) {
            outValue += course.getDuration();
        }

        return outValue;
    }
}
