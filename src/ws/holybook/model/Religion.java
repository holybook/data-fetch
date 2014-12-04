package ws.holybook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class to represent an author in the database.
 * 
 * @author Hannes Widmoser
 */
public class Religion {

	private String id;

	private List<Author> authors;

	public Religion() {
		authors = new ArrayList<>();
	}
	
	public Religion(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void addAuthor(Author author) {
		authors.add(author);
	}

	public List<Author> getAuthors() {
		return authors;
	}

}
