package ws.holybook.model;

import ws.holybook.utils.IdUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

/**
 * Model class to represent a book in the database.
 * 
 * @author Hannes Widmoser
 */
@XmlRootElement
@XmlType(propOrder={"meta", "content"})
public class Book {

	private Meta meta;
	private Content content;

	private String id;

	public Book() {
		this.content = new Content();
		this.meta = new Meta();
	}

	public Book(Meta meta) {
		this();
		this.meta = meta;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return id + ".xml";
	}

	@XmlElement(name = "meta")
	public Meta getMeta() {
		return meta;
	}

	@XmlElement(name = "content")
	public Content getContent() {
		return content;
	}

	private Marshaller getXMLMarshaller() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		return jaxbMarshaller;
	}

	public void writeXML(Writer w) throws JAXBException, IOException {
		Marshaller m = getXMLMarshaller();
		w.write("<?xml version='1.0'?>\n");
		w.write("<?xml-stylesheet type=\"text/xsl\" href=\"/book.xsl\" ?>\n");
		m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		m.marshal(this, w);
	}

	public void writeXML(File directory) throws JAXBException {
		File file = new File(directory, getId() + ".xml");
		getXMLMarshaller().marshal(this, file);
	}

	public static Book readXML(InputStream input) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Book b = (Book)jaxbUnmarshaller.unmarshal(input);
		return b;
	}
}
