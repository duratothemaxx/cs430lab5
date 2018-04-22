package lab5;

public class Book {
	private String isbn;
	private String title;
	private String author;
	
	public Book(String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;		
	}
	
	public String getisbn() {return this.isbn;}
	public String getTitle() {return this.title;}
	public String getAuthor() {return this.author;}
	
	public String toString() {
		return title + ", " + isbn + ", " + author;
	}
}
