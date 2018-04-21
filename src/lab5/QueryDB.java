package lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryDB {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String url;
	private String uid;
	private String pw;

	public QueryDB() {
		url = "jdbc:mysql://localhost:18081/jcedward";
		//url = "jdbc:mysql://faure/jcedward";
		uid = "jcedward";
		pw = "830594668";
		
	}

	
	public void queryMember(String query) {
		System.out.println("Query for memberID: " + query);
		String memberQuery = "Select m.memberID from member m where m.memberID='" 
				+ query + "';";
		con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uid, pw);
			stmt = con.createStatement();
			
			try {
				
				rs = stmt.executeQuery(memberQuery);
				while(rs.next()) {
					System.out.println(rs.getString("m.memberID"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
