package ws.holybook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class to represent an author in the database.
 * 
 * @author Hannes Widmoser
 */
public class Author {

	private String id;

	private List<Book> books;

	public Author() {
		books = new ArrayList<>();
	}
	
	public Author(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void addBook(Book author) {
		books.add(author);
	}
	
	public List<Book> getBooks() {
		return books;
	}

}
