package ws.holybook.documents;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class to represent a book in the database.
 * 
 * @author Hannes Widmoser
 */
@XmlRootElement
public class Document {
	
	 private String language;
	 private String title;
	 private String source;
	 private int pages;
	
	 private List<Section> text;

	public Document() {
	}

	public Document(String language, String title, String source, int pages, List<Section> text) {
		super();
		this.language = language;
		this.title = title;
		this.source = source;
		this.pages = pages;
		this.text = text;
	}
	
	public Document(String language, String title, String source, int pages, Section... text) {
		super();
		this.language = language;
		this.title = title;
		this.source = source;
		this.pages = pages;
		this.text = Arrays.asList(text);
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
	}

	@XmlAttribute
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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
		this.text = text;
	}

}
