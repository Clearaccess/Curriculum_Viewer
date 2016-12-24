package com.epam.com.aleksandr_vaniukov.curriculum_viewer;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Created by Aleksandr on 12/25/2016.
 */
public class ParserErrorHandler implements ErrorHandler {
    private String getParseExceptionInfo(SAXParseException e) {
        String systemId = e.getSystemId();
        if (systemId == null) {
            systemId = "null";
        }
        return "URI=" + systemId + " Line=" + e.getLineNumber()
                + ": " + e.getMessage();
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("Warning: " + getParseExceptionInfo(exception));
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        String message = "Error: " + getParseExceptionInfo(exception);
        throw new SAXException(message);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        String message = "Fatal Error: " + getParseExceptionInfo(exception);
        throw new SAXException(message);
    }
}
