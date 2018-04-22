package lab5;

public class QueryBuilder {

	public QueryBuilder() {
	}

	public String getMemberQuery(String memberID) {
		return "Select m.memberID from member m where m.memberID='" + memberID + "';";
	}

	public String getISBNQuery(String isbn) {
		System.out.println("query for book: " + isbn);
		return "select b.Title, b.ISBN, a.first_name, a.last_name "
				+ "from book b inner join writes w inner join author a where "
				+ "w.ISBN=b.ISBN and w.authorID=a.authorID and b.ISBN='" + isbn + "';";
	}

	public String getBookNameQuery(String name) {
		return "select b.Title, b.ISBN, a.first_name, a.last_name "
				+ "from book b inner join writes w inner join author a where "
				+ "w.ISBN=b.ISBN and w.authorID=a.authorID and b.Title like '%" + name + "%';";
	}

	public String getBookByAuthorQuery(String name) {
		return "select b.Title, b.ISBN, a.first_name, a.last_name "
				+ "from book b inner join writes w inner join author a where "
				+ "w.ISBN=b.ISBN and w.authorID=a.authorID and (a.first_name like '%" + name
				+ "%' OR a.last_name like '%" + name + "%');";
	}

	public String getBookInventoryQuery(Book b) {
		return "select DISTINCT b.Title, st.name, st.shelf_no " + "from stored_on st inner join book b "
				+ "inner join borrowed br " + "where st.ISBN=b.ISBN " + "and b.ISBN=br.ISBN " + "and b.ISBN='"
				+ b.getisbn() + "'";
	}

	public String getBookExistQuery(Book b) {
		return "Select ISBN from book where ISBN IN "
				+ "( select DISTINCT b.ISBN from stored_on st inner join book b inner join borrowed br where st.ISBN=b.ISBN and b.ISBN=br.ISBN)" 
				+ " and ISBN='"	+ b.getisbn() + "';";
	}
}
