package main.java.dam;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ToXML {


    public static void main(String[] args) throws Exception {
	
        //Create the DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //Create the DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Create the Document
        Document doc = builder.newDocument();


        Element results = doc.createElement("Person");
        doc.appendChild(results);

        
        final Database db = new Database(
            "server.berzirkhosting.com",
            "CGCC",
            1444,
            false,
            "AdventureWorks",
            "jdoe",
            "Password1234!"
            );

        Connection con = db.connection();
        
        ResultSet rs = con.createStatement().executeQuery("SELECT * "
                                                            +" FROM "
                                                                + "Person.Person"
                                                                + " WHERE "
                                                                    + "rowguid='"
                                                                    + "E8673E92-31F6-42F4-A65C-A595D3F899F6"
                                                                    + "'");

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();

        while (rs.next()) {
        Element row = doc.createElement("Row");
        results.appendChild(row);
            for (int i = 1; i <= colCount; i++) {
                String columnName = rsmd.getColumnName(i);
                Object value = rs.getObject(i);
                if (rs.wasNull()){
                    value = "null";
                }
                //Create a new element with the same name as the column
                Element node = doc.createElement(columnName);
                node.appendChild(doc.createTextNode(value.toString()));
                //Add the new element to the row
                row.appendChild(node);
            }
        }
        //Create new DOMSource
        DOMSource domSource = new DOMSource(doc);
        //Create the TransformerFactory
        TransformerFactory tf = TransformerFactory.newInstance();
        //Create the Transformer
        Transformer transformer = tf.newTransformer();
        //Omit XML Declaration
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        //Set output method to XML
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        //Transform Source to a Result
        transformer.transform(domSource, sr);

        System.out.println(sw.toString());

        con.close();
        rs.close();
    }
    
  
}
