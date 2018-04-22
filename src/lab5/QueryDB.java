package lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryDB {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String url;
	private String uid;
	private String pw;
	private ArrayList<Book> resultsList;
	private QueryBuilder queryBuilder;

	public QueryDB() {
		url = "jdbc:mysql://localhost:18081/jcedward";
		//url = "jdbc:mysql://faure/jcedward";
		uid = "jcedward";
		pw = "830594668";
		resultsList = new ArrayList<>();
		queryBuilder = new QueryBuilder();
		
	}

	
	public boolean queryMember(String memberID) {
		System.out.println("Query for: " + memberID);
		String memberReturned = "";
		con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			try {
				
				rs = stmt.executeQuery(queryBuilder.getMemberQuery(memberID));
				while(rs.next()) {
					memberReturned = rs.getString("m.memberID");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

			con.close();
			if(memberID.isEmpty()) return false;
			System.out.println("Query: " + memberID + ", result: " + memberReturned);
			return memberID.equals(memberReturned);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	public ArrayList<Book> queryBookByISBN(String isbn) {
		con = null;
		if (!resultsList.isEmpty())
			resultsList.clear();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();

			rs = stmt.executeQuery(queryBuilder.getISBNQuery(isbn));
			while (rs.next()) {
				Book b = new Book(rs.getString("b.ISBN"), rs.getString("b.Title"),
						rs.getString("a.first_name") + " " + rs.getString("a.last_name"));
				resultsList.add(b);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Book> queryBookByName(String name) {
		con = null;
		if (!resultsList.isEmpty())
			resultsList.clear();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();

			rs = stmt.executeQuery(queryBuilder.getBookNameQuery(name));
			while (rs.next()) {
				Book b = new Book(rs.getString("b.ISBN"), rs.getString("b.Title"),
						rs.getString("a.first_name") + " " + rs.getString("a.last_name"));
				resultsList.add(b);
			}

			con.close();
			return resultsList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Book> queryBookByAuthor(String name) {
		con = null;
		if (!resultsList.isEmpty())
			resultsList.clear();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();

			rs = stmt.executeQuery(queryBuilder.getBookByAuthorQuery(name));
			while (rs.next()) {
				Book b = new Book(rs.getString("b.ISBN"), rs.getString("b.Title"),
						rs.getString("a.first_name") + " " + rs.getString("a.last_name"));
				resultsList.add(b);
				System.out.println(
						rs.getString("b.Title") + " " + rs.getString("b.ISBN") + " " + rs.getString("a.first_name"));
			}

			con.close();
			return resultsList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String queryBookInventory(Book b) {
		con = null;
		String info = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			// first lets see if the book is in the library
			// from lab 4 data, only 3 books in the "book" relation
			// are not part of the library
			System.out.println("check if book exists in libary: " + b.getisbn());
			rs = stmt.executeQuery(queryBuilder.getBookExistQuery(b));
			if(!rs.first())
				System.out.println(b.getisbn() + " does not exist in library");
			
			


			rs = stmt.executeQuery(queryBuilder.getBookInventoryQuery(b));
			while (rs.next()) {
				info += (rs.getString("b.Title") + ", " + rs.getString("st.name") + ", Shelf #"
						+ rs.getString("st.shelf_no") + "\n");

			}

			con.close();
			return info;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}















