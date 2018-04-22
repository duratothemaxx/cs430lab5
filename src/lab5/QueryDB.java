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

	public QueryDB() {
		url = "jdbc:mysql://localhost:18081/jcedward";
		//url = "jdbc:mysql://faure/jcedward";
		uid = "jcedward";
		pw = "830594668";
		resultsList = new ArrayList<>();
		
	}

	
	public boolean queryMember(String query) {
		System.out.println("Query for: " + query);
		String memberQuery = "Select m.memberID from member m where m.memberID='" 
				+ query + "';";
		String memberReturned = "";
		con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			try {
				
				rs = stmt.executeQuery(memberQuery);
				while(rs.next()) {
					memberReturned = rs.getString("m.memberID");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

			con.close();
			if(query.isEmpty()) return false;
			System.out.println("Query: " + query + ", result: " + memberReturned);
			return query.equals(memberReturned);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public void insertMember(String values) {
		String insertStmt = "INSERT INTO member VALUES "
				+ values;
		System.out.println("Insert statement: " + insertStmt);
		int count = 0;

		con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			try {
				//count = stmt.executeUpdate(insertStmt);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("rows updated: " + count);					
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Book> queryBookByISBN(String isbn) {
		con = null;
		if(!resultsList.isEmpty())
			resultsList.clear();
		
		String query = "select b.Title, b.ISBN, a.first_name, a.last_name "
				+ "from book b inner join writes w inner join author a where "
				+ "w.ISBN=b.ISBN and w.authorID=a.authorID and b.ISBN='"
				+ isbn +"';";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			try {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Book b = new Book(rs.getString("b.ISBN"), rs.getString("b.Title")
							, rs.getString("a.first_name") + " " + rs.getString("a.last_name"));
					resultsList.add(b);
					System.out.println(rs.getString("b.Title") + " "
							+ rs.getString("b.ISBN") + " " + rs.getString("a.first_name"));

				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public ArrayList<Book> queryBookByName(String name) {
		con = null;
		if(!resultsList.isEmpty())
			resultsList.clear();
		
		String query = "select b.Title, b.ISBN, a.first_name, a.last_name "
				+ "from book b inner join writes w inner join author a where "
				+ "w.ISBN=b.ISBN and w.authorID=a.authorID and b.Title like '%"
				+ name +"%';";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			try {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Book b = new Book(rs.getString("b.ISBN"), rs.getString("b.Title")
							, rs.getString("a.first_name") + " " + rs.getString("a.last_name"));
					resultsList.add(b);
					System.out.println(rs.getString("b.Title") + " "
							+ rs.getString("b.ISBN") + " " + rs.getString("a.first_name"));

				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			con.close();
			return resultsList;

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public ArrayList<Book> queryBookByAuthor(String name) {
		con = null;
		if(!resultsList.isEmpty())
			resultsList.clear();
		
		String query = "select b.Title, b.ISBN, a.first_name, a.last_name "
				+ "from book b inner join writes w inner join author a where "
				+ "w.ISBN=b.ISBN and w.authorID=a.authorID and (a.first_name like '%"
				+ name +"%' OR a.last_name like '%" + name + "%');";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			try {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Book b = new Book(rs.getString("b.ISBN"), rs.getString("b.Title")
							, rs.getString("a.first_name") + " " + rs.getString("a.last_name"));
					resultsList.add(b);
					System.out.println(rs.getString("b.Title") + " "
							+ rs.getString("b.ISBN") + " " + rs.getString("a.first_name"));

				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			con.close();
			return resultsList;

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public String queryBookInventory(Book b) {
		con = null;
		String info = "";
		String query = "";

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			try {
				rs = stmt.executeQuery(query);
				while(rs.next()) {

				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			con.close();
			return info;

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

}















