package ws.holybook.documents;

import java.util.Arrays;
import java.util.List;

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
	
	List<String> paragraphs;
	String title;
	
	public Section() {}

	public Section(String title, List<String> paragraphs) {
		super();
		this.paragraphs = paragraphs;
		this.title = title;
	}
	
	public Section(String title, String... paragraphs) {
		super();
		this.paragraphs = Arrays.asList(paragraphs);
		this.title = title;
	}

	@XmlElement(name = "p")
	public List<String> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<String> paragraphs) {
		this.paragraphs = paragraphs;
	}

	@XmlAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
