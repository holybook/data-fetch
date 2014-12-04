package ws.holybook.model;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class to represent a chapter in a book.
 * 
 * @author Hannes Widmoser
 * 
 */
@XmlRootElement
public class Section {

	private String id;

	private List<String> paragraphs;

	private String title;

	public Section() {
		paragraphs = new ArrayList<>();
	}

	public Section(String title, List<String> paragraphs) {
		this();
		setParagraphs(paragraphs);
		this.title = title;
	}

	public Section(String title, String... paragraphs) {
		this(title, Arrays.asList(paragraphs));
	}

	@XmlAttribute
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "p")
	public List<String> getParagraphs() {
		// return new TextAdapter(paragraphs);
		return paragraphs;
	}
	
	public void setParagraphs(List<String> paragraphs) {
		// this.paragraphs = new ArrayList<>();
		// for (String p : paragraphs) {
		// this.paragraphs.add(new Text(p));
		// }
		if (this.paragraphs != paragraphs) {
			this.paragraphs.clear();
			this.paragraphs.addAll(paragraphs);
		}
	}

	@XmlAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		getXMLMarshaller().marshal(this, w);
	}

}
