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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Model class to represent a book in the database.
 * 
 * @author Hannes Widmoser
 */
@XmlRootElement
public class Book {

	private String language;

	private String title;

	private String source;

	private String author;
	private String religion;

	private int pages;

	private String filename;

	private List<Section> text;

	public Book() {
		this.text = new ArrayList<>();
	}

	public Book(String language, String title, String source, String author, int pages, List<Section> text) {
		this();
		setLanguage(language);
		setTitle(title);
		setSource(source);
		setPages(pages);
		setText(text);
		setAuthor(author);
	}

	public Book(String language, String title, String source, String author, int pages, Section... text) {
		this(language, title, source, author, pages, Arrays.asList(text));
	}

	@XmlAttribute
	public String getId() {
		return filename;
	}

	@XmlAttribute
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@XmlAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.filename = IdUtil.encode(title);
	}

	@XmlAttribute
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@XmlAttribute
	public String getAuthor() {
		return author;
	}
	
	@XmlAttribute
	public String getReligion() {
		return religion;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setReligion(String religion) {
		this.religion = religion;
	}

	@XmlAttribute
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@XmlElement(name = "section")
	public List<Section> getText() {
		return text;
	}

	public void setText(List<Section> text) {
		for (int i = 0; i < text.size(); ++i) {
			text.get(i).setId(String.valueOf(i));
		}
		this.text = text;
	}

	@XmlTransient
	public String getFilename() {
		return filename;
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
		File file = new File(directory, getFilename() + ".xml");
		getXMLMarshaller().marshal(this, file);
	}

	public static Book readXML(InputStream input) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Book b = (Book)jaxbUnmarshaller.unmarshal(input);
		return b;
	}
}
