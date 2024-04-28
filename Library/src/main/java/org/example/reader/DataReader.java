package org.example.reader;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;

public class DataReader {
    private XMLStreamReader _reader;
    private ObjectMapper _mapper;

    public DataReader(String readerPath, String mapperPath) throws IOException, XMLStreamException {
        FileReader input = new FileReader(readerPath);
        FileWriter output = new FileWriter(mapperPath);
        XMLInputFactory f = XMLInputFactory.newInstance();
        _reader = f.createXMLStreamReader(input);
    }
}
